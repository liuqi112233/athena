package com.athena.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.athena.database.GetConnection;
import com.mysql.jdbc.ResultSet;

public class AccountDAO {

	public static boolean addAccount(int id) throws SQLException{
		Connection conn = GetConnection.getConnection();
		String sql = "select * from account where id=?";
	   boolean result = false;
	   PreparedStatement pstat = conn.prepareStatement(sql);
	  
	   pstat.setLong(1, id);
	  
	   ResultSet rs1 = (ResultSet) pstat.executeQuery();
	   pstat.clearBatch();
	   if (!rs1.next()) 
	   {
		   rs1.close();
		   pstat.close();
		   sql = "insert into account(id) values(?)";
		   pstat = conn.prepareStatement(sql);
		   pstat.setLong(1, id);
		   result = pstat.executeUpdate()>0?true:false;
	   }
	   pstat.close();
	   conn.close();
	   return result;
	}
	
	public static boolean delAccount(int id){
		return false;
	}
	
	public static int addBalance(int id,double count){
		return 0;
	}
	
	public static int delBalance(int id,double count){
		return 0;
	}
	
	public static int getBalance(int id){
		return 0;
	}

}