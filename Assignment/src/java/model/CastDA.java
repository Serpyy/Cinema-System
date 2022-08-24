package model;

import java.sql.*;
import java.util.ArrayList;

public class CastDA {

    private String host = "jdbc:derby://localhost:1527/assignmentdb";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "Casts";
    private Connection conn;
    private PreparedStatement stmt;
    private ActorDA actorDA;

    public CastDA() throws SQLException{
         createConnection();
         actorDA = new ActorDA();
    }
    
    //get all actorID 
    public ArrayList<String> getAllCast() throws SQLException{
        String actorID = null;  
        String actorName=null;
        ArrayList<String> casts = new ArrayList<String>();
        String sqlQuery = "SELECT Actor_ID from " + tableName;
            stmt = conn.prepareStatement(sqlQuery);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                actorID=rs.getString(1);
                actorName = actorDA.getActorName(actorID);
                casts.add(actorName);
            }
        return casts;
    }    
    
    //get all actor name related to movie
    public ArrayList<String> getCast(String movieID) throws SQLException{
        String actorID = null;  
        String actorName;
        ArrayList<String> casts = new ArrayList<String>();
        String sqlQuery = "SELECT Actor_ID from " + tableName + " WHERE Movie_ID = ?";
            stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1, movieID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                actorID=rs.getString(1);
                actorName = actorDA.getActorName(actorID);
                casts.add(actorName);
            }
        return casts;
    }    

  
    //get all actor id related to movie
    public ArrayList<String> getCastID(String movieID) throws SQLException{
        String actorID = null;  
        ArrayList<String> casts = new ArrayList<String>();
        String sqlQuery = "SELECT Actor_ID from " + tableName + " WHERE Movie_ID = ?";
            stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1, movieID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                actorID = rs.getString(1);
                casts.add(actorID);
            }
        return casts;
    }      
    
    //check if cast is registered for more than one movie
    public boolean checkCast(String actorID) throws SQLException{
        int count=0;
        String sqlQuery = "SELECT COUNT(*) AS count from " + tableName + " WHERE actor_ID = ?";
            stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1, actorID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        if(count==1){
            return true;
        }else{
        return false;
        }
    }
        
    //add cast
    public void addCast(String movieID,String actorID) throws SQLException{ 
        String sqlQuery = "INSERT INTO " + tableName + " VALUES(?,?)";
            stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1,movieID);
            stmt.setString(2,actorID);
            stmt.executeUpdate();     
    }
    
    //delete cast when movie cast edited
    public void deleteCast(String movieID, String actorID) throws SQLException{
        String sqlQuery = "DELETE FROM " + tableName + " WHERE movie_ID = ? AND actor_ID = ?";    
            stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1,movieID);
            stmt.setString(2,actorID);
            stmt.executeUpdate();
       
    }
    
    //delete cast related to movie
    public void deleteCastWithMovie(String movieID) throws SQLException{
        String sqlQuery = "DELETE FROM " + tableName + " WHERE movie_ID = ?";    
            stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1,movieID);
            stmt.executeUpdate();   
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
