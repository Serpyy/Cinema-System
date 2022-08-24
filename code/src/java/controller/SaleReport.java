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


@WebServlet(name = "SaleReport", urlPatterns = {"/SaleReport"})
public class SaleReport extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
       String fromDate = request.getParameter("fromDate");
       String toDate = request.getParameter("toDate");
       
       double total=0;
       int adulttotal = 0;
       int childtotal = 0;
       
       try {
            
            PaymentDA payment = new PaymentDA();
            TicketDA ticketDA = new TicketDA();
            ArrayList<Payment> list = payment.searchPayement(fromDate, toDate);//out.println("here");
           int totalP = list.size();
            Payment[] paymentlist = new Payment[totalP];
            Ticket[] tickets = new Ticket[totalP];
            
            for (int i = 0; i < list.size(); i++) {
                paymentlist[i] = list.get(i);
                 tickets[i] =ticketDA.getTicket(paymentlist[i].getPayment_no());
                out.println(list.get(i).getPayment_no() + ", " + paymentlist[i].getPayment_date());
                total += paymentlist[i].getAmount();
                adulttotal += paymentlist[i].getAdult_qty();
                childtotal += paymentlist[i].getChild_qty();
            }

            out.println("Total Sales = RM " + total);
            HttpSession s = request.getSession();
            s.setAttribute("PaymentList", paymentlist);
            s.setAttribute("TicketList", tickets);
            s.setAttribute("adult",adulttotal);
            s.setAttribute("child",childtotal);
            s.setAttribute("total",total);
           response.sendRedirect("SaleReport.jsp");
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

