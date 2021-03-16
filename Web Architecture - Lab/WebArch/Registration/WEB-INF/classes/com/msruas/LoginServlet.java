package com.msruas;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String email, Password;
		
		RegisterModel rm=new RegisterModel();
		
		email=request.getParameter("email");
		Password=request.getParameter("pword");
		
		
		
		String pw=rm.login(email);
		
		RequestDispatcher rd=null;
		if(pw.equals(Password)){
			
			rd=request.getRequestDispatcher("/loginsuccess.html");
		}
		else{
			
			rd=request.getRequestDispatcher("/login_fail.html");
			
		}
		rd.forward(request, response);
		
	}
}
