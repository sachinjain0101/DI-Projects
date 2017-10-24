package com.spi.dao;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DisDataSource {
	private static final Logger LOGGER = LoggerFactory.getLogger(DisDataSource.class);
	private static MysqlDataSource ds;

	public static DataSource getDs(String url, String user, String pass) {
		LOGGER.info("Getting MySQL DataSource");
		ds = new MysqlDataSource();
		ds.setURL(url);
		ds.setUser(user);
		ds.setPassword(pass);
		return ds;
	}
}
