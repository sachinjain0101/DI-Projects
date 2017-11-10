package com.peoplenet.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tblTestSJ database table.
 * 
 */
@Entity
@Table(name="tblTestSJ")
@NamedQuery(name="TestSJ.findAll", query="SELECT t FROM TestSJ t")
public class TestSJ implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="FullName")
	private String fullName;

	@Id
	@Column(name="RecordID")
	private int recordID;

	public TestSJ() {
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getRecordID() {
		return this.recordID;
	}

	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}

}
