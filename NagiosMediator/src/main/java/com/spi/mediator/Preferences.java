package com.spi.mediator;

import javax.sql.DataSource;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.DatabaseConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spi.dao.DisDataSource;

public class Preferences extends PropertiesConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(Preferences.class);

	// properties files
	public static final String USER_SETTINGS = "user.settings.properties";
	public static final String APP_NAME = "NAGIOSMEDIATOR";

	public static final String THRESHOLD_DURATION = "THRESHOLD_DURATION";
	public static final String MARGIN_DURATION = "MARGIN_DURATION";
	public static final String POLLER_TIME = "POLLER_TIME";
	// nagios key names
	public static final String NRDP_URL = "NRDP_URL";
	public static final String NRDP_TOKEN = "NRDP_TOKEN";
	public static final String NRDP_TIMEOUT = "NRDP_TIMEOUT";
	public static final String NRDP_SERVICE_IOS = "NRDP_SERVICE_IOS";
	public static final String NRDP_SERVICE_VBS = "NRDP_SERVICE_VBS";
	// web service key names
	public static final String WS_ID = "deviceId";
	public static final String WS_TOKEN = "deviceToken";
	public static final String WS_NAME = "deviceName";
	public static final String WS_NETWORK = "deviceNetwork";
	public static final String WS_STATUS = "displayStatus";
	// we server port
	public static final String JETTY_PORT = "JETTY_PORT";
	// mysql key names
	public static final String MYSQL_JDBC_URL = "MYSQL_JDBC_URL";
	public static final String MYSQL_USER = "MYSQL_USER";
	public static final String MYSQL_PASS = "MYSQL_PASS";
	// configuration table key names
	public static final String CFG_TABLE = "TBL_CONFIG";
	public static final String CFG_KEY = "CFG_KEY";
	public static final String CFG_VAL = "CFG_VAL";
	public static final String CFG_NAME_COL = "AGENT_TYPE";
	public static final String CFG_NAME_VBS = "VBS";
	public static final String CFG_NAME_IOS = "IOS";
	public static final String CFG_NAME_MED = "MED";
	public static final String VBS_WARN_LIMIT = "VBS_WARN_LIMIT";
	public static final String IOS_WARN_LIMIT = "IOS_WARN_LIMIT";

	// configuration holder
	private static CompositeConfiguration config = new CompositeConfiguration();

	public Preferences() {
		super();
	}

	public static CompositeConfiguration getAllConfigs(Configuration usrCfg) {
		CompositeConfiguration cfg = new CompositeConfiguration();
		String url = usrCfg.getString(MYSQL_JDBC_URL);
		String user = usrCfg.getString(MYSQL_USER);
		String pass = usrCfg.getString(MYSQL_PASS);
		LOGGER.info("DB Parameters >> {} ## {} ## {}", url, user, pass);

		DataSource ds = DisDataSource.getDs(url, user, pass);
		cfg.addConfiguration(usrCfg);
		DatabaseConfiguration dbCfgCv = new DatabaseConfiguration(ds, CFG_TABLE, CFG_NAME_COL, CFG_KEY, CFG_VAL, CFG_NAME_VBS);
		cfg.addConfiguration(dbCfgCv);
		DatabaseConfiguration dbCfgIos = new DatabaseConfiguration(ds, CFG_TABLE, CFG_NAME_COL, CFG_KEY, CFG_VAL, CFG_NAME_IOS);
		cfg.addConfiguration(dbCfgIos);
		DatabaseConfiguration dbCfgMed = new DatabaseConfiguration(ds, CFG_TABLE, CFG_NAME_COL, CFG_KEY, CFG_VAL, CFG_NAME_MED);
		cfg.addConfiguration(dbCfgMed);
		return cfg;
	}

	public static CompositeConfiguration loadAllParameters() {
		try {
			Configuration usrCfg = new PropertiesConfiguration(USER_SETTINGS);
			config = getAllConfigs(usrCfg);
			LOGGER.debug("Loaded system parameter using : {} & {} table", USER_SETTINGS, CFG_TABLE);
		} catch (ConfigurationException e) {
			e.printStackTrace(System.out);
			System.exit(0);
		}
		return config;
	}

}
