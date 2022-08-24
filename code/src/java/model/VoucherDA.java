package model;

import java.sql.*;
import java.util.ArrayList;

public class VoucherDA {
    
    private String host = "jdbc:derby://localhost:1527/assignmentdb";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "voucher";
    private Connection conn;
    private PreparedStatement stmt;
    
    public VoucherDA() throws SQLException {
        createConnection();
    }
    
     //get records of refund of the selected customer 
    public ArrayList<Voucher> getRecord(Customer cust) throws SQLException{
        ArrayList<Voucher> voucherList = new ArrayList<Voucher>();
        String queryStr = "SELECT voucher_no, dis_amount FROM " +tableName + " WHERE Cust_ID = ?";
        
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, cust.getCust_ID());
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                 voucherList.add(new Voucher(rs.getString("voucher_no"), cust, rs.getInt("dis_amount")));
             }           
                
        return voucherList;
    }
    
    //get total number of record
    public int getNextNumber() throws SQLException{
        String Str = "SELECT voucher_no FROM voucher" ;   
        int nextVoucher_no = 0;
            stmt = conn.prepareStatement(Str);
            ResultSet rs = stmt.executeQuery();           
            while(rs.next()){
                  String nextVoucher_noinstring = rs.getString("voucher_no").replace("V", "0");
                  nextVoucher_no =  Integer.parseInt(nextVoucher_noinstring);
             }
             
       
        return nextVoucher_no+1;
    }
    
     public void addRecord(Voucher voucher) throws SQLException{
        String insertStr = "INSERT INTO " + tableName + " VALUES(?, ?, ?)";
        
            stmt = conn.prepareStatement(insertStr);
            stmt.setString(1, voucher.getVoucher_no());
            stmt.setString(2, voucher.getCustomer().getCust_ID());
            stmt.setInt(3, voucher.getDis_amount());
            
            stmt.executeUpdate();
       
    }
    
  
    public int voucherValidation(String voucherCode, String custID) throws SQLException{
        String queryStr = "SELECT dis_amount FROM " + tableName + " WHERE voucher_no = ? AND cust_ID = ? "; 
        int disAmount = 0; 
            stmt = conn.prepareStatement(queryStr); 
            stmt.setString(1, voucherCode);
            stmt.setString(2, custID); 
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) { //if the first row of result set is not empty (means the voucher code entered is valid)
                disAmount = rs.getInt(1);
            }
        return disAmount; //return the disAmount back to caller (if 0 means there is no matched record)
    }
    
    public void deleteVoucher(String voucherCode) throws SQLException{
         String queryStr = "DELETE FROM " + tableName + " WHERE voucher_no = ?";
            stmt = conn.prepareStatement(queryStr); 
            stmt.setString(1, voucherCode);
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
