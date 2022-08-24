package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.*;

@WebServlet(name = "StorePaymentTicketDetailsServlet", urlPatterns = {"/StorePaymentTicketDetailsServlet"})
public class StorePaymentTicketDetailsServlet extends HttpServlet {

    //Trigger after user has made payment towards this transaction
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        //retrieve the payment, card and tickets obj that we create in previous Servlet
        Payment payment = (Payment) session.getAttribute("ssTicPayment"); 
        Card paymentCard = (Card) session.getAttribute("ssTicTempCard");
        ArrayList<Ticket> ticketsPurchased = (ArrayList<Ticket>) session.getAttribute("ssTicPurchasedTicketList"); 
        try{
            PaymentDA paymentDA = new PaymentDA();
            //To initialise the next payment code defined inside the payment class
            String lastPaymentNo = paymentDA.getLastPaymentNo();
            String paymentCodeInStr =lastPaymentNo.substring(1); //remove the 'P' at front
            int nextPaymentCode = Integer.parseInt(paymentCodeInStr) + 1;        
            Payment.initialiseNext_payment_code(nextPaymentCode);
        }catch(Exception ex){
            PrintWriter out = response.getWriter();
            out.println("ERROR: " + ex.toString() + "</br> </br>"); //normal error message (not so detail)

            //To print very detail error message (able to trace the entire path that causing the error at the particular file, which line and etc)
            StackTraceElement[] elements = ex.getStackTrace();

            for(StackTraceElement e: elements){
                out.println("File Name: " + e.getFileName() + "</br>");
                out.println("Class Name: " + e.getClassName()+ "</br>");
                out.println("Method Name: " + e.getMethodName()+ "</br>");
                out.println("Line Number: " + e.getLineNumber()+ "</br> </br>");
            }
        }
        
        //Complete up the Payment object and store it to db
        payment.setPayment_no();
        payment.setCard(paymentCard);
        payment.setPayment_time(LocalTime.now());
        payment.setPayment_date(LocalDate.now());
        try{
            PaymentDA paymentDA = new PaymentDA();
            paymentDA.addPayment(payment);
        }catch(SQLException ex){
            PrintWriter out = response.getWriter();
            out.println("ERROR: " + ex.toString() + "</br> </br>"); //normal error message (not so detail)

            //To print very detail error message (able to trace the entire path that causing the error at the particular file, which line and etc)
            StackTraceElement[] elements = ex.getStackTrace();

            for(StackTraceElement e: elements){
                out.println("File Name: " + e.getFileName() + "</br>");
                out.println("Class Name: " + e.getClassName()+ "</br>");
                out.println("Method Name: " + e.getMethodName()+ "</br>");
                out.println("Line Number: " + e.getLineNumber()+ "</br> </br>");
            }
        }
        
        //Complete up all the ticket objects and store them into db
        for(int i=0; i<ticketsPurchased.size(); i++){
            try{
                TicketDA ticketDA = new TicketDA();
                //Assign payment object to each of the ticket object
                ticketsPurchased.get(i).setPayment(payment);
                ticketDA.addTicket(ticketsPurchased.get(i));
            }catch(Exception ex){
                PrintWriter out = response.getWriter();
                out.println("ERROR: " + ex.toString() + "</br> </br>"); //normal error message (not so detail)

                //To print very detail error message (able to trace the entire path that causing the error at the particular file, which line and etc)
                StackTraceElement[] elements = ex.getStackTrace();

                for(StackTraceElement e: elements){
                    out.println("File Name: " + e.getFileName() + "</br>");
                    out.println("Class Name: " + e.getClassName()+ "</br>");
                    out.println("Method Name: " + e.getMethodName()+ "</br>");
                    out.println("Line Number: " + e.getLineNumber()+ "</br> </br>");
                }
            }
        }
        
        //Calculate the point gained after performing this transaction and update the customer's point inside the Customer record
        //Assume that purchasing 1 child ticket will obtain 7 points, whereas purchase 1 adult ticket will gain 10 points
        Customer cust = (Customer)session.getAttribute("ssLogUser");
        int currentPoint = cust.getPoint();
        int childPoint = 7;
        int adultPoint = 10;
        int childQty = payment.getChild_qty();
        int adultQty = payment.getAdult_qty();
        int ttlPointGained = (childPoint * childQty) + (adultPoint * adultQty);
        int latestPoint = currentPoint + ttlPointGained;
        request.setAttribute("ttlPointGained", ttlPointGained); //Pass the no. of point gained in this transaction to the next page using request scope
        try{
            CustomerDA customerDA = new CustomerDA();
            customerDA.updatePoint(latestPoint, cust.getCust_ID());
            cust.setPoint(latestPoint);
        }catch(Exception ex){
            PrintWriter out = response.getWriter();
            out.println("ERROR: " + ex.toString() + "</br> </br>"); //normal error message (not so detail)

            //To print very detail error message (able to trace the entire path that causing the error at the particular file, which line and etc)
            StackTraceElement[] elements = ex.getStackTrace();

            for(StackTraceElement e: elements){
                out.println("File Name: " + e.getFileName() + "</br>");
                out.println("Class Name: " + e.getClassName()+ "</br>");
                out.println("Method Name: " + e.getMethodName()+ "</br>");
                out.println("Line Number: " + e.getLineNumber()+ "</br> </br>");
            }
        }
        
        //redirect to the transaction successful page
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/TransactionSuccessful.jsp");
        dispatcher.forward(request, response);
    }

}