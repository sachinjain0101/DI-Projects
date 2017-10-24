package com.spi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spi.model.DeviceType;
import com.spi.model.DevicesHistory;
import com.spi.model.DevicesRegister;
import com.spi.model.VbsDevicesHistory;
import com.spi.model.VbsDevicesRegister;
import com.spi.model.IosDevicesHistory;
import com.spi.model.IosDevicesRegister;
import com.spi.model.TableType;

public class DbHelper {
	protected static final Logger LOGGER = LoggerFactory.getLogger(DbHelper.class);
	private static final int LIMIT = 10;
	private String sqlStmt = "";

	private DbOps dbo;

	public DbHelper() {
		this.dbo = new DbOps();
	}

	@SuppressWarnings("unchecked")
	public <T extends DevicesHistory> List<T> getDeviceHistory(String deviceId, Connection conn, DeviceType devType) throws SQLException {

		List<T> lstHist = new ArrayList<T>();
		ResultSet rs = dbo.execDeviceDtlsSql(conn, deviceId, LIMIT, devType, TableType.HISTORY);

		switch (devType) {
			case VBS:
				while (rs.next()) {
					VbsDevicesHistory device = new VbsDevicesHistory();
					device.setDevId(deviceId);
					device.setDevName(rs.getString("DEVICE_NAME"));
					device.setChkType(rs.getString("CHECK_TYPE"));
					device.setLstStat(rs.getString("LAST_STATUS"));
					device.setLastUpdTS(rs.getTimestamp("LAST_UPDATE_TIMESTAMP"));
					device.setStatCount(rs.getInt("STATUS_COUNT"));
					lstHist.add((T) device);
				}
				break;
			case IOS:
				while (rs.next()) {
					IosDevicesHistory device = new IosDevicesHistory();
					device.setDevId(deviceId);
					device.setDevName(rs.getString("DEVICE_NAME"));
					device.setNetworkName(rs.getString("NETWORK_NAME"));
					device.setChkType(rs.getString("CHECK_TYPE"));
					device.setLstStat(rs.getString("LAST_STATUS"));
					device.setLastUpdTS(rs.getTimestamp("LAST_UPDATE_TIMESTAMP"));
					device.setStatCount(rs.getInt("STATUS_COUNT"));
					lstHist.add((T) device);
				}
				break;
			default:
				break;
		}
		return lstHist;
	}

	@SuppressWarnings("unchecked")
	public <T extends DevicesRegister> List<T> getDevices(Connection conn, DeviceType devType) throws SQLException {
		ResultSet rs;
		List<T> lstDevs = new ArrayList<T>();
		rs = dbo.execDevicesSql(conn, sqlStmt, devType);
		switch (devType) {
			case IOS:
				while (rs.next()) {
					IosDevicesRegister device = new IosDevicesRegister();
					device.setDevId(rs.getString(1));
					device.setDevName(rs.getString(2));
					device.setDevToken(rs.getString(3));
					device.setNetworkName(rs.getString(4));
					device.setLastUpdTS(rs.getTimestamp(5));
					lstDevs.add((T) device);
				}
				break;
			case VBS:
				while (rs.next()) {
					VbsDevicesRegister device = new VbsDevicesRegister();
					device.setDevId(rs.getString(1));
					device.setDevName(rs.getString(2));
					device.setLastUpdTS(rs.getTimestamp(3));
					lstDevs.add((T) device);
				}
				break;
			default:
				break;
		}
		return lstDevs;
	}

	public boolean insertiOSMsgToDb(Connection conn, IosDevicesRegister newEntry) throws SQLException {
		return dbo.insertIosDevicesRegister(conn, newEntry);
	}

	public boolean insertDisMsgToDb(Connection conn, VbsDevicesRegister newEntry) throws SQLException {
		return dbo.insertVbsDevicesRegister(conn, newEntry);
	}

}
