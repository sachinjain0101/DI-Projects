package com.peoplenet.service.dao;

import org.apache.commons.dbcp2.BasicDataSource;

public class DataSource {

    private static BasicDataSource dataSource;

    private DataSource() {
    }

    public static BasicDataSource getDataSourceConnection() {

        if (dataSource == null) {
            BasicDataSource ds = new BasicDataSource();
            ds.setUrl("jdbc:sqlserver://qa2-sql1:15150;databaseName=TimeHistory;integratedSecurity=true;");
            //ds.setUsername("root");
            //ds.setPassword("password");

            ds.setMinIdle(5);
            ds.setMaxIdle(10);
            ds.setMaxOpenPreparedStatements(1000);

            dataSource = ds;
        }
        return dataSource;
    }

}


/*
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

private static HikariConfig config = new HikariConfig();
private static HikariDataSource ds;

static {
    config.setJdbcUrl("jdbc:sqlserver://qa2-sql;databaseName=TimeHistory;integratedSecurity=true;");
    //config.setUsername("");
    //config.setPassword("");
    config.addDataSourceProperty("cachePrepStmts", "true");
    config.addDataSourceProperty("prepStmtCacheSize", "250");
    config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
    ds = new HikariDataSource(config);
}

public static Connection getConnection() throws SQLException {
    return ds.getConnection();
}

*/
