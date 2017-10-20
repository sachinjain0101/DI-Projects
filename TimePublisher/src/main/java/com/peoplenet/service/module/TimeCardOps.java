package com.peoplenet.service.module;

import com.google.gson.Gson;
import com.peoplenet.model.TimeCard;
import com.peoplenet.model.TimeCardParams;
import com.peoplenet.service.dao.DataSource;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TimeCardOps {

    public final static Logger LOGGER = Logger.getLogger(TimeCardOps.class);

    private static Properties props;

    static {
        props = new Properties();
        try {
            props.load(new FileInputStream("queries.qry"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getTimeCard(TimeCardParams tcp) {

        String sql = "";

        if(tcp != null)
            sql = props.getProperty("TC_QRY");
        else
            sql = props.getProperty("TC_ALL_QRY");

        LOGGER.debug("========" + sql);
        //LOGGER.debug("========" + tcp.toString());
        Gson gson = new Gson();
        //ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        List<String> lstTC = new ArrayList<String>();

        try {
            Connection con = DataSource.getDataSourceConnection().getConnection();
            PreparedStatement pst = con.prepareStatement(sql);

            if(tcp != null) {
                int i = 0;
                pst.setString(1, tcp.getClient());
                pst.setString(2, tcp.getGroupCode());
                pst.setString(3, tcp.getPped());
                pst.setString(4, tcp.getSsn());
            }

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                TimeCard tc = new TimeCard();
                tc.setClient(rs.getString(1));
                tc.setGroupCode(rs.getString(2));
                tc.setSsn(rs.getString(3));
                tc.setPayrollPeriodEndDate(rs.getString(4));
                tc.setFirstName(rs.getString(5));
                tc.setLastName(rs.getString(6));
                tc.setSiteNo(rs.getInt(7));
                tc.setDeptNo(rs.getInt(8));
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

                lstTC.add(gson.toJson(tc));
                //LOGGER.debug(tc.toString());
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lstTC;

    }


}
