package edu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

public class DbPub {
    private static String driver="com.mysql.jdbc.Driver";
    private static String url="jdbc:mysql://localhost:3306/exam?useSSL=false&useUnicode=true&characterEncoding=utf-8";
    private static String user="root";
    private static String password="root";
   static {
    	try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
   public static Long queryScalearLong(Connection conn,String sql,Object...params) {
	   java.sql.PreparedStatement statement=null;
	   ResultSet rs=null;
	   long result=0l;
	   try {
		      statement=conn.prepareStatement(sql);
			 if(params!=null) {
				 for(int i=0;i<params.length;i++) {
					 statement.setObject(i+1, params[i]);
				 }
			 }
			   rs=statement.executeQuery();
			   if (rs.next()) {
				   result=rs.getLong(1);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("执行["+sql+"]出现错误。");
		}
	   return result;
   }
   public static Connection getConn() {
	   Connection conn=null;
	   try {
		conn = DriverManager.getConnection(url, user, password);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return conn;
   }
   public static void colse(Connection conn,Statement stmt,ResultSet rs) {
       closeResultSet(rs);
       
       closeStatement(stmt);
       closeConnection(conn);
   }
   public static ResultSet query(Connection conn,String sql,Object...params) {
	   ResultSet rs= null;
	   
	   java.sql.PreparedStatement statement=null;
	   try {
	      statement=conn.prepareStatement(sql);
		 if(params!=null) {
			 for(int i=0;i<params.length;i++) {
				 statement.setObject(i+1, params[i]);
			 }
		 }
		   rs=statement.executeQuery();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("执行["+sql+"]出现错误。");
	}
	   return rs;
	
	   
   }
   public static int update(Connection conn,String sql,Object...params) {
	   java.sql.PreparedStatement statement=null;
	   Integer num=0;
	   try {
		   statement=conn.prepareStatement(sql);
			 if(params!=null) {
				 for(int i=0;i<params.length;i++) {
					 statement.setObject(i+1, params[i]);
				 }
			 }
			 num=statement.executeUpdate();
			 
			  
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	    return num;	   
   }
   
   public static void closeConnection(Connection conn) {
       if(conn !=null) {
           try {
               conn.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
       //等待垃圾回收
       conn = null;
   }
   
   public static void closeStatement(Statement st) {
       if(st !=null) {
           try {
               st.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
       //等待垃圾回收
       st = null;
   }
   
   public static void closeResultSet(ResultSet rs) {
       if(rs !=null) {
           try {
               rs.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
       rs = null;
   }
}

