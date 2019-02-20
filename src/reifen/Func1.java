package reifen; 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Func1 {
   
    private Connection conn = null;
    private Statement stat = null;
    private PreparedStatement pstat = null;
   
    public Connection getConnection() {
        if(this.conn == null) {        
            try {
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection("jdbc:sqlite:reifen.sqlite");
            } catch (Exception e){
                System.out.println("Create Connection error");
            }
        }
        return this.conn;
    }
   
    public Statement getStat() {
        if(this.conn == null) {
            this.getConnection();
        }
       
        if(this.stat == null && this.conn != null) {
            try {
                stat = this.conn.createStatement();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                System.out.println("Create Statment error");
                e.printStackTrace();
            }
        }
        return stat;
    }
 
    public PreparedStatement getStat(String sql) {
        if(this.conn == null) {
            this.getConnection();
        }
       
        if(this.pstat == null && this.conn != null) {
            try {
                pstat = this.conn.prepareStatement(sql);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                System.out.println("Create Statment error");
                e.printStackTrace();
            }
        }
        return pstat;
    }
   
    public boolean closeStatement() {
        boolean res = false;
       
        if(this.stat != null) {
            try {
                this.stat.close();
                res = true;
            } catch (Exception e) {
                System.out.println("Close Statement error");
            }
        } else if(this.pstat != null) {
            try {
                this.pstat.close();
                res = true;
            } catch (Exception e) {
                System.out.println("Close P Statement error");
            }
        }
       
        return res;
    }
   
    public boolean closeConnection() {
        boolean res = false;       
       
        if(this.conn != null) {
            try {
                this.conn.close();
                res = true;
            } catch (Exception e) {
                System.out.println("Close Connection error");
            }
        }
       
        return res;
    }
 
}