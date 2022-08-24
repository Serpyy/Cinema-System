package controller;

import model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet(name = "CancelRefund", urlPatterns = {"/CancelRefund"})
public class CancelRefund extends HttpServlet {

    private CustomerDA customerDA;
    private RefundDA refundDA;
    private TicketDA ticketDA;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
         HttpSession session = request.getSession();
         
        try{
                refundDA = new RefundDA();
                customerDA = new CustomerDA();
                ticketDA = new TicketDA();
            
                String refund_no = request.getParameter("txtRefundNumber");
                                
                ArrayList<Refund> refundList = (ArrayList<Refund>) refundDA.getRecord();
                Refund cancelThisRefund = null;

                for(Refund refund : refundList){
                    if(refund.getRefund_no().equals(refund_no)){
                        cancelThisRefund = refund;
                    }
                }
                
                System.out.println(refund_no);
                
                ArrayList<Ticket> refundTicketList = ticketDA.getRecord(cancelThisRefund.getPayment());
                
                session.setAttribute("ssRefundTicketList", refundTicketList);
                session.setAttribute("ssCancelRefund", cancelThisRefund);
                response.sendRedirect("CancelRefund.jsp");
                
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
        
        out.close();
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        
        
        try{
                refundDA = new RefundDA();
                customerDA = new CustomerDA();
                ticketDA = new TicketDA();
            
            
                String refund_no = request.getParameter("txtRefundNumber");
        
                if("Confirm".equals(request.getParameter("action"))){
                    refundDA.deleteRecord(refund_no);
                }
                
                //to get value to display customer refund if needed 
                String user_ID = request.getParameter("txtID");
                
                ArrayList<Refund> refundList = refundDA.getRecord();
                ArrayList<Refund> sortedRefundList =  new ArrayList<Refund>();
                         
                        
                  for (Refund refund : refundList) {
                     if(refund.getPayment().getCard().getCustomer().getCust_ID().equals(user_ID) ){ 
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
        
        out.close();
    
    }
}
