package model;

import java.sql.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

public class CardDA{
    
    //define the variables needed to connect the program to db
    private String host = "jdbc:derby://localhost:1527/assignmentdb";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "CARD";
    private Connection conn;
    private PreparedStatement stmt;
    private CustomerDA customerDA ;
    
    public CardDA() throws SQLException{
        createConnection();
        customerDA = new CustomerDA();    
    }
    

    public ArrayList<Card> retrieveCards(Customer cust) throws SQLException {
        String queryStr = "SELECT * FROM " + tableName + " WHERE CUST_ID = ?"; 
        ArrayList<Card> cards = new ArrayList<Card>(); 

            stmt = conn.prepareStatement(queryStr); 
            stmt.setString(1, cust.getCust_ID());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LocalDate tempDate = rs.getDate(5).toLocalDate(); 
                YearMonth tempYearMonth = YearMonth.of(tempDate.getYear(), tempDate.getMonthValue());
                cards.add(new Card(rs.getString(1), cust, rs.getString(3), rs.getString(4), tempYearMonth));
            }   
        return cards; //if length 0 means no record!
    }
    
    public boolean chkCardExistance(String cardNo) throws SQLException{
        String queryStr = "SELECT * FROM " + tableName + " WHERE card_no = ?"; 
        boolean exist = false;
            stmt = conn.prepareStatement(queryStr); 
            stmt.setString(1, cardNo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) { //means there is matched record found (error)
                exist = true;
            }
        return exist; //if return false means no matched record!
    }
    
    public Card getRecord(String card_no)  throws SQLException {
        String queryStr = "SELECT *  FROM " + tableName + " WHERE Card_no = ?";
        Card card = null;
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, card_no);
            ResultSet rs = stmt.executeQuery(); 
            while(rs.next()) {
                Customer cust = customerDA.getRecord(rs.getString("Cust_ID"));
                card =new Card(rs.getString("Card_no"), cust, rs.getString("CVV"),rs.getString("Cardholder_Name")
                        ,YearMonth.from((rs.getDate("Expiry_Date").toLocalDate())));  
            }       
        return card;
    }


    public void addCard(Card card) throws SQLException{
        String queryStr = "INSERT INTO " + tableName + " VALUES(?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(queryStr); 
            stmt.setString(1, card.getCard_no());
            stmt.setString(2, card.getCustomer().getCust_ID());
            stmt.setString(3, card.getCvv());
            stmt.setString(4, card.getCardholder_name());
            LocalDate temp = LocalDate.of(card.getExpiry_date().getYear(), card.getExpiry_date().getMonth(), 1);
            stmt.setDate(5, Date.valueOf(temp));

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
