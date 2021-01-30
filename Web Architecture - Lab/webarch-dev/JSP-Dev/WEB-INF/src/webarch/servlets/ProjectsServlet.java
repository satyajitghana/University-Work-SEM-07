package webarch.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webarch.dao.ProjectDao;
import webarch.models.Project;

import javax.servlet.*;  
import javax.servlet.http.*;  

public class ProjectsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        List<Project> projects;
        
        if (request.getParameterMap().containsKey("projectname")) {
            String projectname = request.getParameter("projectname");
            projects = ProjectDao.findProjects(projectname);
        } else {
            projects = new ArrayList<>();
        }
        
        request.setAttribute("projects", projects);

        RequestDispatcher rd = request.getRequestDispatcher("projects.jsp");
        rd.include(request, response);
        
    }
}


