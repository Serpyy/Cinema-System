package controller;

import model.Payment;
import model.Schedule;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.PaymentDA;
import model.ScheduleDA;
import model.TicketDA;

@WebServlet(name = "MovieReport", urlPatterns = {"/MovieReport"})
public class MovieReport extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        double salesAmout = 0;
        
        try {
            PaymentDA payDA = new PaymentDA();
            String movieID = request.getParameter("movieID");
            out.println(movieID);
            ScheduleDA movie = new ScheduleDA();
            ArrayList<Schedule> list = movie.getSchedules(movieID);
            double salesAmount = 0;
            Schedule[] schedules = new Schedule[list.size()];
            for (int i = 0; i < list.size(); i++) {
                schedules[i] = list.get(i);
            
            }

            PaymentDA payment = new PaymentDA();
             TicketDA ticketDA = new TicketDA();
            ArrayList<Payment> list2 = payment.getPayments();
            int totalP = list2.size();
            Payment[] paymentlist = new Payment[totalP];

            for (int i = 0; i < list2.size(); i++) {
                paymentlist[i] = list2.get(i);
                
            }
            out.println(list2.size());
            
            int[] adult= new int[paymentlist.length];
            int[] child= new int[paymentlist.length];
            double[] scheduleTotal = new double[schedules.length];
            for (int i = 0; i < paymentlist.length; i++) {
                    for (int a = 0; a < schedules.length; a++) {
                        if (ticketDA.getTicket(paymentlist[i].getPayment_no()).getSchedule().getSchedule_no().equalsIgnoreCase((schedules[a].getSchedule_no()))) {
                           scheduleTotal[a] = 0;
                           scheduleTotal[a] += scheduleTotal[a] + paymentlist[i].getAmount();
                              adult[a] +=paymentlist[i].getAdult_qty();
                              child[a] +=paymentlist[i].getChild_qty();
                        }
                    }

                
            }
            
            HttpSession s = request.getSession();
            s.setAttribute("scheduleLists", schedules);
            s.setAttribute("scheduleTotal", scheduleTotal);
             s.setAttribute("adult", adult);
             s.setAttribute("child", child);
            response.sendRedirect("MovieReport.jsp");

        } catch (SQLException ex) {
            out.println("Error:" + ex.getMessage());
        }
    }

}
