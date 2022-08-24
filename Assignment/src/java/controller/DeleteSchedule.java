package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.PaymentDA;
import model.RefundDA;
import model.ScheduleDA;
import model.TicketDA;

@WebServlet(name = "DeleteSchedule", urlPatterns = {"/DeleteSchedule"})
public class DeleteSchedule extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
            
        try{
            ScheduleDA scheduleDA = new ScheduleDA();
            TicketDA ticketDA = new TicketDA();
            PaymentDA paymentDA = new PaymentDA();
            RefundDA refundDA = new RefundDA();
            
            String scheduleID = request.getParameter("scheduleID");
            ArrayList<String> paymentNo = ticketDA.getPaymentNo(scheduleID);
            ticketDA.deleteTicket(scheduleID);
            for(int i=0;i<paymentNo.size();i++){
                refundDA.deleteRefund(paymentNo.get(i));
                paymentDA.deletePayment(paymentNo.get(i)); 
            }
            scheduleDA.deleteSchedule(scheduleID);
            
            response.sendRedirect("MaintainSchedule");
        }catch(Exception ex){            
            out.println("ERROR: " + ex.toString() + "<br /><br />");
            StackTraceElement[] elements = ex.getStackTrace();
            for(StackTraceElement e: elements){
                 out.println("File Name: " + e.getFileName() + "<br />");
                 out.println("Class Name: " + e.getClassName() + "<br />");
                 out.println("Method Name: " + e.getMethodName() + "<br />");
                 out.println("Line Number: " + e.getLineNumber() + "<br /><br />");
            }
        }
    }
}
