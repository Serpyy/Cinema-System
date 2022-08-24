package model;

import java.sql.*;
import java.util.ArrayList;

public class ReviewDA {
    private String host = "jdbc:derby://localhost:1527/assignmentdb";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "REVIEW";
    private Connection conn;
    private PreparedStatement stmt;
    
    public ReviewDA() throws SQLException{
        createConnection();
    }
    
    // Get a Review
    public Review getReview(String review_no) throws SQLException{
        Customer customer = null;
        Review review = null;
        
        String query = "SELECT * FROM " + tableName + " WHERE review_no = ?";    
            stmt = conn.prepareStatement(query);
            stmt.setString(1, review_no);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                    review = new Review(rs.getString("review_no"), rs.getString("comment"), rs.getInt("rating"), customer);
            }
        return review;
    }
    
    // Get all Reviews
    public ArrayList<Review> getAllReview() throws SQLException{
        ArrayList<Review> reviewList = new ArrayList<Review>();
        Customer customer = null;
        Review review = null;
        
        CustomerDA custDA =  null;
        
        String query = "SELECT * FROM " + tableName + " ORDER BY review_no DESC";
        
        stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
            
        while (rs.next()) {
            custDA = new CustomerDA();
            customer =  custDA.getRecord(rs.getString("cust_id"));
            review = new Review(rs.getString("review_no"), rs.getString("comment"), rs.getInt("rating"), customer, rs.getString("response"));
            reviewList.add(review);
        }
            
        return reviewList;
    }
    
    // Add new Review
    public void addReview(Review review) throws SQLException {
        String query = "INSERT INTO " + tableName + " (review_no, cust_id, comment, rating) VALUES (?,?,?,?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, review.getReviewNo());
            stmt.setString(2, review.getCustomer().getCust_ID());
            if (review.getComment() == null || review.getComment() == "") {
                stmt.setString(3, "");    
            } else {
                stmt.setString(3, review.getComment());   
            }
            stmt.setInt(4, review.getRating());
            stmt.executeUpdate();
    }
    
    // Delete Review
    public void deleteReview(String reviewNo) throws SQLException{
        String query = "DELETE FROM " + tableName + " WHERE review_no = ?";
        
            stmt = conn.prepareStatement(query);
            stmt.setString(1,reviewNo);
            stmt.executeUpdate(); 
    }
    
    // Get Latest Review ID
    public String getLastReview() throws SQLException {
        String reviewNo = null;
        String query = "SELECT MAX(review_no) FROM " + tableName;
        
            stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                reviewNo = rs.getString(1);
            }
        return reviewNo;
    }
    
    // Staff add Response
    public void addResponse(String review_no, String response) throws SQLException {
        String query = "UPDATE Review SET response = ? WHERE review_no = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, response);
            stmt.setString(2, review_no);
            stmt.executeUpdate();
    }
    
    // Create DB Connection
    private void createConnection() throws SQLException{
        conn = DriverManager.getConnection(host, user, password);
    }
    
    // Shut down DB
    private void shutDown() throws SQLException{
        if (conn != null) {
            conn.close();
        }
    }
}