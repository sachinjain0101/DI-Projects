package com.spi.model;

import java.util.Date;

public class VbsDevicesHistory extends DevicesHistory {

	private int displayStatus;

	public VbsDevicesHistory() {
		super();
	}

	public VbsDevicesHistory(String devId, String devName, String chkType, String lstStat, Date lastUpdTS, int statCount) {
		super(devId, devName, chkType, lstStat, lastUpdTS, statCount);
	}

	public VbsDevicesHistory(String devId, String devName, String chkType, String lstStat, Date lastUpdTS, int statCount, int displayStatus) {
		super(devId, devName, chkType, lstStat, lastUpdTS, statCount);
		this.displayStatus = displayStatus;
	}

	public int getDisplayStatus() {
		return displayStatus;
	}

	public void setDisplayStatus(int displayStatus) {
		this.displayStatus = displayStatus;
	}

}
