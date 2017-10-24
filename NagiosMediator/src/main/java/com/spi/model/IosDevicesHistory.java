package com.spi.model;

import java.util.Date;

public class IosDevicesHistory extends DevicesHistory {
	private String networkName;

	public IosDevicesHistory(String networkName) {
		super();
		this.networkName = networkName;
	}

	public IosDevicesHistory() {
		super();
	}

	public IosDevicesHistory(String devId, String devName, String chkType, String lstStat, Date lastUpdTS, int warningCount) {
		super(devId, devName, chkType, lstStat, lastUpdTS, warningCount);
	}

	public String getNetworkName() {
		return networkName;
	}

	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}

}
