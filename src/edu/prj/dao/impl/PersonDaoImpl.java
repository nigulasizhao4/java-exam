package edu.prj.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import edu.prj.bean.Manager;
import edu.prj.bean.Person;
import edu.prj.bean.Student;
import edu.prj.bean.Teacher;
import edu.prj.dao.PersonDao;
import edu.util.DbPub;

public class PersonDaoImpl implements PersonDao {

	@Override
	public Long changePass(Person person) {
		 java.sql.Connection conn=null;
		   PreparedStatement stmt=null;
		   Integer num=0;
	 	   conn = DbPub.getConn();
	
		  if(person instanceof Student) {

			Student bean = (Student) person;
			String sql = "update student set loginpass =? where loginname = ?";		  
			   List<Object> paramsList = new ArrayList<Object>();		
			   paramsList.add(bean.getLoginpass());			
			   paramsList.add(bean.getLoginname());
			   Object[] params = paramsList.toArray();			   
			   num=DbPub.update(conn, sql, params);
		   }
		if(person instanceof Teacher) {
			Teacher bean = (Teacher) person;

			String sql = "update teacher set loginpass =? where loginname = ?";		  
			   List<Object> paramsList = new ArrayList<Object>();		
			   paramsList.add(bean.getLoginpass());			
			   paramsList.add(bean.getLoginname());
			   Object[] params = paramsList.toArray();			   
			   num=DbPub.update(conn, sql, params);
		}
		if(person instanceof Manager) {
			Manager bean = (Manager) person;
			String sql = "update manager set loginpass =? where loginname = ?";		  
			   List<Object> paramsList = new ArrayList<Object>();		
			   paramsList.add(bean.getLoginpass());			
			   paramsList.add(bean.getLoginname());
			   Object[] params = paramsList.toArray();			   
			   num=DbPub.update(conn, sql, params);
		}
		  DbPub.colse(conn, stmt, null);
			return num.longValue();
	}

	@Override
	public Person loadName(String name,Person person) {
		java.sql.Connection conn=null;
		 PreparedStatement stmt=null;
		 ResultSet rs=null;
		 Person bean = null;
		 conn = DbPub.getConn();
		 if(person instanceof Manager) {
		 Manager bean1 = new Manager();
		 String sql=("select * from manager where loginname= ?");
		 List<Object> list=new ArrayList<Object>();
		 list.add(name);
		 Object[] params = list.toArray();
		 rs=DbPub.query(conn, sql,params);
		 try {
			 if(rs.next()) {
				bean1=mtoBean(rs);
			 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		    DbPub.colse(conn, stmt, rs);
			return bean1;
		 } if(person instanceof Student) {
             Student bean1 = new Student();
			 String sql=("select * from student where loginname= ?");
			 List<Object> list=new ArrayList<Object>();
			 list.add(name);
			 Object[] params = list.toArray();
			 rs=DbPub.query(conn, sql,params);
			 try {
				 if(rs.next()) {
					bean1=stoBean(rs);
				 }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			 DbPub.colse(conn, stmt, rs);
				return bean1;
			 }
		 if(person instanceof Teacher) {
             Teacher bean1 = new Teacher();
			 String sql=("select * from teacher where loginname= ?");
			 List<Object> list=new ArrayList<Object>();
			 list.add(name);
			 Object[] params = list.toArray();
			 rs=DbPub.query(conn, sql,params);
			 try {
				 if(rs.next()) {
					bean1=ttoBean(rs);
				 }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			 DbPub.colse(conn, stmt, rs);
				return bean1;
			 }
		 return bean;

	}
	private Manager mtoBean(ResultSet rs) throws SQLException{
		Manager bean= new Manager();
	    bean.setLoginpass(rs.getString("loginpass"));
		return bean;	
	}
	private Student stoBean(ResultSet rs) throws SQLException{
		Student bean= new Student();
	    bean.setLoginpass(rs.getString("loginpass"));
		return bean;	
	}
	private Teacher ttoBean(ResultSet rs) throws SQLException{
		Teacher bean= new Teacher();
	    bean.setLoginpass(rs.getString("loginpass"));
		return bean;	
	}


}
