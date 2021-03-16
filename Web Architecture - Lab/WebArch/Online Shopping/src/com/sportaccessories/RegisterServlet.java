package com.sportaccessories;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class RegisterServlet extends HttpServlet {
	public void service(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException 
	{
		String fn,email,phone,gender,username,password;
		fn= request.getParameter("name");
		email= request.getParameter("email");
		phone= request.getParameter("phno");
		gender= request.getParameter("gen");
		username=request.getParameter("uname");
		password=request.getParameter("password");
		
	 	response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<h1>Sport Accessories<h1>");
		out.println("<center><h3>Registration is successful<h3>");
		out.println("<table>");
		out.println("<tr><td>First Name</td>"+fn+"<td></tr>");
		out.println("<tr><td>Email</td>"+email+"<td></tr>");
		out.println("<tr><td>Phone No.</td>"+phone+"<td></tr>");
		out.println("<tr><td>Gender</td>"+gender+"<td></tr>");
		out.println("<tr><td>User Name</td>"+username+"<td></tr>");
		out.println("</table>");
		out.println("<center>");
	}
}
