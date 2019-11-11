<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

    <head>

        <title>Log In</title>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8">

    </head>

    <body>

        <form id="loginform" name="loginform" method="POST" action="j_security_check" accept-charset="UTF-8">

            <fieldset>
                
                <legend>Login Setup</legend>
                
                <p>
                    <label for="j_username">Username:</label>
                    <input type="text" name="j_username" id="j_username">
                </p>
                
                <p>
                    <label for="j_password">Password:</label>
                    <input type="password" name="j_password" id="j_password">
                </p>
                
                <p>
                    <input type="Submit" value="Submit">
                </p>
                
            </fieldset>

        </form>

        <b>How to Log In</b>

        <ul>
            <li>Enter the Username and Password that you were provided.</li>                    
        </ul>

        <%

            String result = request.getParameter("error");

            if (result != null) {

        %>

        <div id="loginerror" style="color: red;">

            <b>There was a problem processing your login request.</b>

            <ul>
                <li>Please try entering your Username and Password again.</li>
            </ul>

        </div>

        <%

            }

        %>
        
        <script type="text/javascript">

        </script>

    </body>

</html>