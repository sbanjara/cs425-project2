<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

    <head>

        <title>Log In</title>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
        
        <link rel="stylesheet" type="text/css" href="login.css">

    </head>

    <body>

        <header id="header">
            
            <img src="jsu_logo.jpg" height= 130px id="jsulogo">		
            
	</header>
        
        <div id=container> 
            
            <div id="loginarea">
            
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
                
                <div id="logininfo">

                    <b>How to Log In</b>

                    <ul>
                        <li>Enter the Username and Password that you were provided.</li>                    
                    </ul>
                
                </div>

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
                
            </div>
        
        </div>
            
        <footer id="footer">

            <p>Jacksonville State University</p>
            <p>700 Pelham Rd N,Jacksonville, AL 36265<p>
            <p>Phone:1-800-231-5291</p>
            <p>Email: info@jsu.edu</p>
		
	</footer>
            
        <script type="text/javascript">

        </script>

    </body>

</html>