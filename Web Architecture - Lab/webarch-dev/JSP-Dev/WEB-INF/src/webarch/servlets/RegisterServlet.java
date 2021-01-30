package webarch.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webarch.dao.UserDao;

import javax.servlet.*;  
import javax.servlet.http.*;  

public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
        rd.include(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        String usnno = request.getParameter("usnno");
        String dept = request.getParameter("dept");
        String course = request.getParameter("course");
        
        boolean registerStatus = UserDao.registerUser(username, password, fullname, usnno, dept, course);
        
        
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.println("<script type=\"text/javascript\">");
        pw.println("alert('Register Status: [ "+registerStatus+"] ');");
        pw.println("</script>");
        
        // if register success then send to login page
        // RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
        
        RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
        rd.include(request, response);
    }
}
