package com.spi.model;

import java.util.Date;

import ch.shamu.jsendnrdp.domain.State;

public class Device {
	DeviceType deviceType;
	String deviceId;
	String deviceName;
	State devLstState;
	String devNetworkName;
	int devStatCount;
	Integer dispStatus;
	Date deviceStatusTS;

	public Device() {}

	public Device(DeviceType deviceType, String deviceId, String deviceName, State devLstState, String devNetworkName, int devStatCount, Date deviceStatusTS) {
		this.deviceType = deviceType;
		this.deviceId = deviceId;
		this.deviceName = deviceName;
		this.devLstState = devLstState;
		this.devNetworkName = devNetworkName;
		this.devStatCount = devStatCount;
		this.deviceStatusTS = deviceStatusTS;
	}

	public Device(DeviceType deviceType, String deviceId, String deviceName, State devLstState, String devNetworkName, int devStatCount,
			Integer dispStatus, Date deviceStatusTS) {
		this.deviceType = deviceType;
		this.deviceId = deviceId;
		this.deviceName = deviceName;
		this.devLstState = devLstState;
		this.devNetworkName = devNetworkName;
		this.devStatCount = devStatCount;
		this.dispStatus = dispStatus;
		this.deviceStatusTS = deviceStatusTS;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public State getDevLstState() {
		return devLstState;
	}

	public String getDevNetworkName() {
		return devNetworkName;
	}

	public int getDevStatCount() {
		return devStatCount;
	}

	public Date getDeviceStatusTS() {
		return deviceStatusTS;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public void setDevLstState(State devLstState) {
		this.devLstState = devLstState;
	}

	public void setDevNetworkName(String devNetworkName) {
		this.devNetworkName = devNetworkName;
	}

	public void setDevStatCount(int devStatCount) {
		this.devStatCount = devStatCount;
	}

	public void setDeviceStatusTS(Date deviceStatusTS) {
		this.deviceStatusTS = deviceStatusTS;
	}

	public Integer getDispStatus() {
		return dispStatus;
	}

	public void setDispStatus(Integer dispStatus) {
		this.dispStatus = dispStatus;
	}

	@Override
	public String toString() {
		return "Device [deviceType=" + deviceType + ", deviceId=" + deviceId + ", deviceName=" + deviceName + ", devLstState=" + devLstState
				+ ", devNetworkName=" + devNetworkName + ", devStatCount=" + devStatCount + ", deviceStatusTS=" + deviceStatusTS + "]";
	}

}