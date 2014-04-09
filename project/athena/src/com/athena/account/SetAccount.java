package com.athena.account;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.athena.level.LevelBean;
import com.athena.level.LevelDAO;

/**
 * Servlet implementation class SetAccount
 */
@WebServlet("/SetAccount")
public class SetAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		   response.setCharacterEncoding("utf-8");
		   String option = request.getParameter("option");
		   String id = request.getParameter("id");
		   if(id == null)
			   return;
		   int userID = Integer.parseInt(id);
		   boolean result = false;
		   try {
				result = AccountDAO.addAccount(userID);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   PrintWriter out=response.getWriter();
	       out.println(result?"1":"0");
	       out.close();
	}

}
