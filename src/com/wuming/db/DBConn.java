package com.wuming.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {
    private String url;//存储SQLServer连接路径
    private String serverName;//存储机器名称
    private String portNumber;//存储商品名称
    private String databaseName;//存储数据库名称
    private String userName;//存储用户名称
    private String password;//存储密码

    //设置连接数据库相关参数
    //设置连接数据库相关参数
    public DBConn() {
        url = "jdbc:sqlserver://";
        serverName = "127.0.0.1";
        portNumber = "1433";
        databaseName = "myDataBase";
        userName = "sa";
        password = "123456";
    }

    //获取连接数据库路径并返回
    private String getConnectionUrl() {
        return url + serverName + ":" + portNumber + ";databaseName=" + databaseName + ";";
    }

    //获取Connection对象并返回
    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//加载驱动
            //String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=myDataBase";
            con = DriverManager.getConnection(getConnectionUrl(), userName, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("getConnection()内部跟踪错误:" + e.getMessage());
        }
        return con;

    }
}
