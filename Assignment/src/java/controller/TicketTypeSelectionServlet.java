package controller;

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
import model.*;

@WebServlet(name = "TicketTypeSelectionServlet", urlPatterns = {"/TicketTypeSelectionServlet"})
public class TicketTypeSelectionServlet extends HttpServlet {
    
    //Trigger after user has finish selecting the ticket type and press the proceed btn
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String selectedSeat = (String) session.getAttribute("selectedSeat");
        String[] selectedSeatArr = selectedSeat.split(" "); //split the seat no to be stored 1 by 1 (for Ticket object geneate purpose)
        Schedule schedule = (Schedule) session.getAttribute("ssTicSelectedSchedule"); 
        int childTicketSelected;
        try{
            childTicketSelected = Integer.parseInt((String) request.getParameter("childQty"));
        }catch(Exception ex){ //If exception thrown, then it means the number box has been disabled and disabled means user not allow to buy child ticket, thats why i set to 0 after catch
            childTicketSelected = 0;
        }
        int adultTicketSelected = Integer.parseInt((String) request.getParameter("adultQty"));
        double adultUnitPrice = Double.parseDouble((String)request.getParameter("adultUnitPrice"));
        double childUnitPrice = Double.parseDouble((String)request.getParameter("childUnitPrice"));
        double ttlAmount = Double.parseDouble((String)request.getParameter("ttlPrice"));
        
        //Create an incomplete Payment object and store in session (will be completed up after payment has successful)
        Payment payment = new Payment(ttlAmount, childTicketSelected, adultTicketSelected); 
        session.setAttribute("ssTicPayment", payment);
        
        //Create incomplete Ticket objects and store in session (will be completed up after payment has successful)
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        for(int i=0; i<adultTicketSelected; i++){ //loop to create adult ticket objects
            tickets.add(new Ticket(schedule, adultUnitPrice, "ADULT", selectedSeatArr[i]));
        }
        for(int i=0; i<childTicketSelected; i++){ //loop to create child ticket objects 
            tickets.add(new Ticket(schedule, childUnitPrice, "CHILD", selectedSeatArr[i + adultTicketSelected]));
        }
        session.setAttribute("ssTicPurchasedTicketList", tickets); 
        
        session.setAttribute("ssTicVoucherValidation", "Valid"); //set this to "valid" so that the alert box in the next page (transaction summary) will not be triggered.
        response.sendRedirect("TransactionSummary.jsp"); //Proceed to the transaction summary page
    }

}
