package edu.prj.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.ldap.Rdn;

import com.mysql.jdbc.PreparedStatement;

import edu.prj.bean.Paper;
import edu.prj.dao.PaperDao;
import edu.util.DbPub;
import java.util.*;

public class PaperDaoImpl implements PaperDao{

	@Override
	public Long insert(Paper bean)  {
		   java.sql.Connection conn=null;
		   PreparedStatement stmt=null;
		   Integer num=0;
	 	   conn = DbPub.getConn();
	 	  SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");//小写的mm表示的是分钟 
//	 	 String dstr="2015-09-16"; 
//	 	 java.util.Date date=sdf.parse(dstr); 
		   String sql="insert into paper (paperName,totalscore,perscore,questionnum,subjectid,examminute,starton,endon,hasgenerate)values(?,?,?,?,?,?,?,?,?)";
		   List<Object> paramsList = new ArrayList<Object>();
		   paramsList.add(bean.getPaperName());
		   paramsList.add(bean.getTotalScore());
		   paramsList.add(bean.getPerScore());
		   paramsList.add(bean.getQuestionNum());
		   paramsList.add(bean.getSubjectId());
		   paramsList.add(bean.getExamMinute());
		   Date date,date1;
		try {
			 date = sdf.parse(bean.getStartOn());
			 paramsList.add(date);
			 date1 = sdf.parse(bean.getEndOn());
			 paramsList.add(date1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		   
		   paramsList.add(0);
	 
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
		   String sql="delete from paper where paperid= ? ";
		   List<Object> paramsList = new ArrayList<Object>();
		   paramsList.add(id);
		    Object[] params = paramsList.toArray();	   
		    num=DbPub.update(conn, sql, params);
          
	        DbPub.colse(conn, stmt, null);
			return num.longValue();
	}

	@Override
	public Long update(Paper bean) {
		 java.sql.Connection conn=null;
		   PreparedStatement stmt=null;
		   Integer num=0;
	 	   conn = DbPub.getConn();
	 	  SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");//小写的mm表示的是分钟 
		   String sql="update paper set paperName=?,totalscore=?,perscore=?,questionnum=?,subjectid = ?,examminute=?,starton=?,endon=? where paperid = ?";		   
		   List<Object> paramsList = new ArrayList<Object>();
		   paramsList.add(bean.getPaperName());
		   paramsList.add(bean.getTotalScore());
		   paramsList.add(bean.getPerScore());
		   paramsList.add(bean.getQuestionNum());
		   paramsList.add(bean.getSubjectId());
		   paramsList.add(bean.getExamMinute());
		   Date date,date1;
		try {
			 date = sdf.parse(bean.getStartOn());
			 paramsList.add(date);
			 date1 = sdf.parse(bean.getEndOn());
			 paramsList.add(date1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		   
//		paramsList.add(bean.getHasGenerate());
		   paramsList.add(bean.getPaperId());
		   
		   Object[] params = paramsList.toArray();	
		   num=DbPub.update(conn, sql, params);
	        DbPub.colse(conn, stmt, null);
			return num.longValue();
	}
	@Override
	public Long update1(Integer id) {
		 java.sql.Connection conn=null;
		   PreparedStatement stmt=null;
		   Integer num=0;
	 	   conn = DbPub.getConn();
	 	
		   String sql="update paper set hasgenerate=? where paperid = ?";		   
		   List<Object> paramsList = new ArrayList<Object>();
		   paramsList.add(1);		
		   paramsList.add(id);
		   
		   Object[] params = paramsList.toArray();	
		   num=DbPub.update(conn, sql, params);
	        DbPub.colse(conn, stmt, null);
			return num.longValue();
	}
	

	@Override
	public List<Paper> list() {
		  java.sql.Connection conn=null;
		   PreparedStatement stmt=null;
		   ResultSet rs=null;
		   conn = DbPub.getConn();
		   String sql="select * from paper";
		   rs=DbPub.query(conn, sql);
		    List<Paper> bean=new ArrayList<>();
			try {
				while (rs.next()) {
				bean.add(toBean(rs));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		DbPub.colse(conn, stmt, rs);
		return (List<Paper>) bean;
	}
	private Paper toBean(ResultSet rs) throws SQLException{
		Paper bean= new Paper();
	    bean.setPaperId(rs.getInt("paperid"));
        bean.setPaperName(rs.getString("paperName"));
        bean.setTotalScore(rs.getDouble("totalScore"));
        bean.setPerScore(rs.getDouble("perscore"));
        bean.setQuestionNum(rs.getInt("questionNum"));
        bean.setSubjectId(rs.getInt("subjectid"));
        bean.setExamMinute(rs.getInt("examminute"));
        bean.setStartOn(rs.getDate("starton").toString());
        bean.setEndOn(rs.getDate("endon").toString());
        bean.setHasGenerate(rs.getInt("hasgenerate"));
		return bean;	
	}


	@Override
	public Long count() {
		java.sql.Connection conn=null;
		conn = DbPub.getConn();
		Long num=0l;
		List<Object> list=new ArrayList<>();
		String sql="select count(1) from paper where 1=1";
		Object[] params = list.toArray();
		num=DbPub.queryScalearLong(conn, sql, params);
		DbPub.closeConnection(conn);
		return num;
	}

	@Override
	public Paper loadName(String name) {
		java.sql.Connection conn=null;
		Paper bean=null;
		 PreparedStatement stmt=null;
		 ResultSet rs=null;
		 conn = DbPub.getConn();
		 String sql=("select * from paper where paperName= ?");
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
		String sql="select count(1) from paper where bookname=?";
		Object[] params = list.toArray();
		num=DbPub.queryScalearLong(conn, sql, params);
		DbPub.closeConnection(conn);
		return num;
	}

	@Override
	public Paper load(Long id) {
		// TODO Auto-generated method stub
				java.sql.Connection conn=null;
				Paper bean=null;
				 PreparedStatement stmt=null;
				 ResultSet rs=null;
				 conn = DbPub.getConn();
				 String sql=("select * from paper where paperid= ?");
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
	public List<Paper> listByName(String name) {
		  List<Paper>bean1=new ArrayList<>();
		   java.sql.Connection conn=null;
		   ResultSet rs=null;
		   conn = DbPub.getConn();
		   String sql="select * from Paper where paperName like ? ";
		    List<Object> list=new ArrayList<Object>();
		    name="%"+name+"%";
			list.add(name);
			 Object[] params = list.toArray();
		  rs=DbPub.query(conn,sql,params);
		  Paper bean=null;
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
