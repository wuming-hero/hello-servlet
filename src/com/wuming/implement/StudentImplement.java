package com.wuming.implement;


import com.wuming.db.MySqlConn;
import com.wuming.pojo.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;  //引入结果集ResultSet
import java.sql.SQLException;
import java.util.ArrayList; //引入数组指针


//学生的实现类
public class StudentImplement {
    //用"-"将数据串成字符串,才能进一步向数据库做插入等操作
    public String joinString(String[] hobby) {
        String joinhobby = "";
        for (String temp : hobby) {
            joinhobby += temp + "-";
        }
        return joinhobby.substring(0, joinhobby.length() - 1);
    }

    public ArrayList<Student> queryStudent(String name) {
        ArrayList<Student> students = new ArrayList<Student>();//查询结果为好多对象,把这些对象放到一个集合里,所以这里先声明一个数组
        Connection con = null;
        PreparedStatement pre = null;
        MySqlConn conns = new MySqlConn();
        con = conns.getConnection();
        ResultSet rs = null;
        try {
            pre = con.prepareStatement("select * from student where name like ?");
            pre.setString(1, "%" + name + "%"); //模糊查询要用"%"+xx+"%"
            rs = pre.executeQuery();  //得到结果集
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setPassword(rs.getString("password"));
                student.setHobby(rs.getString("hobby"));
                students.add(student);//将查询到的一个个对象放到一个声明好的数组集合students里
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
        return students; //返回行到的集合
    }

    public void updateStudent(Student student) {
        Connection con = null;
        PreparedStatement pre = null;
        MySqlConn conns = new MySqlConn();
        con = conns.getConnection();
        try {
            pre = con.prepareStatement("update student set name=?,password=?, hobby=? where id=?");
            pre.setString(1, student.getName());
            pre.setString(2, student.getPassword());
            pre.setString(3, student.getHobby());
            pre.setInt(4, student.getId());
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

    public void deleteStudent(int id) {
        Connection con = null;
        PreparedStatement pre = null;
        MySqlConn conns = new MySqlConn();
        con = conns.getConnection();
        try {
            pre = con.prepareStatement("delete from student where id=?");
            pre.setInt(1, id);
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

    public void saveStudent(Student student) {
        Connection con = null;
        PreparedStatement pre = null;
        MySqlConn conns = new MySqlConn();
        con = conns.getConnection();
        try {
            pre = con.prepareStatement("insert into student(name,password,hobby)values(?,?,?)");
            pre.setString(1, student.getName());
            pre.setString(2, student.getPassword());
            pre.setString(3, student.getHobby());
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

}
