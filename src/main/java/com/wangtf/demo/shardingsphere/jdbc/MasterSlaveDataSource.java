package com.wangtf.demo.shardingsphere.jdbc;

import javax.sql.DataSource;
import java.sql.*;

public class MasterSlaveDataSource {

    public static void main(String[] args) {
        try {
            DataSource dataSource = YamlDataSourceFactory.newInstance();
            insertIntoMaster(dataSource);
            readFromSlave(dataSource);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void insertIntoMaster(DataSource dataSource) throws SQLException {
        Connection connection = dataSource.getConnection();
        String sql = "insert into t1(id) values(5);";
        Statement statement = connection.createStatement();
        statement.execute(sql);
        statement.close();
        connection.close();
    }

    public static void readFromSlave(DataSource dataSource) throws SQLException {
        Connection connection = dataSource.getConnection();
        String sql = "select * from `t1` limit 5";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            long id = rs.getLong("id");
            System.out.println("id: "+id);
        }
        statement.close();
        connection.close();
    }
}
