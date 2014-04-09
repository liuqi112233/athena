package com.athena.account;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.athena.util.BaseServlet;

/**
 * Servlet implementation class SetAccount
 */
@WebServlet("/SetAccount")
public class SetAccount extends BaseServlet {
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
		   if(id == null || option == null)
			   return;
		   int userID = Integer.parseInt(id);
		   boolean result = false;
		   double balance = 0;
		   if(option.equals("addAccount")){
			   try {
					result = AccountDAO.addAccount(userID);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   
			   PrintWriter out=response.getWriter();
		       out.println(result?"1":"0");
		       out.close();
		   } else if(option.equals("delAccount")){
			   try {
					result = AccountDAO.delAccount(userID);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   
			   PrintWriter out=response.getWriter();
		       out.println(result?"1":"0");
		       out.close();
		   } else if(option.equals("addBalance")){
			   try {
				   String balanceStr = request.getParameter("balance");
				   double count = Double.valueOf(balanceStr);
				   if(count <= 0)
					   return;
				   balance = AccountDAO.addBalance(userID, count);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   JSONObject r = new JSONObject();
			   r.accumulate("balance", balance);
			   PrintWriter out=response.getWriter();
		       out.println(r);
		       out.close();
		   } else if(option.equals("redBalance")){
			   try {
				   String balanceStr = request.getParameter("balance");
				   double count = Double.valueOf(balanceStr);
				   if(count <= 0)
					   return;
				   balance = AccountDAO.redBalance(userID, count);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   JSONObject r = new JSONObject();
			   r.accumulate("balance", balance);
			   PrintWriter out=response.getWriter();
		       out.println(r);
		       out.close();
		   }
	}

}
