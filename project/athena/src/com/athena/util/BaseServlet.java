package com.athena.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.athena.log.Log;

public class BaseServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		Log.info(arg0.getRequestURI()+"?" + arg0.getQueryString());
		super.service(arg0, arg1);
	}

}
