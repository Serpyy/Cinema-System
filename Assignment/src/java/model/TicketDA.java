package model;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class TicketDA {

    //define the variables needed to connect the program to db
    private String host = "jdbc:derby://localhost:1527/assignmentdb";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "TICKET";
    private Connection conn;
    private PreparedStatement stmt;
    private Statement statement;

    public TicketDA() throws SQLException {
        createConnection();
    }

    //method that used to check for seat availability of the particular schedule
    public ArrayList<String> retrieveSelectedSeat(String scheduleId) throws SQLException {
        String queryStr = "SELECT SEAT_NO FROM " + tableName + " WHERE SCHEDULE_NO = ?";
        ArrayList<String> seatNo = new ArrayList<String>();
        stmt = conn.prepareStatement(queryStr);
        stmt.setString(1, scheduleId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {

            seatNo.add(rs.getString(1));
        }

        return seatNo; //return the seatNo array list back to caller (if null means there is no matched record)
    }

    //get records of refund of the selected customer 
    public ArrayList<Ticket> getRecord(Payment payment) throws SQLException {
        ScheduleDA scheduleDA = new ScheduleDA();
        ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
        String queryStr = "SELECT * FROM " + tableName + " WHERE payment_no = ? ";

        stmt = conn.prepareStatement(queryStr);
        stmt.setString(1, payment.getPayment_no());
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Schedule schedule = scheduleDA.getRecord(rs.getString("schedule_no"));
            ticketList.add(new Ticket(rs.getString("ticket_no"), payment, schedule,
                    rs.getDouble("price"), rs.getString("category"), rs.getString("seat_no")));
        }
        return ticketList;
    }

    public void addTicket(Ticket tic) throws SQLException {
        String queryStr = "INSERT INTO " + tableName + " VALUES(?, ?, ?, ?, ?, ?)";

        stmt = conn.prepareStatement(queryStr);
        stmt.setString(1, tic.getTicket_no());
        stmt.setString(2, tic.getPayment().getPayment_no());
        stmt.setString(3, tic.getSchedule().getSchedule_no());
        stmt.setBigDecimal(4, BigDecimal.valueOf(tic.getPrice()));
        stmt.setString(5, tic.getCategory());
        stmt.setString(6, tic.getSeat_no());

        stmt.executeUpdate();

    }

    public ArrayList<String> getScheduleNoByPaymentNo(ArrayList<Payment> payment) throws SQLException {
        ArrayList<String> scheduleNo = new ArrayList<String>();
        String queryStr = "SELECT DISTINCT PAYMENT_NO FROM " + tableName + " WHERE payment_no IN('" + payment.get(0).getPayment_no() + "'";
        for (int i = 1; i < payment.size(); i++) { //continue append the query
            queryStr += ", '" + payment.get(i).getPayment_no() + "'";
        }
        queryStr += ") ORDER BY PAYMENT_NO";
        statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(queryStr);

        while (rs.next()) {
            queryStr = "SELECT SCHEDULE_NO FROM " + tableName + " WHERE payment_no = ?"; //retrieve the movie title based on its id
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, rs.getString(1));

            ResultSet rs2 = stmt.executeQuery();
            if (rs2.next()) {
                scheduleNo.add(rs2.getString(1));
            }
        }
        return scheduleNo;

    }

    //get paymentNo using schedule
    public ArrayList<String> getPaymentNo(String scheduleNo) throws SQLException {
        ArrayList<String> paymentNo = new ArrayList<String>();
        String sqlQuery = "SELECT payment_no from " + tableName + " WHERE schedule_no=?";
        stmt = conn.prepareStatement(sqlQuery);
        stmt.setString(1, scheduleNo);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            paymentNo.add(rs.getString(1));
        }
        return paymentNo;
    }

    public Ticket getTicket(String payment_no) throws SQLException {
        String queryStr = "SELECT * FROM  TICKET  WHERE PAYMENT_NO = ?";
        Ticket ticket = null;
        ScheduleDA scheduleDA = new ScheduleDA();
        stmt = conn.prepareStatement(queryStr);
        stmt.setString(1, payment_no);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            ticket = new Ticket(payment_no, rs.getString(6), scheduleDA.getRecord(rs.getString(3)) );
        }
        return ticket;
    }

    //delete ticket
    public void deleteTicket(String scheduleNo) throws SQLException {
        String sqlQuery = "DELETE FROM " + tableName + " WHERE schedule_no = ?";
        stmt = conn.prepareStatement(sqlQuery);
        stmt.setString(1, scheduleNo);
        stmt.executeUpdate();
    }




    //get records of refund of the selected customer 
    public ArrayList<Ticket> getPaymentRecord(Payment payment) throws SQLException {
        ScheduleDA scheduleDA = new ScheduleDA();
        ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
        String queryStr = "SELECT * FROM " + tableName + " WHERE payment_no = ? ";

        stmt = conn.prepareStatement(queryStr);
        stmt.setString(1, payment.getPayment_no());
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Schedule schedule = scheduleDA.getRecord(rs.getString("schedule_no"));
            ticketList.add(new Ticket(rs.getString("ticket_no"), payment, schedule,
                    rs.getDouble("price"), rs.getString("category"), rs.getString("seat_no")));
        }
        return ticketList;
    }

    private void createConnection() throws SQLException {
        conn = DriverManager.getConnection(host, user, password); //possible to return SQLException
    }

    // Shut down DB
    private void shutDown() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
}
