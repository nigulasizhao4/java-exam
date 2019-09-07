package edu.prj.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.ldap.Rdn;

import com.mysql.jdbc.PreparedStatement;

import edu.prj.bean.Grade;
import edu.prj.dao.GradeDao;
import edu.util.DbPub;

public class GradeDaoImpl implements GradeDao{

	@Override
	public Long insert(Grade bean)  {
		   java.sql.Connection conn=null;
		   PreparedStatement stmt=null;
		   Integer num=0;
	 	   conn = DbPub.getConn();
	 	  SimpleDateFormat   lFormat; 
          //格式可以自己根据需要修改   
	 	   Date date = null;
           lFormat  =    new   SimpleDateFormat("yyyyMMddHHmmss");   
           String   gRtnStr   =   lFormat.format(new Date()); 
		   String sql="insert into grade (gradeName,createOn,createBy,updateOn,updateBy)values(?,?,?,?,?)";
		   List<Object> paramsList = new ArrayList<Object>();
		   paramsList.add(bean.getGradeName());
		   bean.setCreateOn(gRtnStr);
		   paramsList.add(gRtnStr);
		   paramsList.add(bean.getCreateBy());	
		   try {
			date = lFormat.parse("00000000000000");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   paramsList.add(date);
		   paramsList.add("无");
		 
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
		   String sql="delete from grade where gradeid= ? ";
		   List<Object> paramsList = new ArrayList<Object>();
		   paramsList.add(id);
		    Object[] params = paramsList.toArray();	   
		    num=DbPub.update(conn, sql, params);
          
	        DbPub.colse(conn, stmt, null);
			return num.longValue();
	}

	@Override
	public Long update(Grade bean) {
		 java.sql.Connection conn=null;
		   PreparedStatement stmt=null;
		   Integer num=0;
	 	   conn = DbPub.getConn();
	 	  SimpleDateFormat   lFormat; 
          //格式可以自己根据需要修改   
           lFormat  =    new   SimpleDateFormat("yyyyMMddHHmmss");   
           String   gRtnStr   =   lFormat.format(new Date()); 
		   String sql="update grade set gradename=?, updateOn = ?, updateBy = ?  where gradeid = ?";
		   
		   List<Object> paramsList = new ArrayList<Object>();
		   paramsList.add(bean.getGradeName());
		   bean.setUpdateOn(gRtnStr);
		   paramsList.add(gRtnStr);
		   paramsList.add(bean.getUpdateBy());
		   paramsList.add(bean.getGradeId());
		   Object[] params = paramsList.toArray();		   
		   num=DbPub.update(conn, sql, params);
	        DbPub.colse(conn, stmt, null);
			return num.longValue();
	}

	@Override
	public List<Grade> list() {
		  java.sql.Connection conn=null;
		   PreparedStatement stmt=null;
		   ResultSet rs=null;
		   conn = DbPub.getConn();
		   String sql="select * from grade";
		   rs=DbPub.query(conn, sql);
		    List<Grade> bean=new ArrayList<>();
			try {
				while (rs.next()) {
				bean.add(toBean(rs));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		DbPub.colse(conn, stmt, rs);
		return (List<Grade>) bean;
	}
	private Grade toBean(ResultSet rs) throws SQLException{
		Grade bean= new Grade();       
			bean.setGradeId(rs.getInt("gradeId"));		 
			bean.setGradeName(rs.getString("gradeName"));		
			bean.setCreateOn(rs.getDate("createon").toString());		
			bean.setCreateBy(rs.getString("createBy"));
			
			bean.setUpdateOn(rs.getDate("updateOn").toString()); 
			bean.setUpdateBy(rs.getString("updateby"));
		return bean;	
	}


	@Override
	public Long count() {
		java.sql.Connection conn=null;
		conn = DbPub.getConn();
		Long num=0l;
		List<Object> list=new ArrayList<>();
		String sql="select count(1) from grade where 1=1";
		Object[] params = list.toArray();
		num=DbPub.queryScalearLong(conn, sql, params);
		DbPub.closeConnection(conn);
		return num;
	}

	@Override
	public Grade loadName(String name) {
		java.sql.Connection conn=null;
		Grade bean=null;
		 PreparedStatement stmt=null;
		 ResultSet rs=null;
		 conn = DbPub.getConn();
		 String sql=("select * from grade where gradename= ?");
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
		String sql="select count(1) from grade where bookname=?";
		Object[] params = list.toArray();
		num=DbPub.queryScalearLong(conn, sql, params);
		DbPub.closeConnection(conn);
		return num;
	}

	@Override
	public Grade load(Long id) {
		// TODO Auto-generated method stub
				java.sql.Connection conn=null;
				Grade bean=null;
				 PreparedStatement stmt=null;
				 ResultSet rs=null;
				 conn = DbPub.getConn();
				 String sql=("select * from grade where gradeid= ?");
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
	public List<Grade> listByName(String name) {
		  List<Grade>bean1=new ArrayList<>();
		   java.sql.Connection conn=null;
		   ResultSet rs=null;
		   conn = DbPub.getConn();
		   String sql="select * from Grade where gradename like ? ";
		    List<Object> list=new ArrayList<Object>();
		    name="%"+name+"%";
			list.add(name);
			 Object[] params = list.toArray();
		  rs=DbPub.query(conn,sql,params);
		  Grade bean=null;
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
