package com.spi.model;

import java.util.Date;

public abstract class DevicesHistory {
	private String devId;
	private String devName;
	private String chkType;
	private String lstStat;
	private Date lastUpdTS;
	private int statCount;

	public DevicesHistory() {}

	public DevicesHistory(String devId, String devName, String chkType, String lstStat, Date lastUpdTS, int statCount) {
		this.devId = devId;
		this.devName = devName;
		this.chkType = chkType;
		this.lstStat = lstStat;
		this.lastUpdTS = lastUpdTS;
		this.statCount = statCount;
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

	public String getChkType() {
		return chkType;
	}

	public void setChkType(String chkType) {
		this.chkType = chkType;
	}

	public String getLstStat() {
		return lstStat;
	}

	public void setLstStat(String lstStat) {
		this.lstStat = lstStat;
	}

	public Date getLastUpdTS() {
		return lastUpdTS;
	}

	public void setLastUpdTS(Date lastUpdTS) {
		this.lastUpdTS = lastUpdTS;
	}

	public int getStatCount() {
		return statCount;
	}

	public void setStatCount(int statCount) {
		this.statCount = statCount;
	}

}
