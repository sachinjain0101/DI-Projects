package com.spi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spi.model.DeviceType;
import com.spi.model.VbsDevicesHistory;
import com.spi.model.VbsDevicesRegister;
import com.spi.model.IosDevicesHistory;
import com.spi.model.IosDevicesRegister;
import com.spi.model.TableType;

public class DbOps {
	private static final Logger LOGGER = LoggerFactory.getLogger(DbOps.class);

	private static final String DB = "DIS_MONITORING";
	private static final String DOT = ".";
	public static final String TBL_IOS_REG = DB + DOT + "TBL_IOS_DEVICES_REGISTER";
	public static final String TBL_VBS_REG = DB + DOT + "TBL_VBS_DEVICES_REGISTER";
	public static final String TBL_IOS_HIST = DB + DOT + "TBL_IOS_DEVICES_HISTORY";
	public static final String TBL_VBS_HIST = DB + DOT + "TBL_VBS_DEVICES_HISTORY";

	public static final String IOS_CHECK_TYPE = "HEART-BEAT";
	public static final String DIS_CHECK_TYPE = "CONTENT-VERIFICATION";

	public static final String TBL_IOS_REG_COLS = "DEVICE_ID,DEVICE_NAME,DEVICE_TOKEN,NETWORK_NAME,LAST_UPDATE_TIMESTAMP";
	public static final String TBL_IOS_HIST_COLS = "DEVICE_ID,DEVICE_NAME,NETWORK_NAME,CHECK_TYPE,LAST_STATUS,LAST_UPDATE_TIMESTAMP,STATUS_COUNT";
	public static final String TBL_VBS_REG_COLS = "DEVICE_ID,DEVICE_NAME,DISPLAY_STATUS,LAST_UPDATE_TIMESTAMP";
	public static final String TBL_VBS_HIST_COLS = "DEVICE_ID,DEVICE_NAME,CHECK_TYPE,LAST_STATUS,DISPLAY_STATUS,LAST_UPDATE_TIMESTAMP,STATUS_COUNT";

	private static final String INSERT_STMT = "insert into %s ( %s ) values ( %s ) ; ";

	private static Timestamp getCurrentTimeStamp() {
		java.util.Date today = new java.util.Date();
		return new Timestamp(today.getTime());
	}

	public boolean insertIosDevicesRegister(Connection conn, IosDevicesRegister iosDeviceRegister) throws SQLException {
		String fromColumns = " ? , ? , ? , ? , ? ";
		String insertStmt = String.format(INSERT_STMT, TBL_IOS_REG, TBL_IOS_REG_COLS, fromColumns);
		LOGGER.info(">>> INSERT STMT: {}", insertStmt);
		PreparedStatement prepStmt = conn.prepareStatement(insertStmt);
		prepStmt.setString(1, iosDeviceRegister.getDevId());
		prepStmt.setString(2, iosDeviceRegister.getDevName());
		prepStmt.setString(3, iosDeviceRegister.getDevToken());
		prepStmt.setString(4, iosDeviceRegister.getNetworkName());
		prepStmt.setTimestamp(5, getCurrentTimeStamp());
		// execute insert SQL stetement
		int rowsChanged = prepStmt.executeUpdate();
		LOGGER.info("Done with insertion into {}!", TBL_IOS_REG);
		return (rowsChanged == 0) ? false : true;
	}

	public boolean insertIosDevicesHistory(Connection conn, IosDevicesHistory iosDeviceHistory) throws SQLException {
		String fromColumns = " ? , ? , ? , ? , ? , ? , ? ";
		String insertStmt = String.format(INSERT_STMT, TBL_IOS_HIST, TBL_IOS_HIST_COLS, fromColumns);
		LOGGER.info(">>> INSERT STMT: {}", insertStmt);
		PreparedStatement prepStmt = conn.prepareStatement(insertStmt);
		prepStmt.setString(1, iosDeviceHistory.getDevId());
		prepStmt.setString(2, iosDeviceHistory.getDevName());
		prepStmt.setString(3, iosDeviceHistory.getNetworkName());
		prepStmt.setString(4, IOS_CHECK_TYPE);
		prepStmt.setString(5, iosDeviceHistory.getLstStat());
		prepStmt.setTimestamp(6, getCurrentTimeStamp());
		prepStmt.setInt(7, iosDeviceHistory.getStatCount());
		// execute insert SQL stetement
		prepStmt.executeUpdate();
		LOGGER.info("Done with insertion into {}!", TBL_IOS_HIST);
		return true;
	}

	public boolean insertVbsDevicesRegister(Connection conn, VbsDevicesRegister vbsDeviceRegister) throws SQLException {
		String fromColumns = " ? , ? , ? , ? ";
		String insertStmt = String.format(INSERT_STMT, TBL_VBS_REG, TBL_VBS_REG_COLS, fromColumns);
		LOGGER.info(">>> INSERT STMT: {}", insertStmt);
		PreparedStatement prepStmt = conn.prepareStatement(insertStmt);
		prepStmt.setString(1, vbsDeviceRegister.getDevId());
		prepStmt.setString(2, vbsDeviceRegister.getDevName());
		prepStmt.setInt(3, vbsDeviceRegister.getDisplayStatus());
		prepStmt.setTimestamp(4, getCurrentTimeStamp());
		// execute insert SQL stetement
		int rowsChanged = prepStmt.executeUpdate();
		LOGGER.info("Done with insertion into {}!", TBL_VBS_REG);
		return (rowsChanged == 0) ? false : true;
	}

