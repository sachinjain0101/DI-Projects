package com.peoplenet.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the tblTimeHistDetail database table.
 * 
 */
@Entity
@Table(name="tblTimeHistDetail")
@NamedQuery(name="TimeHistDetail.findAll", query="SELECT t FROM TimeHistDetail t",lockMode=LockModeType.NONE)
public class TimeHistDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="RecordID")
	private long recordID;

	@Column(name="ActualInTime")
	private Timestamp actualInTime;

	@Column(name="ActualOutTime")
	private Timestamp actualOutTime;

	@Column(name="AdjustmentCode")
	private String adjustmentCode;

	@Column(name="AdjustmentName")
	private String adjustmentName;

	@Column(name="AgencyNo")
	private short agencyNo;

	@Column(name="AllocatedDT_Hours")
	private BigDecimal allocatedDT_Hours;

	@Column(name="AllocatedOT_Hours")
	private BigDecimal allocatedOT_Hours;

	@Column(name="AllocatedRegHours")
	private BigDecimal allocatedRegHours;

	@Column(name="AprvlAdjOrigClkAdjNo")
	private String aprvlAdjOrigClkAdjNo;

	@Column(name="AprvlAdjOrigRecID")
	private long aprvlAdjOrigRecID;

	@Column(name="AprvlStatus")
	private String aprvlStatus;

	@Column(name="AprvlStatus_Date")
	private Timestamp aprvlStatus_Date;

	@Column(name="AprvlStatus_Mobile")
	private boolean aprvlStatus_Mobile;

	@Column(name="AprvlStatus_UserID")
	private int aprvlStatus_UserID;

	@Column(name="BillOTRate")
	private BigDecimal billOTRate;

	@Column(name="BillOTRateOverride")
	private BigDecimal billOTRateOverride;

	@Column(name="BillRate")
	private BigDecimal billRate;

	@Column(name="Borrowed")
	private String borrowed;

	@Column(name="Changed_DeptNo")
	private String changed_DeptNo;

	@Column(name="Changed_InPunch")
	private String changed_InPunch;

	@Column(name="Changed_OutPunch")
	private String changed_OutPunch;

	@Column(name="Client")
	private String client;

	@Column(name="ClkTransNo")
	private long clkTransNo;

	@Column(name="ClockAdjustmentNo")
	private String clockAdjustmentNo;

	@Column(name="CostID")
	private String costID;

	@Column(name="CountAsOT")
	private String countAsOT;

	@Column(name="CrossoverOtherGroup")
	private int crossoverOtherGroup;

	@Column(name="CrossoverStatus")
	private String crossoverStatus;

	@Column(name="DaylightSavTime")
	private String daylightSavTime;

	@Column(name="DeptNo")
	private int deptNo;

	@Column(name="DivisionID")
	private long divisionID;

	@Column(name="Dollars")
	private BigDecimal dollars;

	private BigDecimal DT_Dollars;

	private BigDecimal DT_Dollars4;

	private BigDecimal DT_Hours;

	private BigDecimal DTBillingDollars;

	private BigDecimal DTBillingDollars4;

	@Column(name="EmpStatus")
	private short empStatus;

	@Column(name="GroupCode")
	private int groupCode;

	@Column(name="HandledByImporter")
	private String handledByImporter;

	@Column(name="Holiday")
	private String holiday;

	@Column(name="Hours")
	private BigDecimal hours;

	@Column(name="InClass")
	private String inClass;

	@Column(name="InDay")
	private short inDay;

	@Column(name="InRoundOFF")
	private String inRoundOFF;

	@Column(name="InSiteNo")
	private int inSiteNo;

	@Column(name="InSrc")
	private String inSrc;

	@Column(name="InTime")
	private Timestamp inTime;

	@Column(name="InTimestamp")
	private long inTimestamp;

	@Column(name="InVerified")
	private String inVerified;

	@Column(name="JobID")
	private long jobID;

	@Column(name="MasterPayrollDate")
	private Timestamp masterPayrollDate;

	private BigDecimal OT_Dollars;

	private BigDecimal OT_Dollars4;

	private BigDecimal OT_Hours;

	private BigDecimal OTBillingDollars;

	private BigDecimal OTBillingDollars4;

	@Column(name="OutClass")
	private String outClass;

	@Column(name="OutDay")
	private short outDay;

	@Column(name="OutRoundOFF")
	private String outRoundOFF;

	@Column(name="OutSiteNo")
	private int outSiteNo;

	@Column(name="OutSrc")
	private String outSrc;

	@Column(name="OutTime")
	private Timestamp outTime;

	private long outTimestamp;

	@Column(name="OutUserCode")
	private String outUserCode;

	@Column(name="OutVerified")
	private String outVerified;

	@Column(name="PayRate")
	private BigDecimal payRate;

	@Column(name="PayrollPeriodEndDate")
	private Timestamp payrollPeriodEndDate;

	@Column(name="RegBillingDollars")
	private BigDecimal regBillingDollars;

	@Column(name="RegBillingDollars4")
	private BigDecimal regBillingDollars4;

	@Column(name="RegDollars")
	private BigDecimal regDollars;

	@Column(name="RegDollars4")
	private BigDecimal regDollars4;

	@Column(name="RegHours")
	private BigDecimal regHours;

	@Column(name="ShiftDiffAmt")
	private BigDecimal shiftDiffAmt;

	@Column(name="ShiftDiffClass")
	private String shiftDiffClass;

	@Column(name="ShiftNo")
	private short shiftNo;

	@Column(name="SiteNo")
	private int siteNo;

	@Column(name="SSN")
	private int ssn;

	@Column(name="TransDate")
	private Timestamp transDate;

	@Column(name="TransType")
	private short transType;

	@Column(name="UserCode")
	private String userCode;

	private BigDecimal xAdjHours;

	public TimeHistDetail() {
	}

	public long getRecordID() {
		return this.recordID;
	}

	public void setRecordID(long recordID) {
		this.recordID = recordID;
	}

	public Timestamp getActualInTime() {
		return this.actualInTime;
	}

	public void setActualInTime(Timestamp actualInTime) {
		this.actualInTime = actualInTime;
	}

	public Timestamp getActualOutTime() {
		return this.actualOutTime;
	}

	public void setActualOutTime(Timestamp actualOutTime) {
		this.actualOutTime = actualOutTime;
	}

	public String getAdjustmentCode() {
		return this.adjustmentCode;
	}

	public void setAdjustmentCode(String adjustmentCode) {
		this.adjustmentCode = adjustmentCode;
	}

	public String getAdjustmentName() {
		return this.adjustmentName;
	}

	public void setAdjustmentName(String adjustmentName) {
		this.adjustmentName = adjustmentName;
	}

	public short getAgencyNo() {
		return this.agencyNo;
	}

	public void setAgencyNo(short agencyNo) {
		this.agencyNo = agencyNo;
	}

	public BigDecimal getAllocatedDT_Hours() {
		return this.allocatedDT_Hours;
	}

	public void setAllocatedDT_Hours(BigDecimal allocatedDT_Hours) {
		this.allocatedDT_Hours = allocatedDT_Hours;
	}

	public BigDecimal getAllocatedOT_Hours() {
		return this.allocatedOT_Hours;
	}

	public void setAllocatedOT_Hours(BigDecimal allocatedOT_Hours) {
		this.allocatedOT_Hours = allocatedOT_Hours;
	}

	public BigDecimal getAllocatedRegHours() {
		return this.allocatedRegHours;
	}

	public void setAllocatedRegHours(BigDecimal allocatedRegHours) {
		this.allocatedRegHours = allocatedRegHours;
	}

	public String getAprvlAdjOrigClkAdjNo() {
		return this.aprvlAdjOrigClkAdjNo;
	}

	public void setAprvlAdjOrigClkAdjNo(String aprvlAdjOrigClkAdjNo) {
		this.aprvlAdjOrigClkAdjNo = aprvlAdjOrigClkAdjNo;
	}

	public long getAprvlAdjOrigRecID() {
		return this.aprvlAdjOrigRecID;
	}

	public void setAprvlAdjOrigRecID(long aprvlAdjOrigRecID) {
		this.aprvlAdjOrigRecID = aprvlAdjOrigRecID;
	}

	public String getAprvlStatus() {
		return this.aprvlStatus;
	}

	public void setAprvlStatus(String aprvlStatus) {
		this.aprvlStatus = aprvlStatus;
	}

	public Timestamp getAprvlStatus_Date() {
		return this.aprvlStatus_Date;
	}

	public void setAprvlStatus_Date(Timestamp aprvlStatus_Date) {
		this.aprvlStatus_Date = aprvlStatus_Date;
	}

	public boolean getAprvlStatus_Mobile() {
		return this.aprvlStatus_Mobile;
	}

	public void setAprvlStatus_Mobile(boolean aprvlStatus_Mobile) {
		this.aprvlStatus_Mobile = aprvlStatus_Mobile;
	}

	public int getAprvlStatus_UserID() {
		return this.aprvlStatus_UserID;
	}

	public void setAprvlStatus_UserID(int aprvlStatus_UserID) {
		this.aprvlStatus_UserID = aprvlStatus_UserID;
	}

	public BigDecimal getBillOTRate() {
		return this.billOTRate;
	}

	public void setBillOTRate(BigDecimal billOTRate) {
		this.billOTRate = billOTRate;
	}

	public BigDecimal getBillOTRateOverride() {
		return this.billOTRateOverride;
	}

	public void setBillOTRateOverride(BigDecimal billOTRateOverride) {
		this.billOTRateOverride = billOTRateOverride;
	}

	public BigDecimal getBillRate() {
		return this.billRate;
	}

	public void setBillRate(BigDecimal billRate) {
		this.billRate = billRate;
	}

	public String getBorrowed() {
		return this.borrowed;
	}

	public void setBorrowed(String borrowed) {
		this.borrowed = borrowed;
	}

	public String getChanged_DeptNo() {
		return this.changed_DeptNo;
	}

	public void setChanged_DeptNo(String changed_DeptNo) {
		this.changed_DeptNo = changed_DeptNo;
	}

	public String getChanged_InPunch() {
		return this.changed_InPunch;
	}

	public void setChanged_InPunch(String changed_InPunch) {
		this.changed_InPunch = changed_InPunch;
	}

	public String getChanged_OutPunch() {
		return this.changed_OutPunch;
	}

	public void setChanged_OutPunch(String changed_OutPunch) {
		this.changed_OutPunch = changed_OutPunch;
	}

	public String getClient() {
		return this.client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public long getClkTransNo() {
		return this.clkTransNo;
	}

	public void setClkTransNo(long clkTransNo) {
		this.clkTransNo = clkTransNo;
	}

	public String getClockAdjustmentNo() {
		return this.clockAdjustmentNo;
	}

	public void setClockAdjustmentNo(String clockAdjustmentNo) {
		this.clockAdjustmentNo = clockAdjustmentNo;
	}

	public String getCostID() {
		return this.costID;
	}

	public void setCostID(String costID) {
		this.costID = costID;
	}

	public String getCountAsOT() {
		return this.countAsOT;
	}

	public void setCountAsOT(String countAsOT) {
		this.countAsOT = countAsOT;
	}

	public int getCrossoverOtherGroup() {
		return this.crossoverOtherGroup;
	}

	public void setCrossoverOtherGroup(int crossoverOtherGroup) {
		this.crossoverOtherGroup = crossoverOtherGroup;
	}

	public String getCrossoverStatus() {
		return this.crossoverStatus;
	}

	public void setCrossoverStatus(String crossoverStatus) {
		this.crossoverStatus = crossoverStatus;
	}

	public String getDaylightSavTime() {
		return this.daylightSavTime;
	}

	public void setDaylightSavTime(String daylightSavTime) {
		this.daylightSavTime = daylightSavTime;
	}

	public int getDeptNo() {
		return this.deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

	public long getDivisionID() {
		return this.divisionID;
	}

	public void setDivisionID(long divisionID) {
		this.divisionID = divisionID;
	}

	public BigDecimal getDollars() {
		return this.dollars;
	}

	public void setDollars(BigDecimal dollars) {
		this.dollars = dollars;
	}

	public BigDecimal getDT_Dollars() {
		return this.DT_Dollars;
	}

	public void setDT_Dollars(BigDecimal DT_Dollars) {
		this.DT_Dollars = DT_Dollars;
	}

	public BigDecimal getDT_Dollars4() {
		return this.DT_Dollars4;
	}

	public void setDT_Dollars4(BigDecimal DT_Dollars4) {
		this.DT_Dollars4 = DT_Dollars4;
	}

	public BigDecimal getDT_Hours() {
		return this.DT_Hours;
	}

	public void setDT_Hours(BigDecimal DT_Hours) {
		this.DT_Hours = DT_Hours;
	}

	public BigDecimal getDTBillingDollars() {
		return this.DTBillingDollars;
	}

	public void setDTBillingDollars(BigDecimal DTBillingDollars) {
		this.DTBillingDollars = DTBillingDollars;
	}

	public BigDecimal getDTBillingDollars4() {
		return this.DTBillingDollars4;
	}

	public void setDTBillingDollars4(BigDecimal DTBillingDollars4) {
		this.DTBillingDollars4 = DTBillingDollars4;
	}

	public short getEmpStatus() {
		return this.empStatus;
	}

	public void setEmpStatus(short empStatus) {
		this.empStatus = empStatus;
	}

	public int getGroupCode() {
		return this.groupCode;
	}

	public void setGroupCode(int groupCode) {
		this.groupCode = groupCode;
	}

	public String getHandledByImporter() {
		return this.handledByImporter;
	}

	public void setHandledByImporter(String handledByImporter) {
		this.handledByImporter = handledByImporter;
	}

	public String getHoliday() {
		return this.holiday;
	}

	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}

	public BigDecimal getHours() {
		return this.hours;
	}

	public void setHours(BigDecimal hours) {
		this.hours = hours;
	}

	public String getInClass() {
		return this.inClass;
	}

	public void setInClass(String inClass) {
		this.inClass = inClass;
	}

	public short getInDay() {
		return this.inDay;
	}

	public void setInDay(short inDay) {
		this.inDay = inDay;
	}

	public String getInRoundOFF() {
		return this.inRoundOFF;
	}

	public void setInRoundOFF(String inRoundOFF) {
		this.inRoundOFF = inRoundOFF;
	}

	public int getInSiteNo() {
		return this.inSiteNo;
	}

	public void setInSiteNo(int inSiteNo) {
		this.inSiteNo = inSiteNo;
	}

	public String getInSrc() {
		return this.inSrc;
	}

	public void setInSrc(String inSrc) {
		this.inSrc = inSrc;
	}

	public Timestamp getInTime() {
		return this.inTime;
	}

	public void setInTime(Timestamp inTime) {
		this.inTime = inTime;
	}

	public long getInTimestamp() {
		return this.inTimestamp;
	}

	public void setInTimestamp(long inTimestamp) {
		this.inTimestamp = inTimestamp;
	}

	public String getInVerified() {
		return this.inVerified;
	}

	public void setInVerified(String inVerified) {
		this.inVerified = inVerified;
	}

	public long getJobID() {
		return this.jobID;
	}

	public void setJobID(long jobID) {
		this.jobID = jobID;
	}

	public Timestamp getMasterPayrollDate() {
		return this.masterPayrollDate;
	}

	public void setMasterPayrollDate(Timestamp masterPayrollDate) {
		this.masterPayrollDate = masterPayrollDate;
	}

	public BigDecimal getOT_Dollars() {
		return this.OT_Dollars;
	}

	public void setOT_Dollars(BigDecimal OT_Dollars) {
		this.OT_Dollars = OT_Dollars;
	}

	public BigDecimal getOT_Dollars4() {
		return this.OT_Dollars4;
	}

	public void setOT_Dollars4(BigDecimal OT_Dollars4) {
		this.OT_Dollars4 = OT_Dollars4;
	}

	public BigDecimal getOT_Hours() {
		return this.OT_Hours;
	}

	public void setOT_Hours(BigDecimal OT_Hours) {
		this.OT_Hours = OT_Hours;
	}

	public BigDecimal getOTBillingDollars() {
		return this.OTBillingDollars;
	}

	public void setOTBillingDollars(BigDecimal OTBillingDollars) {
		this.OTBillingDollars = OTBillingDollars;
	}

	public BigDecimal getOTBillingDollars4() {
		return this.OTBillingDollars4;
	}

	public void setOTBillingDollars4(BigDecimal OTBillingDollars4) {
		this.OTBillingDollars4 = OTBillingDollars4;
	}

	public String getOutClass() {
		return this.outClass;
	}

	public void setOutClass(String outClass) {
		this.outClass = outClass;
	}

	public short getOutDay() {
		return this.outDay;
	}

	public void setOutDay(short outDay) {
		this.outDay = outDay;
	}

	public String getOutRoundOFF() {
		return this.outRoundOFF;
	}

	public void setOutRoundOFF(String outRoundOFF) {
		this.outRoundOFF = outRoundOFF;
	}

	public int getOutSiteNo() {
		return this.outSiteNo;
	}

	public void setOutSiteNo(int outSiteNo) {
		this.outSiteNo = outSiteNo;
	}

	public String getOutSrc() {
		return this.outSrc;
	}

	public void setOutSrc(String outSrc) {
		this.outSrc = outSrc;
	}

	public Timestamp getOutTime() {
		return this.outTime;
	}

	public void setOutTime(Timestamp outTime) {
		this.outTime = outTime;
	}

	public long getOutTimestamp() {
		return this.outTimestamp;
	}

	public void setOutTimestamp(long outTimestamp) {
		this.outTimestamp = outTimestamp;
	}

	public String getOutUserCode() {
		return this.outUserCode;
	}

	public void setOutUserCode(String outUserCode) {
		this.outUserCode = outUserCode;
	}

	public String getOutVerified() {
		return this.outVerified;
	}

	public void setOutVerified(String outVerified) {
		this.outVerified = outVerified;
	}

	public BigDecimal getPayRate() {
		return this.payRate;
	}

	public void setPayRate(BigDecimal payRate) {
		this.payRate = payRate;
	}

	public Timestamp getPayrollPeriodEndDate() {
		return this.payrollPeriodEndDate;
	}

	public void setPayrollPeriodEndDate(Timestamp payrollPeriodEndDate) {
		this.payrollPeriodEndDate = payrollPeriodEndDate;
	}

	public BigDecimal getRegBillingDollars() {
		return this.regBillingDollars;
	}

	public void setRegBillingDollars(BigDecimal regBillingDollars) {
		this.regBillingDollars = regBillingDollars;
	}

	public BigDecimal getRegBillingDollars4() {
		return this.regBillingDollars4;
	}

	public void setRegBillingDollars4(BigDecimal regBillingDollars4) {
		this.regBillingDollars4 = regBillingDollars4;
	}

	public BigDecimal getRegDollars() {
		return this.regDollars;
	}

	public void setRegDollars(BigDecimal regDollars) {
		this.regDollars = regDollars;
	}

	public BigDecimal getRegDollars4() {
		return this.regDollars4;
	}

	public void setRegDollars4(BigDecimal regDollars4) {
		this.regDollars4 = regDollars4;
	}

	public BigDecimal getRegHours() {
		return this.regHours;
	}

	public void setRegHours(BigDecimal regHours) {
		this.regHours = regHours;
	}

	public BigDecimal getShiftDiffAmt() {
		return this.shiftDiffAmt;
	}

	public void setShiftDiffAmt(BigDecimal shiftDiffAmt) {
		this.shiftDiffAmt = shiftDiffAmt;
	}

	public String getShiftDiffClass() {
		return this.shiftDiffClass;
	}

	public void setShiftDiffClass(String shiftDiffClass) {
		this.shiftDiffClass = shiftDiffClass;
	}

	public short getShiftNo() {
		return this.shiftNo;
	}

	public void setShiftNo(short shiftNo) {
		this.shiftNo = shiftNo;
	}

	public int getSiteNo() {
		return this.siteNo;
	}

	public void setSiteNo(int siteNo) {
		this.siteNo = siteNo;
	}

	public int getSsn() {
		return this.ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}

	public Timestamp getTransDate() {
		return this.transDate;
	}

	public void setTransDate(Timestamp transDate) {
		this.transDate = transDate;
	}

	public short getTransType() {
		return this.transType;
	}

	public void setTransType(short transType) {
		this.transType = transType;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public BigDecimal getXAdjHours() {
		return this.xAdjHours;
	}

	public void setXAdjHours(BigDecimal xAdjHours) {
		this.xAdjHours = xAdjHours;
	}

}