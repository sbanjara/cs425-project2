<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="applicant" class="edu.jsu.mcis.cs425.project2.BeanApplicant" scope="session" />
<jsp:setProperty name="applicant" property="username" value="<%= request.getRemoteUser() %>" />
<% applicant.setUserInfo(); %>

<!DOCTYPE html>

<html>
    
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Applicant Welcome Page</title>
        
        <link type="text/css" href="Userhome.css" rel="stylesheet">
       
    </head>
    
    <body>
        
        <header id="header">
			
            <img src="jsu_logo.jpg" height= 130px id="jsulogo">
			
        </header>
        
        <div id="container">
   
            <div id="student_description">
                
                <p><strong>Display Name: <jsp:getProperty name="applicant" property="displayname" /> </strong></p>
                <p><strong>User ID: <jsp:getProperty name="applicant" property="id" /> </strong></p>
                
            </div>
            
            <p id="welcome_message">Welcome, <jsp:getProperty name="applicant" property="username" />!</p>

            <div class="sidenav">
                
                <a href="applicant_skills.jsp">Skills List</a> 
                <a href="applicant_jobs.jsp">Jobs List</a>
                <a href="<%= request.getContextPath() %>/public/logout.jsp" target="_self">Log Out</a>
                
                
            </div>
        
        </div>
                
        <footer>
			
            <p>Jacksonville State University</p>
            <p>700 Pelham Rd N,Jacksonville, AL 36265<p>
            <p>Phone:1-800-231-5291</p>
            <p>Email: info@jsu.edu</p>

        </footer>
        
    </body>
    
</html>