package com.athena.level;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	   String level = request.getParameter("level");
	   String discount = request.getParameter("discount");
	   LevelBean lvlbean = new LevelBean();
	   int lvl = Integer.parseInt(level);
	   float count = Float.parseFloat(discount);
	   lvlbean.setLevel(lvl);
	   lvlbean.setDiscount(count);
	   LevelDAO dao = new LevelDAO();
	   try {
		   dao.addLevel(lvlbean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
