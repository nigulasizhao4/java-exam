package edu.prj.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import edu.prj.bean.Teacher;
import edu.prj.dao.TeacherDao;
import edu.util.DbPub;

public class TeacherDaoImpl implements TeacherDao{

	@Override
	public Long insert(Teacher bean) throws SQLException {
		   java.sql.Connection conn=null;
		   PreparedStatement stmt=null;
		   Integer num=0;
	 	   conn = DbPub.getConn();
		   String sql="insert into teacher (teacherid,loginname,loginpass,nickname,isdisabled)values(?,?,?,?,?)";
		   List<Object> paramsList = new ArrayList<Object>();
		   paramsList.add(bean.getTeacherid());
		   paramsList.add(bean.getLoginname());
		   paramsList.add(bean.getLoginpass());
		   paramsList.add(bean.getNickname());
		   paramsList.add(bean.getIsdisabled());
		  
		   Object[] params = paramsList.toArray();	   
		   num=DbPub.update(conn, sql, params);
	        DbPub.colse(conn, stmt, null);
			return num.longValue();
	}

	@Override
	public Long delete(Long id) {
		   java.sql.Connection conn=null;
		   PreparedStatement stmt=null;
		   Integer num=0;
	 	   conn = DbPub.getConn();       
		   String sql="delete from teacher where teacherid= ? ";
		   List<Object> paramsList = new ArrayList<Object>();
		   paramsList.add(id);
		    Object[] params = paramsList.toArray();	   
		    num=DbPub.update(conn, sql, params);
          
	        DbPub.colse(conn, stmt, null);
			return num.longValue();
	}

	@Override
	public Long update(Teacher bean) {
		 java.sql.Connection conn=null;
		   PreparedStatement stmt=null;
		   Integer num=0;
	 	   conn = DbPub.getConn();
		   String sql="update teacher set loginname =?, loginpass=? , nickname= ?, isdisabled=? where teacherid = ?";
		   
		   List<Object> paramsList = new ArrayList<Object>();
		   paramsList.add(bean.getLoginname());
		   paramsList.add(bean.getLoginpass());
		   paramsList.add(bean.getNickname());
		   paramsList.add(bean.getIsdisabled());
		   paramsList.add(bean.getTeacherid());
		  // paramsList.add(bean.getStuId());
		   Object[] params = paramsList.toArray();
		   
		   num=DbPub.update(conn, sql, params);
	        DbPub.colse(conn, stmt, null);
			return num.longValue();
	}

	@Override
	public List<Teacher> list() {
		  java.sql.Connection conn=null;
		   PreparedStatement stmt=null;
		   ResultSet rs=null;
		   conn = DbPub.getConn();
		   String sql="select * from teacher";
		   rs=DbPub.query(conn, sql);
		    List<Teacher> bean=new ArrayList<>();
			try {
				while (rs.next()) {
				bean.add(toBean(rs));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		DbPub.colse(conn, stmt, rs);
		return (List<Teacher>) bean;
	}
	private Teacher toBean(ResultSet rs) throws SQLException{
		Teacher bean= new Teacher();
	    bean.setTeacherid(rs.getInt("teacherid"));
	    bean.setLoginname(rs.getString("loginname"));
	    bean.setLoginpass(rs.getString("loginpass"));
	    bean.setNickname(rs.getString("nickname"));
	    bean.setIsdisabled(rs.getInt("isdisabled"));
		return bean;	
	}


	@Override
	public Long count() {
		java.sql.Connection conn=null;
		conn = DbPub.getConn();
		Long num=0l;
		List<Object> list=new ArrayList<>();
		String sql="select count(1) from teacher where 1=1";
		Object[] params = list.toArray();
		num=DbPub.queryScalearLong(conn, sql, params);
		DbPub.closeConnection(conn);
		return num;
	}

	@Override
	public Teacher loadName(String name) {
		java.sql.Connection conn=null;
		Teacher bean=null;
		 PreparedStatement stmt=null;
		 ResultSet rs=null;
		 conn = DbPub.getConn();
		 String sql=("select * from teacher where loginname= ?");
		 List<Object> list=new ArrayList<Object>();
		 list.add(name);
		 Object[] params = list.toArray();
		 rs=DbPub.query(conn, sql,params);
		 try {
			 if(rs.next()) {
				bean=toBean(rs);
			 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		 DbPub.colse(conn, stmt, rs);
		return bean;
	}

	@Override
	public Long countByName(String name) {
		java.sql.Connection conn=null;
		conn = DbPub.getConn();
		Long num=0l;
		List<Object> list=new ArrayList<>();
		String sql="select count(1) from teacher where bookname=?";
		Object[] params = list.toArray();
		num=DbPub.queryScalearLong(conn, sql, params);
		DbPub.closeConnection(conn);
		return num;
	}

	@Override
	public Teacher load(Long id) {
		// TODO Auto-generated method stub
				java.sql.Connection conn=null;
				Teacher bean=null;
				 PreparedStatement stmt=null;
				 ResultSet rs=null;
				 conn = DbPub.getConn();
				 String sql=("select * from teacher where teacherid= ?");
				 List<Object> list=new ArrayList<Object>();
				 list.add(id);
				 Object[] params = list.toArray();
				 rs=DbPub.query(conn, sql,params);
				 try {
					 if(rs.next()) {				 
						bean=toBean(rs);
					 }
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				 DbPub.colse(conn, stmt, rs);
				return bean;
	}

	@Override
	public List<Teacher> listByName(String name) {
		  List<Teacher>bean1=new ArrayList<>();
		   java.sql.Connection conn=null;
		   ResultSet rs=null;
		   conn = DbPub.getConn();
		   String sql="select * from Teacher where nickname like ? ";
		    List<Object> list=new ArrayList<Object>();
		    name="%"+name+"%";
			list.add(name);
			 Object[] params = list.toArray();
		  rs=DbPub.query(conn,sql,params);
		  Teacher bean=null;
			try {
				while(rs.next()) {
					bean=toBean(rs);
				    bean1.add(bean);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			DbPub.colse(conn, null, rs);
		return  bean1;
	}

	


}

