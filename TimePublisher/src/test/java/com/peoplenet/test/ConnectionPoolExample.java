package com.peoplenet.test;

import com.peoplenet.model.TimeCard;
import com.peoplenet.service.dao.DataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionPoolExample {

    public static void main(String[] args) throws IOException {


        Properties props = new Properties();
        props.load(new FileInputStream("queries.qry"));
        String sql = props.getProperty("TC_QRY");


        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery();) {

            List<TimeCard> lstTC = new ArrayList<TimeCard>();

            while (rs.next()) {
                TimeCard tc = new TimeCard();//(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDate(4));
                tc.setClient(rs.getString(1));
                tc.setGroupCode(rs.getString(2));
                tc.setSsn(rs.getString(3));
                tc.setPayrollPeriodEndDate(rs.getString(4));
                tc.setFirstName(rs.getString(5));
                tc.setLastName(rs.getString(6));
                tc.setSiteNo(rs.getInt(7));
                tc.setDeptName(rs.getString(8));
                tc.setDeptName(rs.getString(9));
                tc.setClientDeptCode(rs.getString(10));
                tc.setCostID(rs.getInt(11));
                tc.setAssignmentNo(rs.getString(12));
                tc.setAdjustmentCode(rs.getString(13));
                tc.setAdjustmentName(rs.getString(14));
                tc.setAgencyNo(rs.getInt(15));
                tc.setAprvlStatus(rs.getString(16));
                tc.setAssignmentStartDate(rs.getString(17));
                tc.setTransDate(rs.getString(18));
                tc.setRegHours(rs.getDouble(19));
                tc.setOtHours(rs.getDouble(20));
                tc.setDtHours(rs.getDouble(21));
                tc.setRegDollars(rs.getDouble(22));
                tc.setOtDollars(rs.getDouble(23));
                tc.setDtDollars(rs.getDouble(24));
                lstTC.add(tc);
                System.out.println(tc.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}