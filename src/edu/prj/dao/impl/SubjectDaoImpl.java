package edu.prj.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.ldap.Rdn;

import com.mysql.jdbc.PreparedStatement;

import edu.prj.bean.Subject;
import edu.prj.dao.SubjectDao;
import edu.util.DbPub;

public class SubjectDaoImpl implements SubjectDao{

	@Override
	public Long insert(Subject bean)  {
		   java.sql.Connection conn=null;
		   PreparedStatement stmt=null;
		   Integer num=0;
	 	   conn = DbPub.getConn();
		   String sql="insert into subject (subjectname,status)values(?,?)";
		   List<Object> paramsList = new ArrayList<Object>();
		   paramsList.add(bean.getSubjectName());
		   paramsList.add(bean.getStatus());
		 
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
		   String sql="delete from subject where subjectid= ? ";
		   List<Object> paramsList = new ArrayList<Object>();
		   paramsList.add(id);
		    Object[] params = paramsList.toArray();	   
		    num=DbPub.update(conn, sql, params);
          
	        DbPub.colse(conn, stmt, null);
			return num.longValue();
	}

	@Override
	public Long update(Subject bean) {
		 java.sql.Connection conn=null;
		   PreparedStatement stmt=null;
		   Integer num=0;
	 	   conn = DbPub.getConn();
		   String sql="update subject set subjectname=? , status=? where subjectid = ?";
		   
		   List<Object> paramsList = new ArrayList<Object>();
		   paramsList.add(bean.getSubjectName());
		   paramsList.add(bean.getStatus());
		   paramsList.add(bean.getSubjectId());
		 
		   Object[] params = paramsList.toArray();
		   
		   num=DbPub.update(conn, sql, params);
	        DbPub.colse(conn, stmt, null);
			return num.longValue();
	}

	@Override
	public List<Subject> list() {
		  java.sql.Connection conn=null;
		   PreparedStatement stmt=null;
		   ResultSet rs=null;
		   conn = DbPub.getConn();
		   String sql="select * from subject";
		   rs=DbPub.query(conn, sql);
		    List<Subject> bean=new ArrayList<>();
			try {
				while (rs.next()) {
				bean.add(toBean(rs));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		DbPub.colse(conn, stmt, rs);
		return (List<Subject>) bean;
	}
	private Subject toBean(ResultSet rs) throws SQLException{
		Subject bean= new Subject();
	    bean.setSubjectId(rs.getInt("subjectid"));
        bean.setSubjectName(rs.getString("subjectname"));
        bean.setStatus(rs.getString("status"));
		return bean;	
	}


	@Override
	public Long count() {
		java.sql.Connection conn=null;
		conn = DbPub.getConn();
		Long num=0l;
		List<Object> list=new ArrayList<>();
		String sql="select count(1) from subject where 1=1";
		Object[] params = list.toArray();
		num=DbPub.queryScalearLong(conn, sql, params);
		DbPub.closeConnection(conn);
		return num;
	}

	@Override
	public Subject loadName(String name) {
		java.sql.Connection conn=null;
		Subject bean=null;
		 PreparedStatement stmt=null;
		 ResultSet rs=null;
		 conn = DbPub.getConn();
		 String sql=("select * from subject where subjectname= ?");
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
		String sql="select count(1) from subject where bookname=?";
		Object[] params = list.toArray();
		num=DbPub.queryScalearLong(conn, sql, params);
		DbPub.closeConnection(conn);
		return num;
	}

	@Override
	public Subject load(Long id) {
		// TODO Auto-generated method stub
				java.sql.Connection conn=null;
				Subject bean=null;
				 PreparedStatement stmt=null;
				 ResultSet rs=null;
				 conn = DbPub.getConn();
				 String sql=("select * from subject where subjectid= ?");
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
	public List<Subject> listByName(String name) {
		  List<Subject>bean1=new ArrayList<>();
		   java.sql.Connection conn=null;
		   ResultSet rs=null;
		   conn = DbPub.getConn();
		   String sql="select * from Subject where subjectname like ? ";
		    List<Object> list=new ArrayList<Object>();
		    name="%"+name+"%";
			list.add(name);
			 Object[] params = list.toArray();
		  rs=DbPub.query(conn,sql,params);
		  Subject bean=null;
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
