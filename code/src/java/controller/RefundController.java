package controller;

import model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet(name = "RefundController", urlPatterns = {"/RefundController"})
public class RefundController extends HttpServlet {
     
    private CustomerDA customerDA;
    private RefundDA refundDA;
    private TicketDA ticketDA;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
       
        try{
                refundDA = new RefundDA();
                customerDA = new CustomerDA();
                ticketDA = new TicketDA();
                
                HttpSession session = request.getSession();
                Customer cust = (Customer)session.getAttribute("ssLogUser");
                String user_ID = cust.getCust_ID();
                //String user_ID = request.getParameter("txtID");
                
                ArrayList<Refund> refundList = refundDA.getRecord();
                ArrayList<Refund> sortedRefundList =  new ArrayList<Refund>();
                                                                  
                  for (Refund refund : refundList) {
                     if(refund.getPayment().getCard().getCustomer().getCust_ID().equals(user_ID) && 
                             refund.getRefund_date().isAfter(LocalDate.now().minusMonths(1))){ 
                         sortedRefundList.add(refund);
                        }
                    }
                    
                    ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
                  
                  //get ticket list form payment
                    for ( Refund refund:  refundList) {
                        ArrayList<Ticket> paymentTicket = ticketDA.getRecord(refund.getPayment());
                        ticketList.addAll(paymentTicket);
                    }
                  
                    session.setAttribute("ssTicketList", ticketList);
                    session.setAttribute("ssUser_ID", user_ID);
                    session.setAttribute("ssRefundList", sortedRefundList);
                    response.sendRedirect("RefundCustomer.jsp");
                                             
        }catch(Exception ex){
                out.print("ERROR " + ex.toString() + "<br /><br />");

                StackTraceElement[] elements = ex.getStackTrace();
                for(StackTraceElement e: elements){
                    out.println("File Name: " + e.getFileName() + "<br />");
                    out.println("Class Name: " + e.getClassName() + "<br />");
                    out.println("Method Name: " + e.getMethodName() + "<br />");
                    out.println("Line Number: " + e.getLineNumber()+ "<br /><br />");
                }
            }
        
        
    }
    
}

