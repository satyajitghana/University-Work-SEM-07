<%@ page contentType = "text/html;charset=utf-8" %>
<html>
    <head>
    <title>Login</title>
    </head>
    
    <body style="text-align:center; margin:auto">
        <h1>Login to RUAS LMS</h1>
        <br/>
        <center>
            <form align="center" action="login" method="post">
                <table>
                    <tr>
                        <td>username</td>
                        <td><input type="text" name="username" /></td>
                    </tr>
                    <tr>
                        <td>password</td>
                        <td><input type="password" name="password" /></td>
                    </tr>
                </table>
                <br/>
                <input type="submit" value="login" />
            </form>
        </center>
    </body>
</html>