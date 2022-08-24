package model;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;

public class MovieDA {

    private String host = "jdbc:derby://localhost:1527/assignmentdb";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "Movie";
    private Connection conn;
    private PreparedStatement stmt;

    public MovieDA() throws SQLException {
         createConnection();
    }
    
    //get all movie
    public ArrayList<Movie> getMovie() throws SQLException{
        Movie movie = null;
        ArrayList<Movie> movies = new ArrayList<Movie>();
        String sqlQuery = "SELECT * from " + tableName;
        ImageIcon poster=null;

            stmt = conn.prepareStatement(sqlQuery);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Blob blob = rs.getBlob(9);
                
                if(blob != null){
                    poster = new ImageIcon(blob.getBytes(1, (int)blob.length()));       
                }
                movie = new Movie(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getDate(4).toLocalDate(),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),poster);
                movies.add(movie);
            }

        return movies;
    }
    
    //get selected movie
    public Movie getCurrentMovie(String movieID) throws SQLException{
        Movie movie = null;
        String sqlQuery = "SELECT * FROM " + tableName + " WHERE movie_ID = ?";  
        ImageIcon poster=null;

            stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1,movieID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                 Blob blob = rs.getBlob(9);
                
                if(blob != null){
                    poster = new ImageIcon(blob.getBytes(1, (int)blob.length()));       
                }
                movie = new Movie(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getDate(4).toLocalDate(),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),poster);
            }
        
        return movie;
    }

    //get last movie id
    public String getLastMovie() throws SQLException{
        String movieID=null;
        String sqlQuery = "SELECT MAX(movie_ID) FROM " + tableName;    
            stmt = conn.prepareStatement(sqlQuery);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
               movieID=rs.getString(1);
            }

        
        return movieID;
    }
    
    //add movie
    public void addMovie(Movie movie) throws SQLException{
        String sqlStr="INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?,?,?,?)";
        Date releaseDate = Date.valueOf(movie.getReleaseDate());
            stmt=conn.prepareStatement(sqlStr);   
            stmt.setString(1,movie.getMovieID());
            stmt.setString(2,movie.getMovieTitle());
            stmt.setInt(3,movie.getDuration());
            stmt.setDate(4,releaseDate);
            stmt.setString(5,movie.getSynopsis());
            stmt.setString(6,movie.getDirector());
            stmt.setString(7,movie.getDistributor());           
            stmt.setString(8,movie.getAgeRating());
            stmt.setBinaryStream(9, movie.getInputStream(),movie.getSize());
            stmt.executeUpdate();
    }   
    
    //update movie details
    public void updateMovie(Movie movie) throws SQLException{
        String sqlStr="UPDATE " + tableName + " SET movie_title=?, duration=?, ageRating=?, release_Date=?, director=?, distributor=?, synopsis=?, poster=? WHERE movie_id=?";
        Date releaseDate = Date.valueOf(movie.getReleaseDate());
            stmt=conn.prepareStatement(sqlStr);   
            stmt.setString(1,movie.getMovieTitle());
            stmt.setInt(2,movie.getDuration());
            stmt.setString(3,movie.getAgeRating());
            stmt.setDate(4,releaseDate);
            stmt.setString(5,movie.getDirector());
            stmt.setString(6,movie.getDistributor());
            stmt.setString(7,movie.getSynopsis());           
            stmt.setBinaryStream(8, movie.getInputStream(),movie.getSize());
            stmt.setString(9, movie.getMovieID());
            stmt.executeUpdate();
    }   
 
    //update movie details without poster
    public void updateMovieNP(Movie movie) throws SQLException{
        String sqlStr="UPDATE " + tableName + " SET movie_title=?, duration=?, ageRating=?, release_Date=?, director=?, distributor=? WHERE movie_id=?";
        Date releaseDate = Date.valueOf(movie.getReleaseDate());     
            stmt=conn.prepareStatement(sqlStr);   
            stmt.setString(1,movie.getMovieTitle());
            stmt.setInt(2,movie.getDuration());
            stmt.setString(3,movie.getAgeRating());
            stmt.setDate(4,releaseDate);
            stmt.setString(5,movie.getDirector());
            stmt.setString(6,movie.getDistributor());           
            stmt.setString(7, movie.getMovieID());
            stmt.executeUpdate();
    }   
        
    //delete movie
     public void deleteMovie(String movieID) throws SQLException{
        String sqlStr="DELETE FROM " + tableName + " WHERE movie_id=?";
            stmt=conn.prepareStatement(sqlStr);           
            stmt.setString(1, movieID);
            stmt.executeUpdate();
    }   

    public String getMovieTitle(String movieID) throws SQLException{
            String queryStr = "SELECT MOVIE_TITLE FROM " + tableName + " WHERE MOVIE_ID = ?"; 

            String movieTitle = null;
        
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, movieID); 
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) { 
                movieTitle = rs.getString(1);
            }

            return movieTitle;
            
        }
    
    public Movie getMovie(String id) throws SQLException {
        String queryStr = "SELECT * FROM  MOVIE  WHERE MOVIE_ID = ?";
        Movie m = null;
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
               m = new Movie(id, rs.getString(2));
            }
        return m;
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
