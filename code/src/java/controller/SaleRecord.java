package controller;

import model.Payment;
import model.Ticket;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.PaymentDA;
import model.TicketDA;

@WebServlet(name = "SaleRecord", urlPatterns = {"/SaleRecord"})
public class SaleRecord extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            
            PaymentDA payment = new PaymentDA();
            TicketDA ticketDA = new TicketDA();
            ArrayList<Payment> list = payment.getPayments();
           int totalP = list.size();
           Ticket[] tickets = new Ticket[totalP];
            Payment[] paymentlist = new Payment[totalP];
            double totalamount=0;
            for (int i = 0; i < list.size(); i++) {
                paymentlist[i] = list.get(i);
                tickets[i] = ticketDA.getTicket(paymentlist[i].getPayment_no());
                totalamount += paymentlist[i].getAmount();
            }
           
            HttpSession s = request.getSession();
            s.setAttribute("PaymentList", paymentlist);
            s.setAttribute("TicketList", tickets);
           response.sendRedirect("SalesRecord.jsp");

        } catch (Exception ex) {
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