package com.spi.tests;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.configuration.CompositeConfiguration;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spi.dao.DbHelper;
import com.spi.dao.DisDataSource;
import com.spi.mediator.Preferences;
import com.spi.model.DeviceType;
import com.spi.model.VbsDevicesRegister;
import com.spi.model.IosDevicesRegister;

public class AnotherDbTest {

	protected static final Logger LOGGER = LoggerFactory.getLogger(AnotherDbTest.class);
	
	@Test
	public void test1() throws SQLException{
		DbHelper dh = new DbHelper();
		CompositeConfiguration config = Preferences.loadAllParameters();

		String url = config.getString(Preferences.MYSQL_JDBC_URL);
		String user = config.getString(Preferences.MYSQL_USER);
		String pass = config.getString(Preferences.MYSQL_PASS);		

		Connection conn = DisDataSource.getDs(url, user, pass).getConnection();
		
		List<VbsDevicesRegister> lstDdr = dh.getDevices(conn, DeviceType.VBS);		
		for(VbsDevicesRegister ddr : lstDdr){
			LOGGER.info("{}",ddr.getDevId());
			LOGGER.info("{}",ddr.getLastUpdTS());
		}
		
		List<IosDevicesRegister> lstIdr = dh.getDevices(conn, DeviceType.IOS);		
		for(IosDevicesRegister idr : lstIdr){
			LOGGER.info("{}",idr.getDevId());
			LOGGER.info("{}",idr.getLastUpdTS());
		}
	}
}
