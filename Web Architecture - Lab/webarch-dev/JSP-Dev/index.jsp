<%@ page contentType = "text/html;charset=utf-8" %>
<%@ page import="webarch.core.HelloWorld" %> 
<%
    HelloWorld h = new HelloWorld();
%>
<html>
    <head>
        <title>WebArch Lab</title>
    </head>
<body>
    message = <%=h.sayHello() %>
</body>	
</html>
