package com.spi.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.configuration.CompositeConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.shamu.jsendnrdp.domain.State;

import com.spi.dao.DbOps;
import com.spi.mediator.Preferences;
import com.spi.model.Cols;
import com.spi.model.Device;
import com.spi.model.DeviceType;
import com.spi.model.IosDevicesHistory;
import com.spi.model.KeyVal;
import com.spi.model.TableType;
import com.spi.model.VbsDevicesHistory;

public class ProbeDb implements Runnable {

	protected static final Logger LOGGER = LoggerFactory.getLogger(ProbeDb.class);
	private static final int LIMIT = 1;
	private int iosWarnLimit;
	private int vbsWarnLimit;
	private Connection conn;
	private Long thresholdDuration;
	private String nrdpUrl;
	private String nrdpToken;
	private String nrdpServiceIos;
	private String nrdpServiceVbs;
	private int nrdpTimeout;
	private DbOps dbo;

	public ProbeDb(Connection conn, CompositeConfiguration config) {
		LOGGER.info(">>> SACHIN Instantiating Composite = {}", ProbeDb.class.getCanonicalName());
		this.conn = conn;
		this.nrdpUrl = config.getString(Preferences.NRDP_URL);
		this.nrdpToken = config.getString(Preferences.NRDP_TOKEN);
		this.nrdpServiceIos = config.getString(Preferences.NRDP_SERVICE_IOS);
		this.nrdpServiceVbs = config.getString(Preferences.NRDP_SERVICE_VBS);
		this.nrdpTimeout = Integer.parseInt(config.getString(Preferences.NRDP_TIMEOUT));
		this.thresholdDuration = Long.parseLong(config.getString(Preferences.THRESHOLD_DURATION));
		this.iosWarnLimit = Integer.parseInt(config.getString(Preferences.IOS_WARN_LIMIT));
		this.vbsWarnLimit = Integer.parseInt(config.getString(Preferences.VBS_WARN_LIMIT));
		this.dbo = new DbOps();
		LOGGER.info(">>> ProbeDB instantiated with:");
		LOGGER.info(">>> NRDP URL {} | NRDP Token {} | NRDP Service VBS {} | NRDP Service IOS {} | NRDP timeout {}", nrdpUrl, nrdpToken,
				nrdpServiceVbs, nrdpServiceIos, nrdpTimeout);
		LOGGER.info(">>> Threshold duration {} | iosWarnLimit {} | vbsWarnLimit {}", thresholdDuration, iosWarnLimit, vbsWarnLimit);
	}

