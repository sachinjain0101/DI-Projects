package com.spi.mediator.ws_v1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.eclipse.jetty.http.HttpStatus;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spi.dao.DbHelper;
import com.spi.mediator.Preferences;
import com.spi.model.DeviceType;
import com.spi.model.DevicesRegister;
import com.spi.model.VbsDevicesHistory;
import com.spi.model.VbsDevicesRegister;
import com.spi.model.IosDevicesHistory;
import com.spi.model.IosDevicesRegister;

@Path("/mediator")
public class Mediator extends ResourceConfig {

	protected static final Logger LOGGER = LoggerFactory.getLogger(Mediator.class);

	@Context
	ServletContext context;

	public Mediator(@Context ServletContext context) {
		this.context = context;
	}

	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		return "Test";
	}

	@GET
	@Path("/vbs/devices")
	@Produces(MediaType.APPLICATION_JSON)
	public List<? extends DevicesRegister> getDisDevices() {
		Connection conn = (Connection) context.getAttribute("mysqlConnection");
		DbHelper dbHelper = new DbHelper();
		try {
			return dbHelper.getDevices(conn, DeviceType.VBS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@GET
	@Path("/ios/devices")
	@Produces(MediaType.APPLICATION_JSON)
	public List<? extends DevicesRegister> getIosDevices() {
		Connection conn = (Connection) context.getAttribute("mysqlConnection");
		DbHelper dbHelper = new DbHelper();
		try {
			return dbHelper.getDevices(conn, DeviceType.IOS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@GET
	@Path("/ios/push")
	@Produces(MediaType.TEXT_XML)
	public Response getIosMessage(@Context UriInfo info) {

		Connection conn = (Connection) context.getAttribute("mysqlConnection");
		String deviceId = info.getQueryParameters().getFirst(Preferences.WS_ID);
		String deviceToken = info.getQueryParameters().getFirst(Preferences.WS_TOKEN);
		String deviceName = info.getQueryParameters().getFirst(Preferences.WS_NAME);
		String deviceNetwork = info.getQueryParameters().getFirst(Preferences.WS_NETWORK);

		IosDevicesRegister newEntry = new IosDevicesRegister();
		newEntry.setDevId(deviceId);
		newEntry.setDevName(deviceName);
		newEntry.setDevToken(deviceToken);
		newEntry.setNetworkName(deviceNetwork);

		DbHelper dbHelper = new DbHelper();
		try
		{
			return ResponseHelper.buildResponse(dbHelper.insertiOSMsgToDb(conn, newEntry), DeviceType.IOS, newEntry);
		}
		catch (SQLException e)
		{
			LOGGER.error("Error servicing request", e);
			return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).entity("Unable to comply :(").build();
		}
	}

	@GET
	@Path("/vbs/push")
	@Produces(MediaType.TEXT_XML)
	public Response getDisMessage(@Context UriInfo info) {

		Connection conn = (Connection) context.getAttribute("mysqlConnection");
		String deviceId = info.getQueryParameters().getFirst(Preferences.WS_ID);
		String deviceName = info.getQueryParameters().getFirst(Preferences.WS_NAME);
		int displayStatus = Integer.parseInt(info.getQueryParameters().getFirst(Preferences.WS_STATUS));

		VbsDevicesRegister newEntry = new VbsDevicesRegister();
		newEntry.setDevId(deviceId);
		newEntry.setDevName(deviceName);
		newEntry.setDisplayStatus(displayStatus);

		DbHelper dbHelper = new DbHelper();
		try
		{
			return ResponseHelper.buildResponse(dbHelper.insertDisMsgToDb(conn, newEntry), DeviceType.VBS, newEntry);
		}
		catch (SQLException e)
		{
			LOGGER.error("Error servicing request", e);
			return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).entity("Unable to comply :(").build();
		}
	}

	@GET
	@Path("/ios/deviceHistory")
	public List<IosDevicesHistory> getIosDeviceHistory(@Context UriInfo info) {
		Connection conn = (Connection) context.getAttribute("mysqlConnection");
		String deviceId = info.getQueryParameters().getFirst(Preferences.WS_ID);
		DbHelper dbHelper = new DbHelper();
		try {
			return dbHelper.getDeviceHistory(deviceId, conn, DeviceType.IOS);
		} catch (SQLException e) {
			LOGGER.error("Error accessing iOS device history", e);
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/vbs/deviceHistory")
	public List<VbsDevicesHistory> getDisDeviceHistory(@Context UriInfo info) {
		Connection conn = (Connection) context.getAttribute("mysqlConnection");
		String deviceId = info.getQueryParameters().getFirst(Preferences.WS_ID);
		DbHelper dbHelper = new DbHelper();
		try {
			return dbHelper.getDeviceHistory(deviceId, conn, DeviceType.VBS);
		} catch (SQLException e) {
			LOGGER.error("Error accessing display device history", e);
			e.printStackTrace();
			return null;
		}
	}
}