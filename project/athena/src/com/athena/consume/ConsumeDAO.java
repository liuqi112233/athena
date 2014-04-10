package com.athena.consume;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import sun.org.mozilla.javascript.internal.Synchronizer;

import com.athena.database.GetConnection;
import com.athena.log.Log;
import com.mysql.jdbc.ResultSet;

public class ConsumeDAO {

	public synchronized static boolean addAccount(int id) throws SQLException{
		Connection conn = GetConnection.getConnection();
		String sql = "select * from account where id=?";
	   boolean result = false;
	   PreparedStatement pstat = conn.prepareStatement(sql);
	  
	   pstat.setLong(1, id);
	  
	   ResultSet rs1 = (ResultSet) pstat.executeQuery();
	   pstat.clearBatch();
	   if (!rs1.next()) 
	   {
		   
		   pstat.close();
		   sql = "insert into account(id) values(?)";
		   pstat = conn.prepareStatement(sql);
		   pstat.setLong(1, id);
		   result = pstat.executeUpdate()>0?true:false;
		   if(result == true)
			   Log.info("addAccount id:"+id);
	   }
	   rs1.close();
	   pstat.close();
	   conn.close();
	   return result;
	}
	
	public synchronized static boolean delAccount(int id) throws SQLException{
		Connection conn = GetConnection.getConnection();
		   String sql = "delete from account where id=?";
		   boolean result = false;
		   PreparedStatement pstat = conn.prepareStatement(sql);
		  
		   pstat.setLong(1, id);
		  
		   result = pstat.executeUpdate()>0?true:false;
		   if(result == true)
			   Log.info("delAccount id:"+id);
		   pstat.close();
		   conn.close();
		   return result;
	}
	
	public synchronized static double addBalance(int id,double count) throws SQLException{
		Connection conn = GetConnection.getConnection();
		String sql = "select balance from account where id=?";
		   boolean result = false;
		   double balance = 0;
		   PreparedStatement pstat = conn.prepareStatement(sql);
		  
		   pstat.setLong(1, id);
		  
		   ResultSet rs1 = (ResultSet) pstat.executeQuery();
		   pstat.clearBatch();
		   if (rs1.next()) 
		   {
			    balance = rs1.getDouble(1);
			   result = true;
				Log.info("getBalance id:"+id+" balance:"+balance);
		   }
		   rs1.close();
		   pstat.close();
		   if(result == true){
			   balance += count;
			    sql = "update account set balance=? where id=?";
			   PreparedStatement pstat1 = conn.prepareStatement(sql);
			   pstat1.setDouble(1, balance);
			   pstat1.setLong(2, id);
			  
			   result = pstat1.executeUpdate()>0?true:false;
			   if(result == true)
				   Log.info("addBalance id:"+id+" add balance:"+count);
			   pstat1.close();
		   }
		   
		   conn.close();
		return balance;
	}
	
	public synchronized static double redBalance(int id,double count) throws SQLException{
		Connection conn = GetConnection.getConnection();
		String sql = "select balance from account where id=?";
		   boolean result = false;
		   double balance = 0;
		   PreparedStatement pstat = conn.prepareStatement(sql);
		  
		   pstat.setLong(1, id);
		  
		   ResultSet rs1 = (ResultSet) pstat.executeQuery();
		   pstat.clearBatch();
		   if (rs1.next()) 
		   {
			    balance = rs1.getDouble(1);
			   result = true;
				Log.info("getBalance id:"+id+" balance:"+balance);
		   }
		   rs1.close();
		   pstat.close();
		   if(result == true){
			   balance -= count;
			    sql = "update account set balance=? where id=?";
			   PreparedStatement pstat1 = conn.prepareStatement(sql);
			   pstat1.setDouble(1, balance);
			   pstat1.setLong(2, id);
			  
			   result = pstat1.executeUpdate()>0?true:false;
			   if(result == true)
				   Log.info("redBalance id:"+id+" add balance:"+count);
			   pstat1.close();
		   }
		   
		   conn.close();
		return balance;
	}
	
	public synchronized static double getBalance(int id) throws SQLException{
		Connection conn = GetConnection.getConnection();
		String sql = "select balance from account where id=?";
	   boolean result = false;
	   PreparedStatement pstat = conn.prepareStatement(sql);
	  
	   pstat.setLong(1, id);
	  
	   ResultSet rs1 = (ResultSet) pstat.executeQuery();
	   pstat.clearBatch();
	   double balance = 0;
	   if (rs1.next()) 
	   {
		   balance = rs1.getDouble(1);
		   if(result == true)
			   Log.info("getBalance id:"+id+" balance:"+balance);
	   }
	   rs1.close();
	   pstat.close();
	   conn.close();
		return balance;
	}

}