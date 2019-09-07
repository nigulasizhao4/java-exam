package edu.prj.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.ldap.Rdn;

import com.mysql.jdbc.PreparedStatement;

import edu.prj.bean.PaperItem;
import edu.prj.dao.PaperItemDao;
import edu.util.DbPub;

public class PaperItemDaoImpl implements PaperItemDao{

	@Override
	public Long insert(PaperItem bean)  {
		   java.sql.Connection conn=null;
		   PreparedStatement stmt=null;
		   Integer num=0;
	 	   conn = DbPub.getConn();
		   String sql="insert into paperItem (paperid,questionid,answer,score)values(?,?,?,?)";
		   List<Object> paramsList = new ArrayList<Object>();
		   paramsList.add(bean.getPaperId());
		   paramsList.add(bean.getQuestionId());
		   paramsList.add(bean.getAnswer());
		   paramsList.add(bean.getScore());
		 
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
		   String sql="delete from paperItem where itemid= ? ";
		   List<Object> paramsList = new ArrayList<Object>();
		   paramsList.add(id);
		    Object[] params = paramsList.toArray();	   
		    num=DbPub.update(conn, sql, params);
          
	        DbPub.colse(conn, stmt, null);
			return num.longValue();
	}



	@Override
	public List<PaperItem> list() {
		  java.sql.Connection conn=null;
		   PreparedStatement stmt=null;
		   ResultSet rs=null;
		   conn = DbPub.getConn();
		   String sql="select * from paperItem";
		   rs=DbPub.query(conn, sql);
		    List<PaperItem> bean=new ArrayList<>();
			try {
				while (rs.next()) {
				bean.add(toBean(rs));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		DbPub.colse(conn, stmt, rs);
		return (List<PaperItem>) bean;
	}
	
	private PaperItem toBean(ResultSet rs) throws SQLException{
		PaperItem bean= new PaperItem();
	    bean.setItemId(rs.getInt("itemid"));
	    bean.setPaperId(rs.getInt("paperid"));
	    bean.setQuestionId(rs.getInt("questionid"));
	    bean.setAnswer(rs.getString("answer"));
	    bean.setScore(rs.getDouble("score"));
		return bean;	
	}


	@Override
	public Long count() {
		java.sql.Connection conn=null;
		conn = DbPub.getConn();
		Long num=0l;
		List<Object> list=new ArrayList<>();
		String sql="select count(1) from paperItem where 1=1";
		Object[] params = list.toArray();
		num=DbPub.queryScalearLong(conn, sql, params);
		DbPub.closeConnection(conn);
		return num;
	}

	@Override
	public PaperItem loadName(String name) {
		java.sql.Connection conn=null;
		PaperItem bean=null;
		 PreparedStatement stmt=null;
		 ResultSet rs=null;
		 conn = DbPub.getConn();
		 String sql=("select * from paperItem where roomname= ?");
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
		String sql="select count(1) from paperItem where bookname=?";
		Object[] params = list.toArray();
		num=DbPub.queryScalearLong(conn, sql, params);
		DbPub.closeConnection(conn);
		return num;
	}

	@Override
	public PaperItem load(Long id) {
		// TODO Auto-generated method stub
				java.sql.Connection conn=null;
				PaperItem bean=null;
				 PreparedStatement stmt=null;
				 ResultSet rs=null;
				 conn = DbPub.getConn();
				 String sql=("select * from paperItem where itemid= ?");
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
	public List<PaperItem> listByPId(Integer id) {
		  List<PaperItem>bean1=new ArrayList<>();
		   java.sql.Connection conn=null;
		   ResultSet rs=null;
		   conn = DbPub.getConn();
		   String sql="select * from PaperItem where paperid = ? ";
		    List<Object> list=new ArrayList<Object>();
			list.add(id);
			 Object[] params = list.toArray();
		  rs=DbPub.query(conn,sql,params);
		  PaperItem bean=null;
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
