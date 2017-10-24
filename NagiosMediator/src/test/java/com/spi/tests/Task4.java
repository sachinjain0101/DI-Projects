package com.spi.tests;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.configuration.CompositeConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spi.dao.DisDataSource;
import com.spi.mediator.Preferences;
import com.spi.services.ProbeDb;

public class Task4 {

	protected static final Logger LOGGER = LoggerFactory.getLogger(Task4.class);

	public static void main(String[] args) throws SQLException {
		System.out.println("Hi");
		LOGGER.info("Instantiating JETTY instance...");
		CompositeConfiguration config = Preferences.loadAllParameters();

		String url = config.getString(Preferences.MYSQL_JDBC_URL);
		String user = config.getString(Preferences.MYSQL_USER);
		String pass = config.getString(Preferences.MYSQL_PASS);

		Connection conn = DisDataSource.getDs(url, user, pass).getConnection();

		ProbeDb pd = new ProbeDb(conn, config);
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(pd, 0, 1, TimeUnit.MINUTES);
	}

}
