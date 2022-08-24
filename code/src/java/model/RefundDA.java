package model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
        
public class RefundDA {
    
    private String host = "jdbc:derby://localhost:1527/assignmentdb";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "refund";
    private Connection conn;
    private PreparedStatement stmt;
    private PaymentDA paymentDA ;
    private StaffDA staffDA;
    
    public RefundDA()  throws SQLException{
        createConnection();
        paymentDA = new PaymentDA(); 
        staffDA = new StaffDA();
    }
 
      //get total number of record
    public int setNumberOfRecord() throws SQLException{
        String Str = "SELECT refund_no FROM " + tableName ;
        int numberOfRecord = 0;

            stmt = conn.prepareStatement(Str);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                 String nextRefund_noinstring = rs.getString("refund_no").replace("R", "0");
                 numberOfRecord =  Integer.parseInt(nextRefund_noinstring);
            }
        return numberOfRecord + 1;
    }
    
    public void addRecord(Refund refund) throws SQLException{
        setNumberOfRecord();
        String insertStr = "INSERT INTO " + tableName + " VALUES(?, ?, ?,?,?, ?)";
            stmt = conn.prepareStatement(insertStr);
            stmt.setString(1, refund.getRefund_no());
            stmt.setString(2, refund.getPayment().getPayment_no());
            stmt.setString(3, refund.getStaff().getStaffId());
            stmt.setDate(4, Date.valueOf(refund.getRefund_date()));
            stmt.setString(5, refund.getReason());

            if(refund.getStatus().toUpperCase().equals("PROCESSING"))
                stmt.setString(6, "P");
            else if(refund.getStatus().toUpperCase().equals("APPROVED"))
                stmt.setString(6, "A");
            else if(refund.getStatus().toUpperCase().equals("UNAPPROVED"))
                stmt.setString(6, "U");
            stmt.executeUpdate();
    }
    
    //get records of refund of the selected customer 
    public ArrayList<Refund> getRecord() throws SQLException{
        ArrayList<Refund> refundList = new ArrayList<Refund>();
        String queryStr = "SELECT refund_no, payment_no, staff_ID, refund_date, reason, status"
                + " FROM " + tableName; 
            stmt = conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Payment pay = paymentDA.getRecord(rs.getString("payment_no"));
                Staff staff = staffDA.getRecord(rs.getString("staff_ID"));
                refundList.add(new Refund(rs.getString("refund_no"), 
                        pay, staff, rs.getDate("refund_date").toLocalDate(), 
                        rs.getString("status").charAt(0),
                        rs.getString("reason")));
            }
        return refundList;
    }
    
    //allow the staff to changed the status of the refund
    public void updateRecord(Refund updatedRefund) throws SQLException{
        String updateStr = "UPDATE " + tableName + 
                " SET status = ? WHERE refund_no = ?";

            stmt = conn.prepareStatement(updateStr);
            if(updatedRefund.getStatus().charAt(0) == 'A'){
                stmt.setString(1, "A");
            }else{
                stmt.setString(1, "U");
            }    
            stmt.setString(2, updatedRefund.getRefund_no());
            stmt.executeUpdate();
    }
    
    public ArrayList<Payment> chkPaymentExistence(ArrayList<Payment> payments) throws SQLException{
        String sqlQuery = "SELECT * FROM " + tableName + " WHERE payment_no = ? AND status = 'A'";
        stmt = conn.prepareStatement(sqlQuery);
        ResultSet rs;
        
        for(int i=0; i<payments.size(); i++){
            stmt.setString(1, payments.get(i).getPayment_no());
            rs = stmt.executeQuery();
            
            if(rs.next()){
                payments.remove(i);
            }
        }
        
        return payments;

    }

     
     public void deleteRecord(String refund_no) throws SQLException{
        String deleteStr = "DELETE FROM " + tableName + " WHERE refund_no = ?";
            stmt = conn.prepareStatement(deleteStr);
            stmt.setString(1, refund_no);
            stmt.executeUpdate();
    }

    //delete refund
    public void deleteRefund(String paymentNo) throws SQLException{
        String sqlQuery = "DELETE FROM " + tableName + " WHERE payment_no = ?";    
            stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1,paymentNo);
            stmt.executeUpdate();   
    }
    
     public ArrayList<Refund> getRefunds() throws SQLException {

        ArrayList<Refund> refund = new ArrayList<Refund>();
        String sqlQueryStr = "SELECT * from " + tableName;

        stmt = conn.prepareStatement(sqlQueryStr);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            refund.add(new Refund(rs.getString(1), getPayment(rs.getString(2)),getStaff(rs.getString(3)), LocalDate.parse(rs.getString(4)),rs.getString(6).charAt(0),rs.getString(5)));
        }

        return refund;
    }

    
    public Payment getPayment(String payment_no) throws SQLException  {
        String queryStr = "SELECT * FROM  Payment  WHERE PAYMENT_NO = ?";
        Payment payment = null;
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, payment_no);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
               payment = new Payment(payment_no, Double.parseDouble(rs.getString(3)));
            }
        return payment;
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


    private void createConnection() throws SQLException{
        conn = DriverManager.getConnection(host, user, password);
    }
        
    private void shutDown() throws SQLException{
        if (conn != null) {
                conn.close();
        }
    }
}

