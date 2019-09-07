package edu.prj.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import edu.prj.bean.ExamItem;
import edu.prj.dao.ExamItemDao;
import edu.util.DbPub;

public class ExamItemDaoImpl implements ExamItemDao{

	@Override
	public Long insert(ExamItem bean)  {
		   java.sql.Connection conn=null;
		   PreparedStatement stmt=null;
		   Integer num=0;
	 	   conn = DbPub.getConn();
		   String sql="insert into examItem (examid,questionid,stuanswer,stdanswer,stdscore,markresult,gainScore)values(?,?,?,?,?,?,?)";
		   List<Object> paramsList = new ArrayList<Object>();
		   paramsList.add(bean.getExamId());
		   paramsList.add(bean.getQuestionId());
		   paramsList.add(bean.getStuAnswer());
		   paramsList.add(bean.getStdAnswer());
		   paramsList.add(bean.getStdScore());
		   paramsList.add(bean.getMarkResult());
		   paramsList.add(bean.getGainScore());
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
		   String sql="delete from examItem where examItemid= ? ";
		   List<Object> paramsList = new ArrayList<Object>();
		   paramsList.add(id);
		    Object[] params = paramsList.toArray();	   
		    num=DbPub.update(conn, sql, params);
          
	        DbPub.colse(conn, stmt, null);
			return num.longValue();
	}

	@Override
	public List<ExamItem> list() {
		  java.sql.Connection conn=null;
		   PreparedStatement stmt=null;
		   ResultSet rs=null;
		   conn = DbPub.getConn();
		   String sql="select * from examItem";
		   rs=DbPub.query(conn, sql);
		    List<ExamItem> bean=new ArrayList<>();
			try {
				while (rs.next()) {
				bean.add(toBean(rs));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		DbPub.colse(conn, stmt, rs);
		return (List<ExamItem>) bean;
	}
	private ExamItem toBean(ResultSet rs) throws SQLException{
		ExamItem bean= new ExamItem();
		bean.setItemId(rs.getInt("itemid"));
	    bean.setExamId(rs.getInt("examid"));
	    bean.setQuestionId(rs.getInt("questionid"));
	    bean.setStuAnswer(rs.getString("stuanswer"));
	    bean.setStdAnswer(rs.getString("stdanswer"));
	    bean.setStdScore(rs.getDouble("stdscore"));
	    bean.setMarkResult(rs.getInt("markResult"));
	    bean.setGainScore(rs.getDouble("gainscore"));
		return bean;	
	}


	@Override
	public Long count() {
		java.sql.Connection conn=null;
		conn = DbPub.getConn();
		Long num=0l;
		List<Object> list=new ArrayList<>();
		String sql="select count(1) from examItem where 1=1";
		Object[] params = list.toArray();
		num=DbPub.queryScalearLong(conn, sql, params);
		DbPub.closeConnection(conn);
		return num;
	}

	@Override
	public ExamItem loadName(String name) {
		java.sql.Connection conn=null;
		ExamItem bean=null;
		 PreparedStatement stmt=null;
		 ResultSet rs=null;
		 conn = DbPub.getConn();
		 String sql=("select * from examItem where loginname= ?");
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
		String sql="select count(1) from examItem where bookname=?";
		Object[] params = list.toArray();
		num=DbPub.queryScalearLong(conn, sql, params);
		DbPub.closeConnection(conn);
		return num;
	}

	@Override
	public ExamItem load(Long id) {
		// TODO Auto-generated method stub
				java.sql.Connection conn=null;
				ExamItem bean=null;
				 PreparedStatement stmt=null;
				 ResultSet rs=null;
				 conn = DbPub.getConn();
				 String sql=("select * from examItem where examItemid= ?");
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
	public List<ExamItem> listById(Integer id) {
		  List<ExamItem>bean1=new ArrayList<>();
		   java.sql.Connection conn=null;
		   ResultSet rs=null;
		   conn = DbPub.getConn();
		   String sql="select * from ExamItem where examid=? ";
		    List<Object> list=new ArrayList<Object>();
			list.add(id);
			 Object[] params = list.toArray();
		  rs=DbPub.query(conn,sql,params);
		  ExamItem bean=null;
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