	public void run() {
		LOGGER.info(">>> Probing Database >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		try {
			// get all devices
			ResultSet devices = dbo.execAllDevicesSql(conn);
			while (devices.next()) {
				String deviceId = devices.getString(Cols.DEVICE_ID.toString());
				DeviceType deviceType = DeviceType.valueOf(devices.getString(Cols.DEVICE_TYPE.toString()));
				LOGGER.info(">>> Getting individual device details");
				LOGGER.info(">>> Device ID: {} | Device Type: {} | Limit: {} ", deviceId, deviceType, LIMIT);

				// get individual details from register table
				ResultSet rs = dbo.execDeviceDtlsSql(conn, deviceId, LIMIT, deviceType, TableType.REGISTER);
				while (rs.next()) {
					String devNetworkName = getNetworkName(rs, deviceType);
					Integer devDispStatus = getDispStatus(rs, deviceType);
					String deviceName = rs.getString(Cols.DEVICE_NAME.toString());
					Timestamp lstUpdTS = rs.getTimestamp(Cols.LAST_UPDATE_TIMESTAMP.toString());
					Date currentTS = new Date();
					Long currentTSLong = currentTS.getTime();
					Date deviceStatusTS = currentTS;
					Long lstUpdTSLong = lstUpdTS.getTime() + (lstUpdTS.getNanos() / 1000000);
					Long timeDiff = currentTSLong - lstUpdTSLong;

					LOGGER.info(">>> Time Diff = {} in millisecs", timeDiff);
					LOGGER.info(">>> Details of Device | DeviceName: {} | DeviceId: {} | LastUpdatedTS: {}", deviceName, deviceId, lstUpdTS);
					// calculations w.r.t. the history table
					Device dev = getDeviceAttr(conn, deviceId, deviceName, devNetworkName, deviceStatusTS, timeDiff, deviceType, devDispStatus);
					// Insert into History Table
					LOGGER.info(">>> Inserting into History table >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					insertDevice(dev);

					// Nagios Ops
					LOGGER.info(">>> Starting Nagios NRDP Communication >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					String nrdpMsg = "";
					if (deviceType == DeviceType.IOS) {
						nrdpMsg = "(DeviceName: " + dev.getDeviceName() + ") (Status: " + dev.getDevLstState() + ")";
						LOGGER.info(">>> Nagios Message >> {}", nrdpMsg);
						NrdpService.sendNrdpMessage(nrdpUrl, deviceId, nrdpServiceIos, nrdpToken, nrdpTimeout, dev.getDevLstState(), nrdpMsg);
					} else if (deviceType == DeviceType.VBS) {
						nrdpMsg = "(DeviceName: " + dev.getDeviceName() + ") (Status: " + dev.getDevLstState() + ")";
						LOGGER.info(">>> Nagios Message >> {}", nrdpMsg);
						NrdpService.sendNrdpMessage(nrdpUrl, deviceId, nrdpServiceVbs, nrdpToken, nrdpTimeout, dev.getDevLstState(), nrdpMsg);
					}

					// APNs Ops
					if (deviceType == DeviceType.IOS) {
						LOGGER.info(">>> Starting APNS Communication >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
						String deviceToken = rs.getString(Cols.DEVICE_TOKEN.toString());
						if (dev.getDevLstState() == State.WARNING || dev.getDevLstState() == State.CRITICAL)
							PushNotificationProvider.pushPayLoad(deviceToken);
					}

				}
			}
		} catch (SQLException e) {
			LOGGER.error(">>> Some MySQL issues...");
			e.printStackTrace();
		}
		LOGGER.info(">>> Done Probing Database >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}

	private Device getDeviceAttr(Connection conn, String id, String name, String nwName, Date statusTS, Long timeDiff, DeviceType devType,
			Integer dispStat) throws SQLException {
		LOGGER.info(">>> Getting device details");
		ResultSet hs = dbo.execDeviceDtlsSql(conn, id, LIMIT, devType, TableType.HISTORY);

		Device dev = new Device();
		if (!hs.next()) {
			dev = new Device(devType, id, name, State.OK, nwName, 1, dispStat, statusTS);
		} else {
			hs.beforeFirst();
			while (hs.next()) {
				int devStatCount = hs.getInt(Cols.STATUS_COUNT.toString());
				State devLstState = State.valueOf(hs.getString(Cols.LAST_STATUS.toString()));
				LOGGER.info(">>> BEFORE : device Status: {}, Status Count: {}", devLstState, devStatCount);
				LOGGER.info(">>> TS diff: {}, Threshold duration: {}", timeDiff, thresholdDuration);

				KeyVal kv = null;
				if (devType == DeviceType.IOS)
					kv = getStateStatCnt(timeDiff, thresholdDuration, devLstState, devStatCount, DeviceType.IOS);
				else if (devType == DeviceType.VBS)
					kv = getStateStatCnt(dispStat, timeDiff, thresholdDuration, devLstState, devStatCount, DeviceType.VBS);

				dev.setDeviceType(devType);
				dev.setDeviceId(id);
				dev.setDeviceName(name);
				dev.setDevNetworkName(nwName);
				dev.setDevLstState(kv.getDevLstState());
				dev.setDeviceStatusTS(statusTS);
				dev.setDevStatCount(kv.getDevStatCount());
				dev.setDispStatus(dispStat);
			}
		}
		LOGGER.info(">>> Returning: {}", dev);
		return dev;
	}

	private KeyVal getStateStatCnt(int dispStat, Long timeDiff, Long thresholdDuration, State devLstState, int devStatCount, DeviceType devType) {
		KeyVal kv = new KeyVal();
		if (dispStat > 0 && timeDiff <= thresholdDuration) {
			LOGGER.info(">>> All OK");
			if (devLstState == State.CRITICAL || devLstState == State.WARNING || devLstState == State.UNKNOWN) {
				devLstState = State.OK;
				devStatCount = 1;
			} else if (devLstState == State.OK) {
				devStatCount++;
			}
			kv.setDevLstState(devLstState);
			kv.setDevStatCount(devStatCount);
		} else if (dispStat > 0 && timeDiff > thresholdDuration) {
			LOGGER.info(">>> All OK... BUT HEARTBEAT MISSING");
			if (devLstState == State.UNKNOWN)
				devStatCount++;
			else
				devStatCount = 1;
			devLstState = State.UNKNOWN;
			kv.setDevLstState(devLstState);
			kv.setDevStatCount(devStatCount);
		} else if (dispStat == 0 && timeDiff <= thresholdDuration) {
			LOGGER.info(">>> All NOT OK... BUT HEARTBEAT EXIST");
			kv = calculateState(devLstState, devStatCount);
		} else if (dispStat == 0 && timeDiff > thresholdDuration) {
			LOGGER.info(">>> All NOT OK... AND HEARTBEAT MISSING TOO");
			if (devLstState == State.UNKNOWN)
				devStatCount++;
			else
				devStatCount = 1;
			devLstState = State.UNKNOWN;
			kv.setDevLstState(devLstState);
			kv.setDevStatCount(devStatCount);
		}
		return kv;
	}

	private KeyVal getStateStatCnt(Long timeDiff, Long thresholdDuration, State devLstState, int devStatCount, DeviceType devType) {
		KeyVal kv = new KeyVal();
		if (timeDiff <= thresholdDuration) {
			LOGGER.info(">>> All OK");
			if (devLstState == State.CRITICAL || devLstState == State.WARNING) {
				devLstState = State.OK;
				devStatCount = 1;
			} else if (devLstState == State.OK) {
				devStatCount++;
			}
			kv.setDevLstState(devLstState);
			kv.setDevStatCount(devStatCount);
		} else {
			kv = calculateState(devLstState, devStatCount);
		}
		return kv;
	}

	private KeyVal calculateState(State devLstState, Integer devStatCount) {
		LOGGER.info(">>> All NOT OK");
		switch (devLstState) {
			case OK:
				LOGGER.info(">>> MOVING TO WARNING");
				devLstState = State.WARNING;
				devStatCount = 1;
				break;
			case WARNING:
				LOGGER.info(">>> CHECKING WARNING STATE");
				if (devStatCount < iosWarnLimit) {
					LOGGER.info(">>> CHECKING IOS WARNING STATE");
					devLstState = State.WARNING;
					devStatCount++;
				} else {
					LOGGER.info(">>> MOVING TO CRITICAL");
					devLstState = State.CRITICAL;
					devStatCount = 1;
				}
				break;
			case CRITICAL:
				LOGGER.info(">>> INCREMENTING CRITICAL");
				devStatCount++;
				break;
			case UNKNOWN:
				LOGGER.info(">>> MOVING TO WARNING");
				devLstState = State.WARNING;
				devStatCount = 1;
				break;
			default:
				break;
		}
		return new KeyVal(devLstState, devStatCount);
	}

	private void insertDevice(Device dev) {
		try {
			switch (dev.getDeviceType()) {
				case IOS:
					IosDevicesHistory ios = new IosDevicesHistory();
					ios.setDevId(dev.getDeviceId());
					ios.setDevName(dev.getDeviceName());
					ios.setChkType(DbOps.IOS_CHECK_TYPE);
					ios.setNetworkName(dev.getDevNetworkName());
					ios.setLstStat(dev.getDevLstState().toString());
					ios.setStatCount(dev.getDevStatCount());
					ios.setLastUpdTS(dev.getDeviceStatusTS());
					LOGGER.info("iOS device created", ios);
					LOGGER.info("iOS device ID : {}", ios.getDevId());
					dbo.insertIosDevicesHistory(conn, ios);
					break;
				case VBS:
					VbsDevicesHistory vbs = new VbsDevicesHistory();
					vbs.setDevId(dev.getDeviceId());
					vbs.setDevName(dev.getDeviceName());
					vbs.setChkType(DbOps.IOS_CHECK_TYPE);
					vbs.setLstStat(dev.getDevLstState().toString());
					vbs.setDisplayStatus(dev.getDispStatus());
					vbs.setStatCount(dev.getDevStatCount());
					vbs.setLastUpdTS(dev.getDeviceStatusTS());
					LOGGER.info("VBS device created", vbs);
					LOGGER.info("iOS device ID : {}", vbs.getDevId());
					dbo.insertVbsDevicesHistory(conn, vbs);
					break;
				default:
					break;
			}
		} catch (SQLException e) {
			LOGGER.error("SQL Error", e);
			e.printStackTrace();
		}
	}

	private String getNetworkName(ResultSet rs, DeviceType deviceType) throws SQLException {
		switch (deviceType) {
			case IOS:
				return rs.getString(Cols.NETWORK_NAME.toString());
			case VBS:
				return "";
			default:
				return null;
		}
	}

	private Integer getDispStatus(ResultSet rs, DeviceType deviceType) throws SQLException {
		switch (deviceType) {
			case IOS:
				return 0;
			case VBS:
				return rs.getInt(Cols.DISPLAY_STATUS.toString());
			default:
				return null;
		}
	}
}
