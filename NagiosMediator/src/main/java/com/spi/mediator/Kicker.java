package com.spi.mediator;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration.CompositeConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spi.dao.DisDataSource;

public class Kicker {

	protected static final Logger LOGGER = LoggerFactory.getLogger(Kicker.class);

	public static void main(String[] args) throws Exception {
		LOGGER.info("Instantiating JETTY instance...");
		CompositeConfiguration config = Preferences.loadAllParameters();

		String url = config.getString(Preferences.MYSQL_JDBC_URL);
		String user = config.getString(Preferences.MYSQL_USER);
		String pass = config.getString(Preferences.MYSQL_PASS);

		Connection conn = DisDataSource.getDs(url, user, pass).getConnection();

		int jettyPort = Integer.parseInt(config.getString(Preferences.JETTY_PORT));
		Server server = new Server(jettyPort);

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);

		ServletHolder holder = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
		holder.setInitOrder(0);
		Map<String, String> initMap = new HashMap<String, String>();
		initMap.put("jersey.config.server.provider.packages", "com.spi.mediator.ws_v1; org.codehaus.jackson.jaxrs");
		initMap.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
		holder.setInitParameters(initMap);

		context.setAttribute("mysqlConnection", conn);
		context.addEventListener(new Poller(conn));

		// Add Dashboard
		// ServletHolder dashHolder =
		// context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class,
		// "/dashboard/*");
		// dashHolder.setInitOrder(1);
		// context.setInitParameter("contextConfigLocation",
		// "classpath*:**/web.xml");

		// Tells the Jersey Servlet which REST service/class to load.
		// servlet.setInitParameter("jersey.config.server.provider.classnames",
		// EntryPoint.class.getCanonicalName());

		try {
			//server.se
			//server.setStopTimeout(10000);
			server.setStopAtShutdown(true);
			server.start();
			server.join();
		} finally {
			conn.close();
			server.stop();
			server.destroy();
		}
	}
}