package model;

import java.sql.*;
import java.util.ArrayList;

public class StaffDA {

    private String host = "jdbc:derby://localhost:1527/assignmentdb";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "Staff";
    private Connection conn;
    private PreparedStatement stmt;

    public StaffDA() throws SQLException{
         createConnection();
    }
    
    //get all staff
    public ArrayList<Staff> getStaff() throws SQLException{
            String sqlQuery = "SELECT staff_ID, staff_name, role, staff_email, staff_phone_no FROM " + tableName;
            ArrayList<Staff> staffList = new ArrayList<Staff>();
            Staff staff = null;
            stmt = conn.prepareStatement(sqlQuery);  
            ResultSet rs =stmt.executeQuery(); 
            while(rs.next()){
                staff = new Staff(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
                staffList.add(staff);
            }   
        return staffList;
    }
    
//GET one staff 
    public Staff getRecord(String staff_ID) throws SQLException {
        String queryStr = "SELECT * FROM " + tableName + " WHERE staff_ID = ?";
        Staff staff = null;
       
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, staff_ID);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                staff = new Staff(staff_ID, rs.getString("staff_name"), rs.getString("staff_email"), 
                        rs.getString("password"), rs.getString("role"), rs.getString("staff_phone_no"), 
                        rs.getString("security_question"), rs.getString("answer"));
            }
        
        return staff;
    }

    public Staff getStaff(String staff_ID) throws SQLException  {
        String queryStr = "SELECT * FROM  STAFF  WHERE STAFF_ID = ?";
        Staff staff = null;
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, staff_ID);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
               staff = new Staff(staff_ID, rs.getString(2));
            }
        return staff;
    }

    //get last staff ID
    public String getLastStaff() throws SQLException{
        String staffID=null;
        String sqlQuery = "SELECT MAX(staff_ID) FROM " + tableName;    
            stmt = conn.prepareStatement(sqlQuery);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
               staffID=rs.getString(1);
            }
        
        return staffID;
    }
    
    //check staff has been registered
    public Staff checkStaff(String email, String password) throws SQLException{

        Staff staff=null;
        String sqlQuery = "SELECT * FROM " + tableName + " WHERE staff_email = ? AND password = ?";    
            stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                staff = new Staff(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
            }
        return staff;
    }
    
    public Staff checkEmail(String email) throws SQLException{
        Staff staff=null;
        String sqlQuery = "SELECT * FROM " + tableName + " WHERE staff_email = ?";  
            stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                staff = new Staff(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
            }
        return staff;   
    }
    
    //add staff
    public void addStaff(Staff staff) throws SQLException{ 
        String sqlQuery = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1,staff.getStaffId());
            stmt.setString(2,staff.getStaffName());
            stmt.setString(3,staff.getStaffEmail());
            stmt.setString(4,"gscstaff123");
            stmt.setString(5,staff.getRole());
            stmt.setString(6,staff.getStaffPhoneNo());
            stmt.setString(7,null);
            stmt.setString(8,null);
            stmt.executeUpdate();    
    }
    
    public void updateStaff(Staff staff) throws SQLException{
        String query = "UPDATE " + tableName + " SET staff_name = ?, staff_email = ?, password = ?, staff_phone_no = ?, security_question = ?, answer = ? WHERE staff_id = ?";
            stmt = conn.prepareStatement(query);    
            stmt.setString(1, staff.getStaffName());
            stmt.setString(2, staff.getStaffEmail());
            stmt.setString(3, staff.getStaffPassword());
            stmt.setString(4, staff.getStaffPhoneNo());
            stmt.setString(5, staff.getSecurityQuestion());
            stmt.setString(6, staff.getAnswer());
            stmt.setString(7, staff.getStaffId());
            stmt.executeUpdate(); 
    }

    //edit staff
    public void editStaff(Staff staff) throws SQLException{ 
        String sqlQuery = "UPDATE " + tableName + " SET staff_name = ?, staff_email = ?, role= ?, staff_phone_no = ? WHERE staff_ID = ?";
            stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1,staff.getStaffName());
            stmt.setString(2,staff.getStaffEmail());
            stmt.setString(3,staff.getRole());
            stmt.setString(4,staff.getStaffPhoneNo());
            stmt.setString(5,staff.getStaffId());
            stmt.executeUpdate();     
    }
    
        //edit staff
    public void editStaffPassword(Staff staff) throws SQLException{ 
        String sqlQuery = "UPDATE " + tableName + " SET password = ? WHERE staff_ID = ?";
            stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1,staff.getStaffPassword());
            stmt.setString(2,staff.getStaffId());
            stmt.executeUpdate();     
    }
    
    //edit staff only question and answer
    public void editStaffSec(Staff staff) throws SQLException{ 
        String sqlQuery = "UPDATE " + tableName + " SET security_question = ?, answer = ? WHERE staff_ID = ?";
            stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1,staff.getSecurityQuestion());
            stmt.setString(2,staff.getAnswer());
            stmt.setString(3,staff.getStaffId());
            stmt.executeUpdate();     
    }
    
    //delete staff    
    public void deleteStaff(String staffID) throws SQLException{ 
        String sqlQuery = "DELETE FROM " + tableName + " WHERE staff_ID=?";
            stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1,staffID);    
            stmt.executeUpdate();     
    }
    
    //get Manager
    public ArrayList<Staff> getManager() throws SQLException{
        ArrayList<Staff> staffList = new ArrayList<Staff>();
        String queryStr = "SELECT * " + "FROM " + tableName
                + " WHERE role = ? "; 
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, "Manager");
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                staffList.add(new Staff(rs.getString("staff_ID"), rs.getString("staff_name"), rs.getString("staff_email"), 
                        rs.getString("password"), rs.getString("role"), rs.getString("staff_phone_no"), 
                        rs.getString("security_question"), rs.getString("answer")));
            }
      
        return staffList;
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
