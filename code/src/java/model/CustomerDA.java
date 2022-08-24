package model;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDA {

    private String host = "jdbc:derby://localhost:1527/assignmentdb";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "Customer";
    private Connection conn;
    private PreparedStatement stmt;

    public CustomerDA() throws SQLException{
        createConnection();
    }
    
    public Customer currentCustomer(String email, String password) throws SQLException{
        Customer customer = null;
        
            String query = "SELECT * FROM " + tableName + " where email=? and cust_password=?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1,email);
            stmt.setString(2, password);
            
            ResultSet rs  = stmt.executeQuery();
            
            if(rs.next()){
                 customer = new Customer();
                 customer.setCust_ID(rs.getString("cust_ID"));
                 customer.setCust_name(rs.getString("cust_name"));
                 customer.setIc_no(rs.getString("ic_no"));
                 customer.setEmail(rs.getString("email"));
                 customer.setCust_password(rs.getString("cust_password"));
                 customer.setPhone_no(rs.getString("phone_no"));
                 customer.setSecurity_question(rs.getString("security_question"));
                 customer.setAnswer(rs.getString("answer"));
                 customer.setPoint(rs.getInt("point"));
            }
         
            return customer;
    }

    public void addRecord(Customer customer) throws SQLException{  //throws the error back to the caller

        String insertStr = "INSERT INTO " + tableName + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";       
            stmt = conn.prepareStatement(insertStr);
            stmt.setString(1, customer.getCust_ID());
            stmt.setString(2, customer.getCust_name());
            stmt.setString(3, customer.getIc_no());
            stmt.setString(4, customer.getEmail());
            stmt.setString(5, customer.getCust_password());
            stmt.setString(6, customer.getPhone_no());
            stmt.setString(7, customer.getSecurity_question());
            stmt.setString(8, customer.getAnswer());
            stmt.setInt(9, customer.getPoint());
            stmt.executeUpdate();

    }

    public String getLastCustomer() throws SQLException{
        String cust_ID=null;
        String sqlQuery = "SELECT MAX(cust_ID) FROM " + tableName;   
            stmt = conn.prepareStatement(sqlQuery);
            ResultSet rs  = stmt.executeQuery();

            stmt = conn.prepareStatement(sqlQuery);
            while (rs.next()) {
               cust_ID=rs.getString(1);
            }
  
        return cust_ID;
    }


    public Customer getRecord(String cust_ID) throws SQLException{
        String queryStr = "SELECT * FROM " + tableName + " WHERE cust_ID = ?";
        Customer customer = null;        
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, cust_ID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                customer = new Customer(cust_ID, rs.getString("cust_name"), 
                        rs.getString("ic_no"), rs.getString("email"),rs.getString("cust_password"),rs.getString("phone_no"),rs.getString("security_question"), rs.getString("answer"),rs.getInt("point")
                );
            }
        
        return customer;
    }
    
    public Customer getRecordEmail(String email) throws SQLException{
        String queryStr = "SELECT * FROM " + tableName + " WHERE email = ?";
        Customer customer = null;
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {

                customer = new Customer(rs.getString("cust_ID"),rs.getString("cust_name"), 
                        rs.getString("ic_no"), rs.getString("email"),rs.getString("cust_password"),rs.getString("phone_no"),rs.getString("security_question"),
                        rs.getString("answer"),rs.getInt("point")
                );
            }
        return customer;
    }

    public void updateRecord(Customer customer) throws SQLException{
        String updateStr = "UPDATE " + tableName + 
                    " SET cust_name = ?, ic_no = ?, email = ?, cust_password=?, phone_no = ?, security_question = ?, answer = ?, point = ? " + " WHERE cust_ID = ?";
            stmt = conn.prepareStatement(updateStr);
            
            stmt.setString(1, customer.getCust_name());
            stmt.setString(2, customer.getIc_no());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getCust_password());
            stmt.setString(5, customer.getPhone_no());
            stmt.setString(6, customer.getSecurity_question());
            stmt.setString(7, customer.getAnswer());
            stmt.setInt(8, customer.getPoint());
            stmt.setString(9, customer.getCust_ID());      
            stmt.executeUpdate();
   
    }

    public void updatePoint (int newPoint, String id) throws SQLException{
        String queryStr = "UPDATE " + tableName + " SET POINT = ? WHERE CUST_ID = ?";
        
            stmt = conn.prepareStatement(queryStr);
            stmt.setInt(1, newPoint);
            stmt.setString(2, id);
            stmt.executeUpdate();
    }

public void updatePointRecord(Customer cust) throws SQLException{
        String updateStr = " UPDATE " + tableName + 
                    " SET point = ?  WHERE Cust_ID = ? ";
            stmt = conn.prepareStatement(updateStr);
            stmt.setInt(1, cust.getPoint());
            stmt.setString(2, cust.getCust_ID());          
            stmt.executeUpdate();
        
    }

 public ArrayList<Customer> getCustomer() throws SQLException {

        ArrayList<Customer> customer = new ArrayList<Customer>();
        String sqlQueryStr = "SELECT * from " + tableName;

        stmt = conn.prepareStatement(sqlQueryStr);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            customer.add( new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),Integer.parseInt(rs.getString(9))));
        }

        return customer;
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


