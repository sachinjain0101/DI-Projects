package com.spi.model;

import ch.shamu.jsendnrdp.domain.State;

public class KeyVal {
	State devLstState;
	Integer devStatCount;

	public KeyVal() {}

	public KeyVal(State devLstState, Integer devStatCount) {
		this.devLstState = devLstState;
		this.devStatCount = devStatCount;
	}

	public State getDevLstState() {
		return devLstState;
	}

	public Integer getDevStatCount() {
		return devStatCount;
	}

	public void setDevLstState(State devLstState) {
		this.devLstState = devLstState;
	}

	public void setDevStatCount(Integer devStatCount) {
		this.devStatCount = devStatCount;
	}
}
