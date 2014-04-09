package com.athena.level;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.athena.database.GetConnection;
import com.mysql.jdbc.ResultSet;

public class LevelDAO {
	private Connection conn;
	private PreparedStatement pstat;
	String sql="";
	private static Map<Integer, LevelBean> levellist = new HashMap<Integer, LevelBean>();
	
	public static LevelBean getDiscount(int level){
		return levellist.get(level);
	}
	
	public boolean addLevel(LevelBean level) throws SQLException{
	   conn = GetConnection.getConnection();
	   sql = "select * from member where level=?";
	   boolean result = false;
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
			
			pstat.setDouble(1, level.getDiscount());
			pstat.setLong(2, level.getLevel());
			result = pstat.executeUpdate() > 0?true:false;
	   }
	   else 
	   {
		   rs1.close();
		   pstat.close();
		   sql = "insert into member(level,discount) values(?,?)";
		   pstat = conn.prepareStatement(sql);
		   pstat.setLong(1, level.getLevel());
		   pstat.setDouble(2, level.getDiscount());
		   result = pstat.executeUpdate()>0?true:false;
	   }
	   levellist.put(level.getLevel(),level);
	   pstat.close();
	   conn.close();
	   return result;
	}
	
	public boolean delLevel(LevelBean level) throws SQLException{
		   conn = GetConnection.getConnection();
		   sql = "delete from member where level=?";
		   boolean result = false;
		   pstat = conn.prepareStatement(sql);
		  
		   pstat.setLong(1, level.getLevel());
		  
		   result = pstat.executeUpdate()>0?true:false;
		   levellist.remove(level.getLevel());
		   pstat.close();
		   conn.close();
		   return result;
	}
	
	public Map<Integer, LevelBean> getAllLevel() throws SQLException{
		conn = GetConnection.getConnection();
		sql = "select * from member";
		pstat = conn.prepareStatement(sql);
		ResultSet rs1 = (ResultSet) pstat.executeQuery();
		levellist.clear();
		while (rs1.next())
		{
			LevelBean level = new LevelBean();
			level.setLevel(rs1.getInt(1));
			level.setDiscount(rs1.getDouble(2));
			levellist.put(level.getLevel(),level);
		}
		pstat.close();
		conn.close();
		return levellist;
		
	}
	
	public JSONArray getAllLevelJSON() {
		JSONArray result = new JSONArray();
		Iterator<?> iter = levellist.entrySet().iterator(); 
		while (iter.hasNext()) { 
		    @SuppressWarnings("rawtypes")
			Map.Entry entry = (Map.Entry) iter.next(); 
		    LevelBean level = (LevelBean)entry.getValue();
		    JSONObject jsonObject = JSONObject.fromObject(level);
			result.add(jsonObject);
		}
		return result;
	}

}