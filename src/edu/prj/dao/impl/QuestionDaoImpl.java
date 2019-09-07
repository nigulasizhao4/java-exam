package edu.prj.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.ldap.Rdn;

import com.mysql.jdbc.PreparedStatement;

import edu.prj.bean.Question;
import edu.prj.dao.QuestionDao;
import edu.util.DbPub;

public class QuestionDaoImpl implements QuestionDao{

	@Override
	public Long insert(Question bean)  {
		   java.sql.Connection conn=null;
		   PreparedStatement stmt=null;
		   Integer num=0;
	 	   conn = DbPub.getConn();
		   String sql="insert into question (qtypeid,question,itema,itemb,itemc,itemd,answer,subjectid,tag)values(?,?,?,?,?,?,?,?,?)";
		   List<Object> paramsList = new ArrayList<Object>();
		   paramsList.add(bean.getqTypeId());
		   paramsList.add(bean.getQuestion());
		   paramsList.add(bean.getItemA());
		   paramsList.add(bean.getItemB());
		   paramsList.add(bean.getItemC());
		   paramsList.add(bean.getItemD());
		   paramsList.add(bean.getAnswer());
		   paramsList.add(bean.getSubjectId());
		   paramsList.add(bean.getTag());
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
		   String sql="delete from question where questionid= ? ";
		   List<Object> paramsList = new ArrayList<Object>();
		   paramsList.add(id);
		    Object[] params = paramsList.toArray();	   
		    num=DbPub.update(conn, sql, params);
          
	        DbPub.colse(conn, stmt, null);
			return num.longValue();
	}

	@Override
	public Long update(Question bean) {
		 java.sql.Connection conn=null;
		   PreparedStatement stmt=null;
		   Integer num=0;
	 	   conn = DbPub.getConn();
		   String sql="update question set question=?,itema=?,itemb=?,itemc=?,itemd=?,answer=?,tag=? where questionid = ?";
		   
		   List<Object> paramsList = new ArrayList<Object>();
		   paramsList.add(bean.getQuestion());
		   paramsList.add(bean.getItemA());
		   paramsList.add(bean.getItemB());
		   paramsList.add(bean.getItemC());
		   paramsList.add(bean.getItemD());
		   paramsList.add(bean.getAnswer());
		   paramsList.add(bean.getTag());
		   paramsList.add(bean.getQuestionId());
		   Object[] params = paramsList.toArray();
		   
		   num=DbPub.update(conn, sql, params);
	        DbPub.colse(conn, stmt, null);
			return num.longValue();
	}

	@Override
	public List<Question> list() {
		  java.sql.Connection conn=null;
		   PreparedStatement stmt=null;
		   ResultSet rs=null;
		   conn = DbPub.getConn();
		   String sql="select * from question";
		   rs=DbPub.query(conn, sql);
		    List<Question> bean=new ArrayList<>();
			try {
				while (rs.next()) {
				bean.add(toBean(rs));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		DbPub.colse(conn, stmt, rs);
		return (List<Question>) bean;
	}
	@Override
	public List<Question> listByQtype(Integer qtypeid,Integer subjectid) {
		  java.sql.Connection conn=null;
		   PreparedStatement stmt=null;
		   ResultSet rs=null;
		   conn = DbPub.getConn();
		   String sql="select * from question where qTypeid=? and subjectid=?";
		   List<Object> paramsList = new ArrayList<Object>();
		   paramsList.add(qtypeid);
		   paramsList.add(subjectid);
		   Object[] params = paramsList.toArray();
		   rs=DbPub.query(conn, sql,params);
		   
		    List<Question> bean=new ArrayList<>();
			try {
				while (rs.next()) {
				bean.add(toBean(rs));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		DbPub.colse(conn, stmt, rs);
		return (List<Question>) bean;
	}
	private Question toBean(ResultSet rs) throws SQLException{
		Question bean= new Question();
	    bean.setQuestionId(rs.getInt("questionid"));
        bean.setqTypeId(rs.getInt("qtypeid"));
        bean.setQuestion(rs.getString("question"));
        bean.setItemA(rs.getString("itema"));
        bean.setItemB(rs.getString("itemB"));
        bean.setItemC(rs.getString("itemC"));
        bean.setItemD(rs.getString("itemD"));
        bean.setAnswer(rs.getString("answer"));
        bean.setSubjectId(rs.getInt("subjectId"));
        bean.setTag(rs.getString("tag"));
		return bean;	
	}


	@Override
	public Long count() {
		java.sql.Connection conn=null;
		conn = DbPub.getConn();
		Long num=0l;
		List<Object> list=new ArrayList<>();
		String sql="select count(1) from question where 1=1";
		Object[] params = list.toArray();
		num=DbPub.queryScalearLong(conn, sql, params);
		DbPub.closeConnection(conn);
		return num;
	}

	@Override
	public Question loadName(String name) {
		java.sql.Connection conn=null;
		Question bean=null;
		 PreparedStatement stmt=null;
		 ResultSet rs=null;
		 conn = DbPub.getConn();
		 String sql=("select * from question where question= ?");
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
		String sql="select count(1) from question where bookname=?";
		Object[] params = list.toArray();
		num=DbPub.queryScalearLong(conn, sql, params);
		DbPub.closeConnection(conn);
		return num;
	}

	@Override
	public Question load(Long id) {
		// TODO Auto-generated method stub
				java.sql.Connection conn=null;
				Question bean=null;
				 PreparedStatement stmt=null;
				 ResultSet rs=null;
				 conn = DbPub.getConn();
				 String sql=("select * from question where questionid= ?");
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
	public List<Question> listByName(String name) {
		  List<Question>bean1=new ArrayList<>();
		   java.sql.Connection conn=null;
		   ResultSet rs=null;
		   conn = DbPub.getConn();
		   String sql="select * from Question where question like ? ";
		    List<Object> list=new ArrayList<Object>();
		    name="%"+name+"%";
			list.add(name);
			 Object[] params = list.toArray();
		  rs=DbPub.query(conn,sql,params);
		  Question bean=null;
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
