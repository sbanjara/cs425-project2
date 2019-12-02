<%@page import="java.util.Arrays"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="applicant" class="edu.jsu.mcis.cs425.project2.BeanApplicant" scope="session" />
<jsp:setProperty name="applicant" property="*" />

<%
   if (applicant.getSkills() != null) {
      applicant.setSkillsList();
   }
%>

<!DOCTYPE html>

<html>
    
    <head>
       
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Select Jobs</title>
      
     <link type="text/css" href="Userhome.css" rel="stylesheet">
       
    </head>
    
    <body>
        
        <header id="header">
			
            <img src="<%= request.getContextPath() %>/public/jsu_logo.jpg" height= 130px id="jsulogo">		
			
        </header>
        
        <div id="container">
            
            <div id="form_area">
       
                <form id="jobsform" name="jobsform" method="post" action="applicant_report.jsp">

                    <fieldset>

                        <legend>Select Your Job(s):</legend>
                        <jsp:getProperty name="applicant" property="jobsList" />
                        <input type="submit" value="Submit" />

                    </fieldset>

                </form>
                  
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