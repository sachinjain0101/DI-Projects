package com.spi.model;

import java.util.Date;

public class IosDevicesRegister extends DevicesRegister {

	public IosDevicesRegister() {
		super();
	}

	public IosDevicesRegister(String devId, String devName, Date lastUpdTS) {
		super(devId, devName, lastUpdTS);
	}

	public IosDevicesRegister(String devId, String devName, Date lastUpdTS, String devToken, String networkName) {
		super(devId, devName, lastUpdTS);
		this.devToken = devToken;
		this.networkName = networkName;
	}

	private String devToken;
	private String networkName;

	public String getDevToken() {
		return devToken;
	}

	public void setDevToken(String devToken) {
		this.devToken = devToken;
	}

	public String getNetworkName() {
		return networkName;
	}

	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}

}
