package model;

import java.sql.*;
import java.util.ArrayList;

public class HallDA {

    private String host = "jdbc:derby://localhost:1527/assignmentdb";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "Hall";
    private Connection conn;
    private PreparedStatement stmt;

    public HallDA() throws SQLException{
         createConnection();
    }
    
    //method that used to retrieve the hall record from db table named 'Hall'
    public Hall retrieveHall(String hallNo) throws SQLException{
        String queryStr = "SELECT * FROM " + tableName + " WHERE HALL_NO = ?"; 
        Hall hall = null; 

            stmt = conn.prepareStatement(queryStr); 
            stmt.setString(1, hallNo); 
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { 
                hall = new Hall(hallNo, rs.getInt(2), rs.getInt(3));
            }
        return hall; 
    }

 
    //get all hall no
    public ArrayList<String> getHallNo() throws SQLException{ 
        String hallNo = null;
        ArrayList<String> hall = new ArrayList<String>();
        String sqlQuery = "SELECT hall_no from " + tableName;
            stmt = conn.prepareStatement(sqlQuery);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                hallNo=rs.getString(1);
                hall.add(hallNo);
            }
        return hall;
    }   
    
      
   private void createConnection() throws SQLException {
        conn = DriverManager.getConnection(host, user, password); //possible to return SQLException
    }

    private void shutDown() throws SQLException{
        if (conn != null) {
                conn.close();
        }
    }
   
}
