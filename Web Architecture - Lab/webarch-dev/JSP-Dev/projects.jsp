<%@ page contentType = "text/html;charset=utf-8" %>
<%@ page import="java.util.List" %>
<%@ page import="webarch.models.Project" %>
<html>
    <head>
    <title>Project</title>
    </head>
    
    <body style="text-align:center; margin:auto">
        <h1>Search for Project Details</h1>
        <br/>
        <center>
            <form align="center" action="projects" method="get">
                <table>
                    <tr>
                        <td>project name</td>
                        <td>
                            <input type="text" name="projectname" />
                        </td>
                    </tr>
                </table>
                <br />
                <input type="submit" value="search" />
            </form>
            <br />
            <h2>Results</h2>
            <table>
                <tr>
                    <th>Id</th>
                    <th>Project Leader</th>
                    <th>Project Name</th>
                    <th>Mentor Name</th>
                    <th>Department</th>
                    <th>Category</th>
                </tr>
                <%
                    List<Project> projects = (List<Project>) request.getAttribute("projects");
                    if (projects != null) {
                        if (projects.size() == 0) {
                %>
                            <h4> No results found</h4>
                <%
                        } else {
                            for (Project project: projects) {
                %>
                                <tr>
                                    <td>
                                        <%out.write(project.getId().toString());%>
                                    </td>
                                    <td>
                                        <%out.write(project.getProjectLeaderRegno());%>
                                    </td>
                                    <td>
                                        <%out.write(project.getProjectName());%>
                                    </td>
                                    <td>
                                        <%out.write(project.getMentorName());%>
                                    </td>
                                    <td>
                                        <%out.write(project.getDepartment());%>
                                    </td>
                                    <td>
                                        <%out.write(project.getCategory());%>
                                    </td>
                                </tr>
                <%
                            }
                        }
                    }
                %>
            </table>
        </center>
    </body>
</html>