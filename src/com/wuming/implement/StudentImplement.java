package com.wuming.implement;

import com.wuming.db.MySqlConn;
import com.wuming.pojo.Grade;
import com.wuming.pojo.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;  //引入结果集ResultSet
import java.sql.SQLException;
import java.util.ArrayList; //引入数组指针



//学生的实现类
public class StudentImplement {
	//定义分页查询每页显示的条数
	private int size=5;
	//用"-"将数据串成字符串,才能进一步向数据库做插入等操作
	public String joinString(String[] hobby) {
		String joinhobby = "";
		for (String temp : hobby) {
			joinhobby += temp + "-";
		}
		return joinhobby.substring(0, joinhobby.length() - 1);
	}
	
	
	//---------查询实现函数开始----------//
	public ArrayList<Student> queryStudent(String name){
		ArrayList<Student> students=new ArrayList<Student>();//查询结果为好多对象,把这些对象放到一个集合里,所以这里先声明一个数组
		Connection con=null;
		PreparedStatement pre=null;
		MySqlConn conns=new MySqlConn();
		con=conns.getConnection();
		ResultSet rs=null;
		try {
			pre=con.prepareStatement("select * from student s left join grade g on s.gid=g.gid where name like ?");
			pre.setString(1,"%"+name+"%"); //模糊查询要用"%"+xx+"%"
			rs=pre.executeQuery();  //得到结果集
			while(rs.next()){
				Student student=new Student();
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setPassword(rs.getString("password"));
				student.setHobby(rs.getString("hobby"));
				Grade grade=new Grade();
				grade.setGid(rs.getInt("gid"));
				grade.setGname(rs.getString("gname"));
				student.setGrade(grade);
				students.add(student);//将查询到的一个个对象放到一个声明好的数组集合students里
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return students; //返回行到的集合
	}
	//--------分页查询函数的实现--------------//
	//此函数和上面的查询函数名字和返回值一样,但传入的参数不一样,此为函数的重载!!
	public ArrayList<Student> queryStudent(String name,int currentPage){
		ArrayList<Student> students=new ArrayList<Student>();//查询结果为好多对象,把这些对象放到一个集合里,所以这里先声明一个数组
		Connection con=null;
		PreparedStatement pre=null;
		MySqlConn conns=new MySqlConn();
		con=conns.getConnection();
		ResultSet rs=null;
		try {
			pre=con.prepareStatement("select top "+size+" * from student s left join grade g on s.gid=g.gid where name like ? and id not in (select top ((?-1)*?) id from student where name like ?)");
			pre.setString(1,"%"+name+"%"); //模糊查询要用"%"+xx+"%"
			pre.setInt(2, currentPage);
			pre.setInt(3,size);
			pre.setString(4,"%"+name+"%");
			rs=pre.executeQuery();  //得到结果集
			while(rs.next()){
				Student student=new Student();
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setPassword(rs.getString("password"));
				student.setHobby(rs.getString("hobby"));
				Grade grade=new Grade();
				grade.setGid(rs.getInt("gid"));
				grade.setGname(rs.getString("gname"));
				student.setGrade(grade);
				students.add(student);//将查询到的一个个对象放到一个声明好的数组集合students里
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return students; //返回行到的集合
	}
	//--------更新实现函数开始---------------//
	public void updateStudent(Student student){
		Connection con=null;
		PreparedStatement pre=null;
		MySqlConn conns=new MySqlConn();
		con=conns.getConnection();
		try {
			pre=con.prepareStatement("update student set name=?,password=? where id=?");
			pre.setString(1,student.getName()); 
			pre.setString(2,student.getPassword());
			pre.setInt(3, student.getId());
			pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//---------删除实现函数的开始-------------//
	public void deleteStudent(int id){
		Connection con=null;
		PreparedStatement pre=null;
		MySqlConn conns=new MySqlConn();
		con=conns.getConnection();
		try {
			pre=con.prepareStatement("delete from student where id=?");
			pre.setInt(1,id); 
			pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//---------插入实现函数开始----------//
	public void saveStudent(Student student){
		//连接数据库,完成数据的录入操作
		//System.out.println("---saveStudent---");
		Connection con=null;
		PreparedStatement pre=null;
		MySqlConn conns=new MySqlConn();
		con=conns.getConnection();
		try {
			pre=con.prepareStatement("insert into student(name,password,hobby)values(?,?,?)");
			pre.setString(1,student.getName());
			pre.setString(2, student.getPassword());
			pre.setString(3, student.getHobby());
			pre.executeUpdate();
			System.out.print("恭喜您,操作成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	//根据关键字查询总页数
		public int countPage(String sname){
			int countPage=0;   //存储总页数
			// 连接数据库 完成数据的录入操作
			Connection con = null;
			PreparedStatement pre = null;
			ResultSet rs=null;
			MySqlConn conns = new MySqlConn();
			con = conns.getConnection();
			try {
				pre = con.prepareStatement("SELECT COUNT(*) total FROM student WHERE name like ?"); //total相当于把查询结果加个列名!!
				pre.setString(1, "%" + sname + "%");
				rs=pre.executeQuery();
				if(rs.next()){
					//表达式 ? 值 :值
					int total=rs.getInt("total");
					countPage=total%size==0?total/5:total/5+1;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
			return countPage;
		}
		
}
