package com.peoplenet.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RecalcId implements Serializable{
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

}
