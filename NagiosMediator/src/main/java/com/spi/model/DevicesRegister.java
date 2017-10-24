package com.spi.model;

import java.util.Date;

public abstract class DevicesRegister {
	private String devId;
	private String devName;
	private Date lastUpdTS;

	public DevicesRegister() {}

	public DevicesRegister(String devId, String devName, Date lastUpdTS) {
		this.devId = devId;
		this.devName = devName;
		this.lastUpdTS = lastUpdTS;
	}

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public Date getLastUpdTS() {
		return lastUpdTS;
	}

	public void setLastUpdTS(Date lastUpdTS) {
		this.lastUpdTS = lastUpdTS;
	}

}
