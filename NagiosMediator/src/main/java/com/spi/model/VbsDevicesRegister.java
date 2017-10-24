package com.spi.model;

import java.util.Date;

public class VbsDevicesRegister extends DevicesRegister {

	private int displayStatus;
	
	public VbsDevicesRegister() {
		super();
	}

	public VbsDevicesRegister(String devId, String devName, Date lastUpdTS) {
		super(devId, devName, lastUpdTS);
	}
	
	public VbsDevicesRegister(String devId, String devName, int displayStatus, Date lastUpdTS) {
		super(devId, devName, lastUpdTS);
		this.displayStatus = displayStatus;
	}

	public int getDisplayStatus()
	{
		return displayStatus;
	}
	public void setDisplayStatus(int displayStatus)
	{
		this.displayStatus = displayStatus;
	}

}
