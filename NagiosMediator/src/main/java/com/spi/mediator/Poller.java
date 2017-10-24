package com.spi.mediator;

import java.sql.Connection;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.configuration.CompositeConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.datastax.driver.core.Session;
import com.spi.services.ProbeDb;
import com.spi.mediator.Preferences;

public class Poller implements ServletContextListener {

	protected static final Logger LOGGER = LoggerFactory.getLogger(Poller.class);
	private Connection conn;

	public Poller(Connection conn) {
		this.conn = conn;
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		LOGGER.info("Closing = {}", Poller.class.getCanonicalName());
	}

	public void contextInitialized(ServletContextEvent arg0) {
		LOGGER.info("Initiating = {}", Poller.class.getCanonicalName());
		CompositeConfiguration config = Preferences.loadAllParameters();
		Long pollerTime = Long.parseLong(config.getString(Preferences.POLLER_TIME));
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(new ProbeDb(conn, config), 0, pollerTime, TimeUnit.MILLISECONDS);
	}

}
