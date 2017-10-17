package com.peoplenet.dbops.model;

import java.util.Date;

public class TimeCard {

    private String client;
    private String groupCode;
    private String ssn;
    private Date payrollPeriodEndDate;
    private String firstName;
    private String lastName;
    private Integer siteNo;
    private Integer deptNo;
    private String deptName;
    private String clientDeptCode;
    private Integer costID;
    private Integer assignmentNo;
    private String adjustmentCode;
    private String adjustmentName;
    private Integer agencyNo;
    private String aprvlStatus;
    private String assignmentStartDate;
    private Date transDate;
    private Double regHours;
    private Double otHours;
    private Double dtHours;
    private Double regDollars;
    private Double otDollars;
    private Double dtDollars    ;

    public TimeCard(String client, String groupCode, String ssn, Date payrollPeriodEndDate, String firstName, String lastName, Integer siteNo, Integer deptNo, String deptName, String clientDeptCode, Integer costID, Integer assignmentNo, String adjustmentCode, String adjustmentName, Integer agencyNo, String aprvlStatus, String assignmentStartDate, Date transDate, Double regHours, Double otHours, Double dtHours, Double regDollars, Double otDollars, Double dtDollars) {
        this.client = client;
        this.groupCode = groupCode;
        this.ssn = ssn;
        this.payrollPeriodEndDate = payrollPeriodEndDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.siteNo = siteNo;
        this.deptNo = deptNo;
        this.deptName = deptName;
        this.clientDeptCode = clientDeptCode;
        this.costID = costID;
        this.assignmentNo = assignmentNo;
        this.adjustmentCode = adjustmentCode;
        this.adjustmentName = adjustmentName;
        this.agencyNo = agencyNo;
        this.aprvlStatus = aprvlStatus;
        this.assignmentStartDate = assignmentStartDate;
        this.transDate = transDate;
        this.regHours = regHours;
        this.otHours = otHours;
        this.dtHours = dtHours;
        this.regDollars = regDollars;
        this.otDollars = otDollars;
        this.dtDollars = dtDollars;
    }

    public TimeCard(String client, String groupCode, String ssn, Date payrollPeriodEndDate) {
        this.client = client;
        this.groupCode = groupCode;
        this.ssn = ssn;
        this.payrollPeriodEndDate = payrollPeriodEndDate;
    }

