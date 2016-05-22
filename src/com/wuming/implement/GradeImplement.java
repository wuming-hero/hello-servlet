package com.wuming.implement;

import com.wuming.db.MySqlConn;
import com.wuming.pojo.Grade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GradeImplement {
    public void saveGrade(Grade grade) {
        //连接数据库,完成数据的录入操作
        //System.out.println("---saveGrade---");
        Connection con = null;
        PreparedStatement pre = null;
        MySqlConn conns = new MySqlConn();
        con = conns.getConnection();
        try {
            pre = con.prepareStatement("insert into grade(gname) values (?)");
            pre.setString(1, grade.getGname());
            pre.executeUpdate();
            System.out.print("恭喜您,操作成功");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    public ArrayList<Grade> queryGrade(String gname) {
        ArrayList<Grade> grades = new ArrayList<Grade>();//查询结果为好多对象,把这些对象放到一个集合里,所以这里先声明一个数组
        Connection con = null;
        PreparedStatement pre = null;
        MySqlConn conns = new MySqlConn();
        con = conns.getConnection();
        ResultSet rs = null;
        try {
            pre = con.prepareStatement("select * from grade where gname like ?");
            pre.setString(1, "%" + gname + "%"); //模糊查询要用"%"+xx+"%"
            rs = pre.executeQuery();  //得到结果集
            while (rs.next()) {
                Grade grade = new Grade();
                grade.setGid(rs.getInt("gid"));

                grade.setGname(rs.getString("gname"));
                grades.add(grade);//将查询到的一个个对象放到一个声明好的数组集合students里

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return grades; //返回行到的集合
    }

    public void updateGrade(Grade grade) {
        Connection con = null;
        PreparedStatement pre = null;
        MySqlConn conns = new MySqlConn();
        con = conns.getConnection();
        try {
            pre = con.prepareStatement("update grade set gname=? where gid=?");
            pre.setString(1, grade.getGname());
            pre.setInt(2, grade.getGid());
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    public void deleteGrade(int gid) {
        Connection con = null;
        PreparedStatement pre = null;
        MySqlConn conns = new MySqlConn();
        con = conns.getConnection();
        try {
            pre = con.prepareStatement("delete grade where gid=?");
            pre.setInt(1, gid);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
