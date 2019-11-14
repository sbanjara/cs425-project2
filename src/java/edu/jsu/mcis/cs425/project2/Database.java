
package edu.jsu.mcis.cs425.project2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Database {
    
    private Connection getConnection() {
        
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
            
            System.err.print("Test0");
            
            if(hasresults) {
                
                System.err.print("Test1");
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
                    
                    if(userid == resultset.getInt("userid"))
                        checked = "checked";
                    
                    String skill = resultset.getString("description");
                    id = resultset.getInt("id");
                    skills.append("<input type=\"checkbox\" name=\"skills\" value=");
                    skills.append("\"").append(id).append("\"").append("id=\"skills_").append(id).append("\" ").append(checked).append(">");
                    skills.append("<label for=\"skills_").append(id).append("\">").append(skill).append("</label><br>");
                    
                }
                 
            }
            
        }
        
        catch (SQLException e) { e.printStackTrace(); }
        
        return skills.toString(); 
        
    }
    
}
