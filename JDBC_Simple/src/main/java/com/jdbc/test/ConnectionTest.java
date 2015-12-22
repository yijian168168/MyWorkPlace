package com.jdbc.test;

import org.junit.Test;

import java.sql.*;
import java.util.Properties;

/**
 * Created by Administrator on 2015/11/20 0020.
 */
public class ConnectionTest {

    @Test
    public void testConnection() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        Properties properties = new Properties();
        properties.put("jdbc_driver","com.mysql.jdbc.Driver");
        properties.put("jdbc_url","jdbc:mysql://localhost:3306/jdbc");
        properties.put("user","qingrong");
        properties.put("password", "123456");
        Connection connection = getConnection(properties);
        System.out.println(connection);
    }

    @Test
    public void testDriverManager() throws SQLException, ClassNotFoundException {
        Properties properties = new Properties();
        properties.put("jdbc_driver","com.mysql.jdbc.Driver");
        properties.put("jdbc_url","jdbc:mysql://localhost:3306/jdbc");
        properties.put("user","qingrong");
        properties.put("password", "123456");

        Connection connection = getConnectionByDriverManager(properties);
        System.out.println(connection);
    }


    @Test
    public void testExcuteInsert() throws SQLException, ClassNotFoundException {
        Properties properties = new Properties();
        properties.put("jdbc_driver","com.mysql.jdbc.Driver");
        properties.put("jdbc_url","jdbc:mysql://localhost:3306/jdbc");
        properties.put("user","qingrong");
        properties.put("password", "123456");
        Connection connection = getConnectionByDriverManager(properties);
        String sql = "insert into jdbcTest1(name) values('庆荣')";
        Boolean aBoolean = insertBySql(sql, connection);
        System.out.println(aBoolean);
    }

    /**直接建立数据库连接*/
    public Connection getConnection(Properties properties) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Driver driver = (Driver) Class.forName(properties.getProperty("jdbc_driver")).newInstance();
        Connection connection = driver.connect(properties.getProperty("jdbc_url"), properties);
        return connection;
    }

    /**通过建立数据库连接*/
    public Connection getConnectionByDriverManager(Properties properties) throws ClassNotFoundException, SQLException {

        //加载数据库驱动
        Class.forName(properties.getProperty("jdbc_driver"));

        Connection connection = DriverManager.getConnection(properties.getProperty("jdbc_url"), properties);

        return connection;
    }


    /**执行execute(),会返回boolean值,如果是true表示你执行的是查询(select)操作,如果是false,表示你执行的是更新操作啊(即DDL和DML语句啊)*/
    public Boolean insertBySql(String sql,Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        Boolean aBoolean = statement.execute(sql);
        return aBoolean;
    }


}
