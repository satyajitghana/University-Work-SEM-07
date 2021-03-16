package com.usecase;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





public class SearchServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String pname;
		
		MainModel rm = new MainModel();
		
		
		pname = request.getParameter("search");
		RequestDispatcher rd = null;
		
		ArrayList<String> al = rm.search(pname);
		request.setAttribute("PROD_DETAILS", al);
		if(al!=null)
		{
			rd = request.getRequestDispatcher("/result.jsp");
		}
		else
		{
			rd = request.getRequestDispatcher("/prod_not_found.html");
		}
		rd.forward(request, response);
	}

}
