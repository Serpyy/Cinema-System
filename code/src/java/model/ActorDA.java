package model;

import java.sql.*;
import java.util.ArrayList;

public class ActorDA {

    private String host = "jdbc:derby://localhost:1527/assignmentdb";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "Actor";
    private Connection conn;
    private PreparedStatement stmt;

    public ActorDA() throws SQLException{
         createConnection();
    }
    
    //get all actor
    public ArrayList<String> getActor() throws SQLException{ 
        String actorName = null;
        ArrayList<String> actors = new ArrayList<String>();
        String sqlQuery = "SELECT Actor_name from " + tableName;
            stmt = conn.prepareStatement(sqlQuery);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                actorName=rs.getString(1);
                actors.add(actorName);
            }
        return actors;
    }   
	
   //get actor name
    public String getActorName(String actorID) throws SQLException{
        String actorName= null;
        String sqlQuery = "SELECT Actor_name from " + tableName + " WHERE Actor_ID = ?";
            stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1,actorID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                 actorName = rs.getString(1);    
            }
        return actorName;
    }

    
    //add actor
    public String addActor(String actorName) throws SQLException{ 
        ArrayList<String> actors = new ArrayList<String>();
        String sqlQuery = "INSERT INTO " + tableName + " VALUES(?,?)";
        String lastActor = getLastActor();
        String[] parts = lastActor.split("A");
        int actorInt = Integer.parseInt(parts[1]);
        actorInt++;
        String actorNo = String.format("%03d",actorInt);
        String actorID = "A"+ actorNo;   
            stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1,actorID);
            stmt.setString(2, actorName);
            stmt.executeUpdate();     
        return actorID;
    }
    
    //get actor id by name
    public String getActorID(String actorName) throws SQLException{ 
        String sqlQuery = "SELECT actor_ID FROM " + tableName + " WHERE actor_name = ?";
        String actorID = null;
            stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1,actorName);
            ResultSet rs = stmt.executeQuery();     
            while(rs.next()){
                actorID=rs.getString(1);
            }
        return actorID;
    }
    
    //get last actor id
    public String getLastActor() throws SQLException{
        String actorID=null;
        String sqlQuery = "SELECT MAX(actor_ID) FROM " + tableName;    
            stmt = conn.prepareStatement(sqlQuery);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
               actorID=rs.getString(1);
            }
        
        return actorID;
    }
    
    //delete actor
    public void deleteActor(String actorID) throws SQLException{
        String sqlQuery = "DELETE FROM " + tableName + " WHERE actor_ID = ?";    
            stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1,actorID);
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


