package com.athena.level;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.athena.database.GetConnection;
import com.mysql.jdbc.ResultSet;

public class LevelDAO {
	private Connection conn;
	private PreparedStatement pstat;
	String sql="";
	/**
	* 
	* ÓÃ»§µÇÂ¼
	*/
	public boolean addLevel(LevelBean level) throws SQLException{
	   conn = GetConnection.getConnection();
	   sql = "select * from member where level=?";
	  
	   pstat = conn.prepareStatement(sql);
	  
	   pstat.setLong(1, level.getLevel());
	  
	   ResultSet rs1 = (ResultSet) pstat.executeQuery();
	   pstat.clearBatch();
	   if (rs1.next())
	   {
		    rs1.close();
		    pstat.close();
		    sql = "update member set discount=? where level=?";
		    pstat = conn.prepareStatement(sql);
			
			pstat.setFloat(1, level.getDiscount());
			pstat.setLong(2, level.getLevel());
			pstat.executeUpdate();
	   }
	   else 
	   {
		   rs1.close();
		   pstat.close();
		   sql = "insert into member(level,discount) values(?,?)";
		   pstat = conn.prepareStatement(sql);
		   pstat.setLong(1, level.getLevel());
		   pstat.setFloat(2, level.getDiscount());
		   pstat.execute();
	   }
	   
	   pstat.close();
	   conn.close();
	   return true;
	}

}