    public TimeCard() {
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public Date getPayrollPeriodEndDate() {
        return payrollPeriodEndDate;
    }

    public void setPayrollPeriodEndDate(Date payrollPeriodEndDate) {
        this.payrollPeriodEndDate = payrollPeriodEndDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getSiteNo() {
        return siteNo;
    }

    public void setSiteNo(Integer siteNo) {
        this.siteNo = siteNo;
    }

    public Integer getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getClientDeptCode() {
        return clientDeptCode;
    }

    public void setClientDeptCode(String clientDeptCode) {
        this.clientDeptCode = clientDeptCode;
    }

    public Integer getCostID() {
        return costID;
    }

    public void setCostID(Integer costID) {
        this.costID = costID;
    }

    public Integer getAssignmentNo() {
        return assignmentNo;
    }

    public void setAssignmentNo(Integer assignmentNo) {
        this.assignmentNo = assignmentNo;
    }

    public String getAdjustmentCode() {
        return adjustmentCode;
    }

    public void setAdjustmentCode(String adjustmentCode) {
        this.adjustmentCode = adjustmentCode;
    }

    public String getAdjustmentName() {
        return adjustmentName;
    }

    public void setAdjustmentName(String adjustmentName) {
        this.adjustmentName = adjustmentName;
    }

    public Integer getAgencyNo() {
        return agencyNo;
    }

    public void setAgencyNo(Integer agencyNo) {
        this.agencyNo = agencyNo;
    }

    public String getAprvlStatus() {
        return aprvlStatus;
    }

    public void setAprvlStatus(String aprvlStatus) {
        this.aprvlStatus = aprvlStatus;
    }

    public String getAssignmentStartDate() {
        return assignmentStartDate;
    }

    public void setAssignmentStartDate(String assignmentStartDate) {
        this.assignmentStartDate = assignmentStartDate;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public Double getRegHours() {
        return regHours;
    }

    public void setRegHours(Double regHours) {
        this.regHours = regHours;
    }

    public Double getOtHours() {
        return otHours;
    }

    public void setOtHours(Double otHours) {
        this.otHours = otHours;
    }

    public Double getDtHours() {
        return dtHours;
    }

    public void setDtHours(Double dtHours) {
        this.dtHours = dtHours;
    }

    public Double getRegDollars() {
        return regDollars;
    }

    public void setRegDollars(Double regDollars) {
        this.regDollars = regDollars;
    }

    public Double getOtDollars() {
        return otDollars;
    }

    public void setOtDollars(Double otDollars) {
        this.otDollars = otDollars;
    }

    public Double getDtDollars() {
        return dtDollars;
    }

    public void setDtDollars(Double dtDollars) {
        this.dtDollars = dtDollars;
    }


    @Override
    public String toString() {
        return "TimeCard{" +
                "client='" + client + '\'' +
                ", groupCode='" + groupCode + '\'' +
                ", ssn='" + ssn + '\'' +
                ", payrollPeriodEndDate=" + payrollPeriodEndDate +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", siteNo=" + siteNo +
                ", deptNo=" + deptNo +
                ", deptName='" + deptName + '\'' +
                ", clientDeptCode='" + clientDeptCode + '\'' +
                ", costID=" + costID +
                ", assignmentNo=" + assignmentNo +
                ", adjustmentCode='" + adjustmentCode + '\'' +
                ", adjustmentName='" + adjustmentName + '\'' +
                ", agencyNo=" + agencyNo +
                ", aprvlStatus='" + aprvlStatus + '\'' +
                ", assignmentStartDate='" + assignmentStartDate + '\'' +
                ", transDate=" + transDate +
                ", regHours=" + regHours +
                ", otHours=" + otHours +
                ", dtHours=" + dtHours +
                ", regDollars=" + regDollars +
                ", otDollars=" + otDollars +
                ", dtDollars=" + dtDollars +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeCard timeCard = (TimeCard) o;

        if (client != null ? !client.equals(timeCard.client) : timeCard.client != null) return false;
        if (groupCode != null ? !groupCode.equals(timeCard.groupCode) : timeCard.groupCode != null) return false;
        if (ssn != null ? !ssn.equals(timeCard.ssn) : timeCard.ssn != null) return false;
        if (payrollPeriodEndDate != null ? !payrollPeriodEndDate.equals(timeCard.payrollPeriodEndDate) : timeCard.payrollPeriodEndDate != null)
            return false;
        if (firstName != null ? !firstName.equals(timeCard.firstName) : timeCard.firstName != null) return false;
        if (lastName != null ? !lastName.equals(timeCard.lastName) : timeCard.lastName != null) return false;
        if (siteNo != null ? !siteNo.equals(timeCard.siteNo) : timeCard.siteNo != null) return false;
        if (deptNo != null ? !deptNo.equals(timeCard.deptNo) : timeCard.deptNo != null) return false;
        if (deptName != null ? !deptName.equals(timeCard.deptName) : timeCard.deptName != null) return false;
        if (clientDeptCode != null ? !clientDeptCode.equals(timeCard.clientDeptCode) : timeCard.clientDeptCode != null)
            return false;
        if (costID != null ? !costID.equals(timeCard.costID) : timeCard.costID != null) return false;
        if (assignmentNo != null ? !assignmentNo.equals(timeCard.assignmentNo) : timeCard.assignmentNo != null)
            return false;
        if (adjustmentCode != null ? !adjustmentCode.equals(timeCard.adjustmentCode) : timeCard.adjustmentCode != null)
            return false;
        if (adjustmentName != null ? !adjustmentName.equals(timeCard.adjustmentName) : timeCard.adjustmentName != null)
            return false;
        if (agencyNo != null ? !agencyNo.equals(timeCard.agencyNo) : timeCard.agencyNo != null) return false;
        if (aprvlStatus != null ? !aprvlStatus.equals(timeCard.aprvlStatus) : timeCard.aprvlStatus != null)
            return false;
        if (assignmentStartDate != null ? !assignmentStartDate.equals(timeCard.assignmentStartDate) : timeCard.assignmentStartDate != null)
            return false;
        if (transDate != null ? !transDate.equals(timeCard.transDate) : timeCard.transDate != null) return false;
        if (regHours != null ? !regHours.equals(timeCard.regHours) : timeCard.regHours != null) return false;
        if (otHours != null ? !otHours.equals(timeCard.otHours) : timeCard.otHours != null) return false;
        if (dtHours != null ? !dtHours.equals(timeCard.dtHours) : timeCard.dtHours != null) return false;
        if (regDollars != null ? !regDollars.equals(timeCard.regDollars) : timeCard.regDollars != null) return false;
        if (otDollars != null ? !otDollars.equals(timeCard.otDollars) : timeCard.otDollars != null) return false;
        return dtDollars != null ? dtDollars.equals(timeCard.dtDollars) : timeCard.dtDollars == null;
    }

    @Override
    public int hashCode() {
        int result = client != null ? client.hashCode() : 0;
        result = 31 * result + (groupCode != null ? groupCode.hashCode() : 0);
        result = 31 * result + (ssn != null ? ssn.hashCode() : 0);
        result = 31 * result + (payrollPeriodEndDate != null ? payrollPeriodEndDate.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (siteNo != null ? siteNo.hashCode() : 0);
        result = 31 * result + (deptNo != null ? deptNo.hashCode() : 0);
        result = 31 * result + (deptName != null ? deptName.hashCode() : 0);
        result = 31 * result + (clientDeptCode != null ? clientDeptCode.hashCode() : 0);
        result = 31 * result + (costID != null ? costID.hashCode() : 0);
        result = 31 * result + (assignmentNo != null ? assignmentNo.hashCode() : 0);
        result = 31 * result + (adjustmentCode != null ? adjustmentCode.hashCode() : 0);
        result = 31 * result + (adjustmentName != null ? adjustmentName.hashCode() : 0);
        result = 31 * result + (agencyNo != null ? agencyNo.hashCode() : 0);
        result = 31 * result + (aprvlStatus != null ? aprvlStatus.hashCode() : 0);
        result = 31 * result + (assignmentStartDate != null ? assignmentStartDate.hashCode() : 0);
        result = 31 * result + (transDate != null ? transDate.hashCode() : 0);
        result = 31 * result + (regHours != null ? regHours.hashCode() : 0);
        result = 31 * result + (otHours != null ? otHours.hashCode() : 0);
        result = 31 * result + (dtHours != null ? dtHours.hashCode() : 0);
        result = 31 * result + (regDollars != null ? regDollars.hashCode() : 0);
        result = 31 * result + (otDollars != null ? otDollars.hashCode() : 0);
        result = 31 * result + (dtDollars != null ? dtDollars.hashCode() : 0);
        return result;
    }
}
