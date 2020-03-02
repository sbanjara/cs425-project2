#  Employment Database Application
   It provides an employment portal for the users (students with a special need) via which they can apply for the jobs. The back-end is written in Java (Java Servlets, Java Beans, and Java Server Pages) and JavaScript/jQuery (AJAX) where as the front-end is written in HTML/CSS.
   It was the final project of my CS 425 (Web App Development) class. 
##
## Description
   Once the user launches this application, they are prompted to the login page as shown below,
   
   ![picture](employment1.PNG)
   
   After entering the username and password (**Username: jobseeker and Password: jobseeker**), the user is prompted to a page as shown below. They can add and look up the skills and jobs.
   
   ![picture](employment2.PNG)
   
   When the user selects the "Skills List", they are prompted to a page as shown below. The user can select their skills, and the skills that are previously selected and stored in the database will automatically be checked.
   
   ![picture](employment3.PNG)
   
   Once the user selects the skills and submits, they are prompted to a page with jobs list. The jobs are listed according to the user's skills. The jobs will automatically be checked, if the user's skills matches the skills that are required for the job. This page looks like below,
   
   ![picture](employment4.png)
   
   Once the user selects the jobs and submits them, the job report is created. The job report is a pdf file which consists of student's information, jobs selected, and the skills that the user possess for those jobs.
   
##
## Structure
  The directory structure of the source code is as follows:

    project root     (root directory of project, "cs425-project2")
               |
                - src             (root directory of the source code)
               |    |           
               |    - java   (all packages go here)
               |    |     |
               |    |     - edu.jsu.mcis.cs425.project2    
               |    |     |                          |
               |    |     |                          - BeanApplicant.java (Java Bean class which holds the user information)
               |    |     |                          |
               |    |     |                          - Database.java (Database class which interacts with the database)
               |    |     |                          |
               |    |     |                          - JobReport.java (Java Servlet which creates a pdf file which entails job info)
               |
                - web
                    |
                    - main
                    |     |
                    |     - Reports (Folder which contains JobReport.jasper file which creates a pdf file) 
                    |     |
                    |     - applicants_jobs.jsp (Web page which displays the list of jobs) 
                    |     | 
                    |     - applicant_main.jsp (Main page of the user. Holds information of skills and jobs) 
                    |     | 
                    |     - applicant-report.jsp 
                    |     |
                    |     - applicant_skills.jsp (Web page which displays the list of skills) 
                    |     |
                    |     - main.jsp (It checks if the userrole is applicant, if so directs to the applicant_main.jsp) 
                    |     |
                    |     - Userhome.css (CSS file which adds decoration) 
                    |
                    - META-INF
                    |        |
                    |        - context.xml (It adds resource for database pooling and realm for security purpose) 
                    |
                    - public
                    |      |
                    |      - login.jsp (Web page which displays the login setup) 
                    |      |
                    |      - logout.jsp (Logout page) 
                    |      |
                    |      - Login.css (CSS file which adds decoration for the login pgae) 
                    |
                    - WEB-INF
                    |      |
                    |      - web.xml (configuration of security and login) 
                    
##
##  Important Classes and their Methods
##
### Database Class
   Database class is responsible for connecting the database (cs425_p2). It either gets the data associated with the user or adds data into the database. It has following methods;
   ##
   i) **public HashMap<String, String> getUserInfo(String username)** = It retrieves user's information (id and displayname) and adds it to the Hashmap and returns it.
   ##
   ii) **public String getSkillsListAsHTML(int userid)** = It retrieves all the skills from the database and stores them in a string as a checkbox. If the applicants already have those skills, then the checkbox will be checked. It returns the skills string.
   ##
   iii) **public void setSkillsList(int id, String[] skills)** = It updates the user's skills in the applicants_to_skills table. When the user selects the skills, they are passed as an argument along with the user id. And the skills are updated.
   ##
   iv) **public String getJobsListAsHTML(int userid, String[] skills)** =  It retrieves all the jobs from the database that matches the user's skills. It returns the jobs as a string.
   ##
   v) **public void setJobsList(int id, String[] jobs)** = It updates the user's jobs in the applicants_to_jobs table. When the user selects the jobs, they are passed as an argument along with the user id. And the jobs are updated.
##
### BeanApplicant Class
   It hold user's information: username, displayname, id, and list of skills and jobs. It has five instance fields namely username, displayname, skills, jobs, and id. It has following methods along with the getter and setter methods for the instance fields;
   ##
   i) **public void setUserInfo()** = It pulls up information from the getUserInfo(String username) method of Database class, and sets the value of id and displayname.
   ##
   ii) **public String getSkillsList()** = It gets all the user's skills from the getSkillsListAsHTML(int userid) method of Database class.
   ##
   iii) **public String setSkillsList()** = It sets the user's skills by calling the setSkillsList(int id, String[] skills) method of Database class.
   ##
   iv) **public String getJobsList()** = It gets the user's jobs by calling the getJobsListAsHTML(int id, String[] skills) method of Database class.
   ##
   iii) **public String setJobsList()** = It sets the user's jobs by calling the setJobsList(int id, String[] jobs) method of Database class.
   ##
##
## Configuring the Database for the Database Pooling
   To run this application, you'll need to import the application database (**cs425_p2.sql**). After importing the database, you'll need to create database accounts for it, and grant these accounts the appropriate permissions in MySQL. To do this, please run the following command from an SQL client while logged in to MySQL as root:
 ##
         source C:\USER\Desktop\cs425_p2.sql ( It assumes the location of sql file is in Desktop. It may be different for you.)
         create user 'p2_auth'@'localhost' identified by 'CS425!Project2';
         create user 'p2_user'@'localhost' identified by 'CS425!Project2';
         grant select on cs425_p2.login to 'p2_auth'@'localhost';
         grant select on cs425_p2.user_to_role to 'p2_auth'@'localhost';
         grant all on cs425_p2.* to 'p2_user'@'localhost';
         flush privileges;
 ##
   The **"p2_user"** is for the database pool and **"p2_auth"** is for a restricted account for user authentication while login.
##
## Running this Project
   To run this project, you'll need MySQL, Java (particularly Java 8), Apache Tomcat Server, and Netbeans. If you don't have these tools, you can install them as described here (https://github.com/sbanjara/RequiredTools-Installation). Once you have all these required tools, you can clone this repository by running this command,
   ##
                     git clone https://github.com/sbanjara/cs425-project2
   ##
   Once this project is cloned in your workstation, you can open and run it in the Netbeans.
   
