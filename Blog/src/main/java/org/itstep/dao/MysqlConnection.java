package org.itstep.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class MysqlConnection {
    private final ComboPooledDataSource pooledDataSource;
    private static MysqlConnection instance = null;

    public ComboPooledDataSource getPooledDataSource() {
        return pooledDataSource;
    }

    private MysqlConnection() {
        System.out.println("MySQLConnection : constructor() ");
        pooledDataSource = new ComboPooledDataSource();
        try {
            pooledDataSource.setDriverClass("org.mariadb.jdbc.Driver");
            pooledDataSource.setUser("root");
            pooledDataSource.setPassword("");
            pooledDataSource.setJdbcUrl("jdbc:mariadb://localhost");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    public static MysqlConnection getInstance() {
        if (instance == null) {
            instance = new MysqlConnection();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return pooledDataSource.getConnection();
    }
}
