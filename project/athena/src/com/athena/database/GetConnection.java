package com.athena.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
* 
* ��JDBC�ķ���������ݿ������
*
*/
public class GetConnection {
	//ͨ����̬����ע���������������
	
	public static Connection getConnection(){
	   String driver = "com.mysql.jdbc.Driver";
	   String url = "jdbc:mysql://localhost:3306/athena";
	   Connection con = null;
	   try {
	    Class.forName(driver);
	    try {
	     con = DriverManager.getConnection(url,"root","root");
	    } catch (SQLException e) {
	     e.printStackTrace();
	    }
	   } catch (ClassNotFoundException e) {
	    e.printStackTrace();
	   }
	   System.out.println("�ѻ�����ݿ������");
	   return con;
	}
	/*public static void main(String args[]){
	   getConnection();
	}*/
}