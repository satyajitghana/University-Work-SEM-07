package com.sportaccessories;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServelet extends HttpServlet{
	public void service(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException 
	{	
		RegisterModel rm = new RegisterModel();
		int x =0;
		String c_id, name,email,pword,dno,street, city,state,zip;
		c_id= request.getParameter("c_id");
		name= request.getParameter("name");
		email= request.getParameter("email");
		pword= request.getParameter("pword");
		dno= request.getParameter("dno");
		street= request.getParameter("street");
		city= request.getParameter("city");
		state= request.getParameter("state");
		zip= request.getParameter("zip");
		
		RequestDispatcher rd=null;
		x= rm.register(c_id,name,email,pword,dno,street,city,state,zip);
		if(x==1){
			
			rd=request.getRequestDispatcher("/login.html");
		}
		else{
			
			rd=request.getRequestDispatcher("/registration_fail.html");		
		}
		rd.forward(request, response);
	}
}
