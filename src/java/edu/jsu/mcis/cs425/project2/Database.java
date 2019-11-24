
package edu.jsu.mcis.cs425.project2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Database {
    
    public Connection getConnection() {
        
        Connection conn = null;
        
        try {
            
            Context envContext = new InitialContext();
            Context initContext  = (Context)envContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)initContext.lookup("jdbc/db_pool");
            conn = ds.getConnection();
            
        }        
        catch (Exception e) { e.printStackTrace(); }
        
        return conn;

    }
    
    public HashMap<String, String> getUserInfo(String username) {
        
        HashMap<String, String> results = null;
        String query;
        Connection conn = null;
        PreparedStatement pstatement = null;
        ResultSet resultset = null;
        boolean hasresults;
        
        try {
     
            conn = getConnection();
            
            query = "SELECT * FROM user WHERE username = ?";
            
            pstatement = conn.prepareStatement(query);
            
            pstatement.setString(1,username); 
            hasresults = pstatement.execute();
            
            if(hasresults) {
                
                resultset = pstatement.getResultSet();
                
                if(resultset.next()) {
                    
                    int id = resultset.getInt("id");
                    System.err.println();
                    String displayname = resultset.getString("displayname");
                    results = new HashMap();
                    results.put("id", String.valueOf(id));
                    results.put("displayname", displayname);
                }
                 
            }
            
        }
        
        catch (SQLException e) { e.printStackTrace(); }
 
        return results;
        
    }
    
    public String getSkillsListAsHTML(int userid) {
        
        StringBuilder skills = new StringBuilder();
        
        String query, checked = "";
        int id = 0;
        Connection conn = null;
        PreparedStatement pstatement = null;
        ResultSet resultset = null;
        boolean hasresults;
        
        try {
     
            conn = getConnection();
            
            query = "SELECT * FROM skills LEFT JOIN applicants_to_skills ON "
                   + "( skills.id = applicants_to_skills.skillsid AND applicants_to_skills.userid = ?)";
            
            pstatement = conn.prepareStatement(query);
            
            pstatement.setInt(1,userid); 
            hasresults = pstatement.execute();
            
            if(hasresults) {
                
                resultset = pstatement.getResultSet();
                
                while(resultset.next()) {
                    
                    if(userid == resultset.getInt("userid")) {
                        checked = "checked";
                    }
                    
                    String skill = resultset.getString("description");
                    id = resultset.getInt("id");
                    skills.append("<p><input type=\"checkbox\" name=\"skills\" value=");
                    skills.append("\"").append(id).append("\"").append("id=\"skills_").append(id).append("\" ").append(checked).append(">");
                    skills.append("<label for=\"skills_").append(id).append("\">").append(skill).append("</label></p>");
                    
                    checked = "";
                    
                }
                 
            }
            
        }
        
        catch (SQLException e) { e.printStackTrace(); }
        
        return skills.toString(); 
        
    }
    
    public void setSkillsList(int id, String[] skills) {
        
        Connection conn = getConnection();
        PreparedStatement pstatement = null;
        String query = null;
        
        try {
            
            query = "DELETE FROM applicants_to_skills WHERE userid = ?";
            
            pstatement = conn.prepareStatement(query);
            pstatement.setInt(1, id);
            
            pstatement.execute();
            
            query = "INSERT INTO applicants_to_skills(userid, skillsid) VALUES(?, ?)";
            pstatement = conn.prepareStatement(query);
            
            for( String s: skills ){
                
                pstatement.setInt(1, id);
                pstatement.setInt(2, Integer.parseInt(s));
                pstatement.addBatch();
                
            } 
            
            int[] r = pstatement.executeBatch();
            
        } 
        
        catch (SQLException e) { e.printStackTrace(); }
        
    }
    
    public String getJobsListAsHTML(int userid, String[] skills) {
        
        StringBuilder jobs = new StringBuilder();
        
        String query, checked = "";
        int id = 0;
        Connection conn = null;
        PreparedStatement pstatement = null;
        ResultSet resultset = null;
        boolean hasresults;
        
        try {
     
            conn = getConnection();
            
            /*
            
            SELECT * FROM applicants_to_jobs LEFT JOIN jobs ON (applicants_to_jobs.userid = ? AND applicants_to_jobs.jobsid = jobs.id)
         
            */
                    
            query = "SELECT jobs.id, jobs.name, a.userid FROM jobs "
                    + "LEFT JOIN (SELECT * FROM applicants_to_jobs WHERE userid = ?) "
                    + "AS a ON jobs.id = a.jobsid "
                    + "WHERE jobs.id IN (SELECT jobsid AS id FROM "
                    + "(applicants_to_skills JOIN skills_to_jobs ON applicants_to_skills.skillsid = skills_to_jobs.skillsid) "
                    + "WHERE applicants_to_skills.userid = ?) ORDER BY jobs.name";
           
            pstatement = conn.prepareStatement(query);
            
            pstatement.setInt(1, userid); 
            pstatement.setInt(2, userid);
            hasresults = pstatement.execute();
            
            if(hasresults) {
                
                resultset = pstatement.getResultSet();
                
                while(resultset.next()) {
                    
                    if(userid == resultset.getInt("userid")) {
                        checked = "checked";
                    }
                    
                    String job = resultset.getString("name");
                    id = resultset.getInt("id");
                    jobs.append("<p><input type=\"checkbox\" name=\"jobs\" value=");
                    jobs.append("\"").append(id).append("\"").append("id=\"jobs_").append(id).append("\" ").append(checked).append(">");
                    jobs.append("<label for=\"jobs_").append(id).append("\">").append(job).append("</label></p>");
                    
                    checked = "";
                    
                }
                 
            }
            
        }
        
        catch (SQLException e) { e.printStackTrace(); }
        
        return jobs.toString(); 
        
    }
    
    public void setJobsList(int id, String[] jobs) {
        
        Connection conn = getConnection();
        PreparedStatement pstatement = null;
        String query = null;
        
        try {
            
            query = "DELETE FROM applicants_to_jobs WHERE userid = ?";
            
            pstatement = conn.prepareStatement(query);
            pstatement.setInt(1, id);
            
            pstatement.execute();
            
            query = "INSERT INTO applicants_to_jobs(userid, jobsid) VALUES(?, ?)";
            pstatement = conn.prepareStatement(query);
            
            for( String s: jobs ){
                
                pstatement.setInt(1, id);
                pstatement.setInt(2, Integer.parseInt(s));
                pstatement.addBatch();
                
            } 
            
            int[] r = pstatement.executeBatch();
      
        } 
        
        catch (SQLException e) { e.printStackTrace(); }
        
    }
    
}
