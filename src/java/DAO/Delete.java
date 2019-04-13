/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.user;

/**
 *
 * @author Admin
 */
public class Delete {
    private Connection conn;
    private ResultSet results;
    private user DeletedUsr = new user();

    public Delete() {
        Properties props = new Properties();
        InputStream instr = getClass().getResourceAsStream("dbConn.properties");//let me read content of a file 
        try {
            props.load(instr);
        } catch (IOException ex) {
            Logger.getLogger(Delete.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            instr.close();
        } catch (IOException ex) {
            Logger.getLogger(Delete.class.getName()).log(Level.SEVERE, null, ex);
        }
        String driver = props.getProperty("driver.name");
        String url = props.getProperty("server.name");
        String username = props.getProperty("user.name");
        String password = props.getProperty("user.password");

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Delete.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            Logger.getLogger(Delete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void DeleteUser(String username, String password) 
             throws SQLException {
        String sql = "DELETE * FROM socialnetworkdb.user WHERE user_name=? and password=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        this.results = ps.executeQuery();
    }
    
    public void DeleteFriend(String UserName) 
            throws SQLException{
        String sql = "DELETE * FROM socialnetworkdb.Friend WHERE UserName=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, UserName);
        this.results = ps.executeQuery();
     
    }
}


