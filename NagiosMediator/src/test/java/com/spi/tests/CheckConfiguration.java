package com.spi.tests;

import java.util.Iterator;

import org.apache.commons.configuration.CompositeConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spi.mediator.Preferences;

public class CheckConfiguration {
	protected static final Logger LOGGER = LoggerFactory.getLogger(CheckConfiguration.class);

	public static void main(String[] args) {
		// Connection conn = VbsDataSource.getDs(url, user, pass)

		CompositeConfiguration config = Preferences.loadAllParameters();
		Iterator<String> itr = config.getKeys();
		while (itr.hasNext()) {
			String key = itr.next();
			LOGGER.info("{} ### {}", key, config.getString(key));
		}
		// LOGGER.info(config.toString());

	}

}
