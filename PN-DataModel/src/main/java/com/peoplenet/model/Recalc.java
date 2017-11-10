package com.peoplenet.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the Recalcs database table.
 * 
 */
@Entity
@Table(name="Recalcs")
@NamedQuery(name="Recalc.findAll", query="SELECT r FROM Recalc r",lockMode=LockModeType.NONE)
public class Recalc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CalcTimeStamp")
	private Timestamp calcTimeStamp;

	@Column(name="Client")
	private String client;

	@Column(name="GroupCode")
	private int groupCode;

	@Column(name="PPED")
	private Object pped;

	@Column(name="RecordID")
	private long recordID;

	@Column(name="SSN")
	private int ssn;

	@Column(name="Status")
	private String status;

	public Recalc() {
	}

	public Timestamp getCalcTimeStamp() {
		return this.calcTimeStamp;
	}

	public void setCalcTimeStamp(Timestamp calcTimeStamp) {
		this.calcTimeStamp = calcTimeStamp;
	}

	public String getClient() {
		return this.client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public int getGroupCode() {
		return this.groupCode;
	}

	public void setGroupCode(int groupCode) {
		this.groupCode = groupCode;
	}

	public Object getPped() {
		return this.pped;
	}

	public void setPped(Object pped) {
		this.pped = pped;
	}

	public long getRecordID() {
		return this.recordID;
	}

	public void setRecordID(long recordID) {
		this.recordID = recordID;
	}

	public int getSsn() {
		return this.ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}