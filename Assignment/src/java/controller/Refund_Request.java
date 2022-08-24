package controller;

import model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Refund_Request", urlPatterns = {"/Refund_Request"})
public class Refund_Request extends HttpServlet {

    private PaymentDA paymentDA;
    private CustomerDA customerDA;
    private RefundDA refundDA;
    private StaffDA staffDA;
    private TicketDA ticketDA;
        
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        out.print("<H1>Hello </H1>");
        
        try{
            paymentDA = new PaymentDA();
            customerDA = new CustomerDA();
            ticketDA = new TicketDA();
            
            String user_ID = (String)session.getAttribute("ssUser_ID");
            System.out.print(user_ID);
            Customer cust = customerDA.getRecord(user_ID);
            System.out.print(cust.getCust_ID());
            
            ArrayList<Payment> paymentList = paymentDA.getRecord(cust);
            ArrayList<Payment> sortedpaymentList = (ArrayList<Payment>)paymentList.clone();
            ArrayList<Refund> refundList = (ArrayList<Refund>)session.getAttribute("ssRefundList");
            ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
            
            for(int i = 0 ;i < paymentList.size();i++){
                for(int j = 0 ; j < refundList.size()  ;j++){
                    if(paymentList.get(i).getPayment_no().equals(refundList.get(j).getPayment().getPayment_no())){
                       sortedpaymentList.remove(paymentList.get(i)); 
                    }
                    
                     ArrayList<Ticket> paymentTicket = ticketDA.getRecord(paymentList.get(i));
                     if(paymentTicket.get(0).getSchedule().getSchedule_date().isBefore(LocalDate.now())){
                                sortedpaymentList.remove(paymentList.get(i));
                                System.out.print(paymentTicket.get(0).getSchedule().getSchedule_date());
                     }
                     
                     if(paymentTicket.get(0).getSchedule().getSchedule_date().isEqual(LocalDate.now())){
                         if(paymentTicket.get(0).getSchedule().getSchedule_time().isBefore(LocalTime.now())){
                                 sortedpaymentList.remove(paymentList.get(i));
                                System.out.print(paymentTicket.get(0).getSchedule().getSchedule_date());
                         }
                     }
                }
            }
            
            //get ticket list form payment
            for ( Payment payment:  sortedpaymentList) {
                ArrayList<Ticket> paymentTicket = ticketDA.getRecord(payment);
                ticketList.addAll(paymentTicket);
            }
            
            System.out.println("Check Sorted List");
            
            session.setAttribute("ssPaymentList", sortedpaymentList);
            session.setAttribute("ssTicketList", ticketList);
            
            for(Payment payment : paymentList){
                
                System.out.println(payment.getPayment_no());
                System.out.println(payment.getPayment_date());
            }
            
            response.sendRedirect("RequestRefund.jsp");
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
            paymentDA = new PaymentDA();
            refundDA = new RefundDA();
            staffDA = new StaffDA();
            ticketDA = new TicketDA();
          
            String payment_no = request.getParameter("txtRefundPayementNo");
            String reason = request.getParameter("txtreason");
            
            System.out.print(reason);

            ArrayList<Staff> managerList = staffDA.getManager();
            Payment payment = paymentDA.getRecord(payment_no);
            ArrayList<Ticket> refundTicketList = ticketDA.getRecord(payment);

            int max_val = managerList.size();
            Random ran = new Random();
            int x = ran.nextInt(max_val) + 1;
            System.out.println("Random Number: "+x);
            Staff staff = managerList.get(x-1);
           
            Refund refund = new Refund(payment, staff, reason);
            refund.setRefund_no(String.format("R%06d", refundDA.setNumberOfRecord()));
            
            System.out.println(refund.getReason());
            
            ArrayList<Refund> refundList = refundDA.getRecord();
            boolean add = true;
           
            refundDA.addRecord(refund);
           
            session.setAttribute("ssRefund", refund);
            session.setAttribute("ssRefundTicketList", refundTicketList);
            response.sendRedirect("NewRefund.jsp");
            
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
