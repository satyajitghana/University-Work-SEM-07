<%@ page contentType = "text/html;charset=utf-8" %>
<html>
    <head>
    <title>Register</title>
        
    <script>
    
        function validateForm() {
            var username = document.regform.username.value;
            var password = document.regform.password.value;
            var fullname = document.regform.fullname.value;
            var usnno    = document.regform.usnno.value;
            
            if (username == "") {
                alert("username cannot be blank");
                return false;
            } else if (password == "") {
                alert("password cannot be blank");
                return false;
            } else if (fullname == "") {
                alert("fullname cannot be blank");
                return false;
            } else if (usnno == "") {
                alert("usnno cannot be blank");
                return false;
            } else if (password.length < 6) {
                alert("password must be at least 6 characters long");
                return false;
            }
            
            return true;
        }
        
    </script>
    </head>
    
    <body style="text-align:center; margin:auto">
        <h1>Register</h1>
        <br/>
        <center>
            <form align="center" name="regform" action="register" method="post" onsubmit="return validateForm();">
                <table>
                    <tr>
                        <td>username</td>
                        <td><input type="text" name="username" /></td>
                    </tr>
                    <tr>
                        <td>password</td>
                        <td><input type="password" name="password" /></td>
                    </tr>
                    <tr>
                        <td>full name</td>
                        <td><input type="text" name="fullname" /></td>
                    </tr>
                    <tr>
                        <td>usn no</td>
                        <td><input type="text" name="usnno" /></td>
                    </tr>
                    <tr>
                        <td>dept</td>
                        <td>
                            <select name="dept">
                              <option value="CSE">CSE</option>
                              <option value="EEE">EEE</option>
                              <option value="ECE">ECE</option>
                              <option value="CIVIL">CIVIL</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>course</td>
                        <td>
                            <select name="course">
                              <option value="B.Tech">B.Tech</option>
                              <option value="M.Tech">M.Tech</option>
                            </select>
                        </td>
                    </tr>
                </table>
                <br/>
                <input type="submit" value="register" />
            </form>
        </center>
    </body>
</html>