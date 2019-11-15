<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="applicant" class="edu.jsu.mcis.cs425.project2.BeanApplicant" scope="session" />

<!DOCTYPE html>

<html>
    
   <head>
       
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Select Skills</title>
      
      <link rel="stylesheet" type="text/css" href="Userhome.css">
      
   </head>
   
   <body>
       
        <header id="header">
            
            <img src="jsu_logo.jpg" height= 130px id="jsulogo">		
            
	</header>
       
        <div id="container">
            
            <div id="form_area">
       
                <form id="skillsform" name="skillsform" method="post" action="applicant_jobs.jsp">

                    <fieldset>

                        <legend>Select Your Skills:</legend>
                        <jsp:getProperty name="applicant" property="skillsList" />

                        <input type="submit" value="Submit" />

                    </fieldset>

                </form>
            
            </div>
                
        </div>
                
        <footer id="footer">

            <p>Jacksonville State University</p>
            <p>700 Pelham Rd N,Jacksonville, AL 36265<p>
            <p>Phone:1-800-231-5291</p>
            <p>Email: info@jsu.edu</p>
		
	</footer>
            
   </body>
   
</html>