	public boolean insertVbsDevicesHistory(Connection conn, VbsDevicesHistory vbsDeviceHistory) throws SQLException {
		String fromColumns = " ? , ? , ? , ? , ? , ? , ? ";
		String insertStmt = String.format(INSERT_STMT, TBL_VBS_HIST, TBL_VBS_HIST_COLS, fromColumns);
		LOGGER.info(insertStmt);
		PreparedStatement prepStmt = conn.prepareStatement(insertStmt);
		prepStmt.setString(1, vbsDeviceHistory.getDevId());
		prepStmt.setString(2, vbsDeviceHistory.getDevName());
		prepStmt.setString(3, DIS_CHECK_TYPE);
		prepStmt.setString(4, vbsDeviceHistory.getLstStat());
		prepStmt.setInt(5,vbsDeviceHistory.getDisplayStatus());
		prepStmt.setTimestamp(6, getCurrentTimeStamp());
		prepStmt.setInt(7, vbsDeviceHistory.getStatCount());
		// execute insert SQL stetement
		prepStmt.executeUpdate();
		LOGGER.info("Done with insertion into {}!", TBL_VBS_HIST);
		return true;
	}

	public ResultSet execDevicesSql(Connection conn, String sqlStmt, DeviceType devType) throws SQLException {
		switch (devType) {
			case IOS:
				sqlStmt = " SELECT DEVICE_ID,DEVICE_NAME,DEVICE_TOKEN,NETWORK_NAME,MAX(LAST_UPDATE_TIMESTAMP) AS LAST_UPDATE_TIMESTAMP " + " FROM "
						+ DbOps.TBL_IOS_REG + " GROUP BY DEVICE_ID, DEVICE_NAME, DEVICE_TOKEN, NETWORK_NAME ";
				break;
			case VBS:
				sqlStmt = " SELECT DEVICE_ID,DEVICE_NAME,MAX(LAST_UPDATE_TIMESTAMP) AS LAST_UPDATE_TIMESTAMP " + " FROM " + DbOps.TBL_VBS_REG
						+ " GROUP BY DEVICE_ID, DEVICE_NAME ";
				break;
			default:
				break;
		}
		PreparedStatement statement = conn.prepareStatement(sqlStmt);
		return statement.executeQuery(sqlStmt);
	}

	public ResultSet execAllDevicesSql(Connection conn) throws SQLException {
		String sqlStmt = " SELECT DISTINCT DEVICE_ID AS DEVICE_ID, '" + DeviceType.IOS.toString() + "' AS DEVICE_TYPE FROM " + TBL_IOS_REG + " "
				+ " UNION " + " SELECT DISTINCT DEVICE_ID AS DEVICE_ID, '" + DeviceType.VBS.toString() + "' AS DEVICE_TYPE FROM " + TBL_VBS_REG + " ";
		LOGGER.info(">>> SELECT STMT: {}", sqlStmt);
		PreparedStatement statement = conn.prepareStatement(sqlStmt);
		return statement.executeQuery(sqlStmt);
	}

	public ResultSet execDevicesSql(Connection conn, DeviceType devType) throws SQLException {
		String sqlStmt = " SELECT DISTINCT DEVICE_ID AS DEVICE_ID FROM %s; ";
		String tableName = "";
		switch (devType) {
			case VBS:
				tableName = TBL_VBS_REG;
				break;
			case IOS:
				tableName = TBL_IOS_REG;
				break;
			default:
				break;
		}
		sqlStmt = String.format(sqlStmt, tableName);
		LOGGER.info(">>> SELECT STMT: {}", sqlStmt);
		PreparedStatement statement = conn.prepareStatement(sqlStmt);
		return statement.executeQuery(sqlStmt);
	}

	public ResultSet execDeviceDtlsSql(Connection conn, String deviceId, Integer limit, DeviceType devType, TableType tblType) throws SQLException {
		String sqlStmt = " SELECT %s FROM %s WHERE DEVICE_ID = '%s' ORDER BY LAST_UPDATE_TIMESTAMP DESC LIMIT %s; ";
		String colStr = "";
		String tableName = "";

		switch (devType) {
			case VBS:
				switch (tblType) {
					case HISTORY:
						tableName = TBL_VBS_HIST;
						colStr = TBL_VBS_HIST_COLS;
						break;
					case REGISTER:
						tableName = TBL_VBS_REG;
						colStr = TBL_VBS_REG_COLS;
						break;
					default:
						break;
				}
				break;
			case IOS:
				switch (tblType) {
					case HISTORY:
						tableName = TBL_IOS_HIST;
						colStr = TBL_IOS_HIST_COLS;
						break;
					case REGISTER:
						tableName = TBL_IOS_REG;
						colStr = TBL_IOS_REG_COLS;
						break;
					default:
						break;
				}
				break;
			default:
				break;
		}
		sqlStmt = String.format(sqlStmt, colStr, tableName, deviceId, limit);
		LOGGER.info(">>> SELECT STMT: {}", sqlStmt);
		PreparedStatement statement = conn.prepareStatement(sqlStmt);
		return statement.executeQuery(sqlStmt);
	}
}
