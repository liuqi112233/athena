package com.athena.level;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class SetMemberLevel
 */
@WebServlet("/SetMemberLevel")
public class SetMemberLevel extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetMemberLevel() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() throws ServletException {
    	System.out.println("test");
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {

	   response.setContentType("text/html");
	   doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	   response.setContentType("text/html");
	   response.setCharacterEncoding("utf-8");
	   String option = request.getParameter("option");
	   String level = request.getParameter("level");
	   String discount = request.getParameter("discount");
	   LevelBean lvlbean = new LevelBean();
	   int lvl = Integer.parseInt(level);
	   lvlbean.setLevel(lvl);
	   LevelDAO dao = new LevelDAO();
	   boolean result = false;
	   if(option == null || !option.equals("delete")){
		   double count = Double.parseDouble(discount);
		   lvlbean.setDiscount(count);
		   try {
			   result  = dao.addLevel(lvlbean);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }
	   else if(option.equals("delete")){
		   try {
			   result  = dao.delLevel(lvlbean);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }
	   JSONArray list = dao.getAllLevelJSON();
	   PrintWriter out=response.getWriter();
       out.println(list);
       out.close();
		   
	}

}
