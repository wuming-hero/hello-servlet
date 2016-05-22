package com.wuming.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConn {
    private static final String url = "jdbc:mysql://";   //存储Server连接路径
    private static final String serverName = "127.0.0.1";    //存储机器名称
    private static final String portNumber = "3306";     //存储商品名称
    private static final String databaseName = "study";  //存储数据库名称
    private static final String userName = "root";   //存储用户名称
    private static final String password = "";   //存储密码

    //获取连接数据库路径并返回
    private static String getConnectionUrl() {
        return url + serverName + ":" + portNumber + "/" + databaseName;
    }

    //获取Connection对象并返回
    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");//加载驱动
            con = DriverManager.getConnection(this.getConnectionUrl(), userName, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("getConnection()内部跟踪错误:" + e.getMessage());
        }
        return con;
    }
}
