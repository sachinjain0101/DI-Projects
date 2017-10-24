package com.spi.tests;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration.CompositeConfiguration;
import org.junit.Before;
import org.junit.Test;

import com.spi.dao.DisDataSource;
import com.spi.mediator.Preferences;

@SuppressWarnings("unused")
public class DbOpsTest {
	CompositeConfiguration config;
	Connection conn;

	@Before
	public void setup() throws SQLException {
		config = Preferences.loadAllParameters();

		String url = config.getString(Preferences.MYSQL_JDBC_URL);
		String user = config.getString(Preferences.MYSQL_USER);
		String pass = config.getString(Preferences.MYSQL_PASS);

		Connection conn = DisDataSource.getDs(url, user, pass).getConnection();

	}

	@Test
	public void cassaTest1() {

		String deviceId = "sachin111";
		String deviceToken = "nitin";
		Date currentDate = new Date();
		Long deviceLogTS = currentDate.getTime();

		Map<String, String> deviceMiscData = new HashMap<String, String>();
		deviceMiscData.put(Preferences.WS_NAME, "xxxxxxxxxxxxxxxxxxx");
		deviceMiscData.put(Preferences.WS_NETWORK, "yyyyyyyyyyyyyyyyy");

	}

	@Test
	public void cassaTest2() {

		String deviceId = "Mamtha11";
		String deviceName = "Mamtha11";
		Date currentDate = new Date();
		Long deviceLogTS = currentDate.getTime();
		int deviceStatus = 1;

		Map<String, String> deviceMiscData = new HashMap<String, String>();
		deviceMiscData.put(Preferences.WS_NAME, "xxxxxxxxxxxxxxxxxxx");
		deviceMiscData.put(Preferences.WS_NETWORK, "SPI-GLOBAL-WIRELESS");

	}
}
