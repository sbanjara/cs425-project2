
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
    
    public HashMap getUserInfo(String username) {
        
        HashMap<String, String> results = null;
        String query;
        PreparedStatement pstatement = null;
        ResultSet resultset = null;
        boolean hasresults;
        
        try {
     
            Connection conn = getConnection();
            
            query = "SELECT * FROM 'user' WHERE username = ?";
            
            pstatement = conn.prepareStatement(query);
            
            pstatement.setString(1,username); 
            hasresults = pstatement.execute();
            
            if(hasresults) {
                
                resultset = pstatement.getResultSet();
                
                if(resultset.next()) {
                    
                    String id = resultset.getString("id");
                    String displayname = resultset.getString("displayname");
                    results = new HashMap();
                    results.put("id", id);
                    results.put("displayname", displayname);
                }
                 
            }
            
        }
        
        catch (SQLException e) { System.err.print(e.toString()); }
 
        return results;
        
    }
    
}
