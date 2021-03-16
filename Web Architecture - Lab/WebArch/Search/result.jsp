<%@ page language="java" import="java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
<font color="red" size="6" face="monotype corsiva"> SPORT ONLINE</font><br>
<font color="blue" size="5" > Search Results </font><br>

<%
ArrayList<String> al=(ArrayList)request.getAttribute("PROD_DETAILS");

for( int i=0; i<al.size();i++)
{ 
	
    out.print(al.get(i)+" ");
    
    
    
    }
%>


</center>

</body>
</html>