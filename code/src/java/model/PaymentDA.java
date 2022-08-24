package model;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class PaymentDA{
    
    //define the variables needed to connect the program to db
    private String host = "jdbc:derby://localhost:1527/assignmentdb";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "PAYMENT";
    private Connection conn;
    private PreparedStatement stmt;
    private Statement statement;
    private CardDA cardDA;
    
    public PaymentDA() throws SQLException{
        createConnection(); 
        cardDA = new CardDA(); 
   }
    
    public String getLastPaymentNo() throws SQLException{
        String queryStr = "SELECT payment_no FROM " + tableName; 
        String lastPaymentNo = "";
        
            stmt = conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) { 
                lastPaymentNo = rs.getString(1);
            }
        return lastPaymentNo;
    }
    
    public ArrayList<Payment> getPaymentByCardNo (ArrayList<Card> cards) throws SQLException{
        ArrayList<Payment> payment = new ArrayList<Payment>();
        Payment tempPayment = null;
        String queryStr = "SELECT * FROM " +tableName + " WHERE CARD_NO IN('" + cards.get(0).getCard_no() + "'";
        for(int i =1; i<cards.size(); i++){ //continue append the query
            queryStr +=", '" + cards.get(i).getCard_no() + "'";
        }
        queryStr += ") ORDER BY PAYMENT_NO"; //close the query's bracket
        
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(queryStr);

            while(rs.next()){
                tempPayment = new Payment(rs.getString(1), new Card(rs.getString(2)), rs.getBigDecimal(3).doubleValue(), rs.getTime(4).toLocalTime(), rs.getDate(5).toLocalDate(), rs.getInt(6), rs.getInt(7));
                payment.add(tempPayment);
            }
        return payment;   
    }

    public Payment getRecord(String payment_no) throws  SQLException{
        String queryStr = "SELECT payment_no, card_no, amount, payment_time, payment_date,"
                + " child_qty, adult_qty"
                + "  FROM " + tableName + " WHERE payment_no = ?";
        Payment payment = null;
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, payment_no);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
               Card card = cardDA.getRecord(rs.getString("card_no"));
               payment = new Payment(rs.getString("payment_no"), card, rs.getDouble("amount"),rs.getTime("payment_Time").toLocalTime(),
               rs.getDate("payment_Date").toLocalDate(), rs.getInt("child_qty"), rs.getInt("adult_qty"));
            }
        return payment;
    }
   
    public ArrayList<Payment> getRecord(Customer cust) throws  SQLException{
        String queryStr = 
                "SELECT P.*, C.* "
                + "FROM Payment P, CARD C "
                + "WHERE P.card_no = C.card_no "
                + "AND C.Cust_ID = ? ";
            ArrayList<Payment> paymentList = new ArrayList<Payment>();
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, cust.getCust_ID());
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Card card = cardDA.getRecord(rs.getString("card_no"));
                paymentList.add( new Payment(rs.getString("payment_no"), card, rs.getDouble("amount"),rs.getTime("payment_Time").toLocalTime(),
                rs.getDate("payment_Date").toLocalDate(), rs.getInt("child_qty"), rs.getInt("adult_qty")));
            }
        return paymentList;
    }

    public void addPayment(Payment payment) throws SQLException{
        String queryStr = "INSERT INTO " + tableName + " VALUES(?, ?, ?, ?, ?, ?, ?)";

            stmt = conn.prepareStatement(queryStr); 
            stmt.setString(1, payment.getPayment_no());
            stmt.setString(2, payment.getCard().getCard_no());
            stmt.setBigDecimal(3, BigDecimal.valueOf(payment.getAmount()));
            stmt.setTime(4, Time.valueOf(payment.getPayment_time()));
            stmt.setDate(5, Date.valueOf(payment.getPayment_date()));
            stmt.setInt(6, payment.getChild_qty());
            stmt.setInt(7, payment.getAdult_qty());
            stmt.executeUpdate();

    }

    //delete payment
    public void deletePayment(String paymentNo) throws SQLException{
        String sqlQuery = "DELETE FROM " + tableName + " WHERE payment_no = ?";    
            stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1,paymentNo);
            stmt.executeUpdate();   
    }
    
    public ArrayList<Payment> getPayments() throws SQLException {

        ArrayList<Payment> payment = new ArrayList<Payment>();
        String sqlQueryStr = "SELECT * from " + tableName;

        stmt = conn.prepareStatement(sqlQueryStr);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
        Payment p= new Payment(LocalDate.parse(rs.getString(5)), rs.getString(1));
        p.setCard(cardDA.getRecord(rs.getString(2)) );
        p.setPayment_time(LocalTime.parse(rs.getString(4)));
        p.setChild_qty(Integer.parseInt(rs.getString(6)));
        p.setAdult_qty(Integer.parseInt(rs.getString(7)));
        p.setAmount(Double.parseDouble(rs.getString(3)));
            payment.add(p);
        }

        return payment;
    }
    


        



       
       //search record
    public ArrayList<Payment> searchPayement(String fromDate, String toDate) throws SQLException {

        ArrayList<Payment> payment = new ArrayList<Payment>();
        String sqlQueryStr = "SELECT * FROM PAYMENT where payment_date >= ? and payment_date <= ? ";

        stmt = conn.prepareStatement(sqlQueryStr);
         stmt.setString(1,fromDate);
         stmt.setString(2,toDate);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Payment p= new Payment(LocalDate.parse(rs.getString(5)), rs.getString(1));
        p.setCard(cardDA.getRecord(rs.getString(2)) );
        p.setPayment_time(LocalTime.parse(rs.getString(4)));
        p.setChild_qty(Integer.parseInt(rs.getString(6)));
        p.setAdult_qty(Integer.parseInt(rs.getString(7)));
        p.setAmount(Double.parseDouble(rs.getString(3)));
            payment.add(p);
        }

        return payment;
    }

    
    private void createConnection() throws SQLException{
        conn = DriverManager.getConnection(host, user, password); //possible to return SQLException
  }
        
    private void shutDown() throws SQLException{
        if (conn != null) {
                conn.close();
        }
    }

    
}
