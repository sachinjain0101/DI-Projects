package com.spi.mediator.ws_v1;

import javax.ws.rs.core.Response;

import org.eclipse.jetty.http.HttpStatus;

import com.spi.model.DeviceType;
import com.spi.model.DevicesRegister;

public class ResponseHelper
{
	private static final String XML_SET_VERSION = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

	private static final String VBS_MSG_OPEN = "<VbsMessageStatus>";
	private static final String VBS_MSG_CLOSE = "</VbsMessageStatus>";
	private static final String IOS_MSG_OPEN = "<IosMessageStatus>";
	private static final String IOS_MSG_CLOSE = "</IosMessageStatus>";

	private static final String STATUS_OPEN = "<dataInsertStatus>";
	private static final String STATUS_CLOSE = "</dataInsertStatus>";

	private static final String DEV_ID_OPEN = "<deviceId>";
	private static final String DEV_ID_CLOSE = "</deviceId>";

	private static final String DEV_NAME_OPEN = "<deviceName>";
	private static final String DEV_NAME_CLOSE = "</deviceName>";

	public static <T extends DevicesRegister> Response buildResponse(boolean dbResponse, DeviceType deviceType, T newEntry)
	{
		StringBuilder sb = new StringBuilder(XML_SET_VERSION);
		switch (deviceType)
		{
			case IOS:
				sb.append(IOS_MSG_OPEN);
				break;
			case VBS:
				sb.append(VBS_MSG_OPEN);
				break;
		}
		sb.append(STATUS_OPEN).append(dbResponse).append(STATUS_CLOSE).append(DEV_ID_OPEN).append(newEntry.getDevId())
				.append(DEV_ID_CLOSE).append(DEV_NAME_OPEN).append(newEntry.getDevName()).append(DEV_NAME_CLOSE);
		switch (deviceType)
		{
			case IOS:
				sb.append(IOS_MSG_CLOSE);
				break;
			case VBS:
				sb.append(VBS_MSG_CLOSE);
				break;
		}
		if (dbResponse)
			return Response.status(HttpStatus.CREATED_201).entity(sb.toString()).build();
		else
			return Response.status(HttpStatus.NOT_MODIFIED_304).entity(sb.toString()).build();	
	}
}
