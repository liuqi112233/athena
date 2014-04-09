package com.athena.game;

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

public class GameDAO {
	private static Map<Integer, GameBean> gamelist = new HashMap<Integer, GameBean>();
	
	public static GameBean getGame(int id){
		return gamelist.get(id);
	}

	public static void getAllGame() throws SQLException{
		Connection conn = GetConnection.getConnection();
		String sql = "select * from game";
		PreparedStatement pstat = conn.prepareStatement(sql);
		ResultSet rs1 = (ResultSet) pstat.executeQuery();
		gamelist.clear();
		while (rs1.next())
		{
			GameBean game = new GameBean();
			game.setId(rs1.getInt(1));
			game.setName(rs1.getString(2));
			game.setCost(rs1.getDouble(3));
			game.setEquipment(rs1.getString(4));
			game.setOnly(rs1.getInt(5));
			gamelist.put(game.getId(), game);
		}
		pstat.close();
		conn.close();
		
	}
	
	public static JSONArray getAllLevelJSON() {
		JSONArray result = new JSONArray();
		Iterator<?> iter = gamelist.entrySet().iterator(); 
		while (iter.hasNext()) { 
		    @SuppressWarnings("rawtypes")
			Map.Entry entry = (Map.Entry) iter.next(); 
		    GameBean level = (GameBean)entry.getValue();
		    JSONObject jsonObject = JSONObject.fromObject(level);
			result.add(jsonObject);
		}
		return result;
	}

}