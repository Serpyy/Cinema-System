package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.*;

@WebServlet(name = "SeatSelectionServlet", urlPatterns = {"/SeatSelectionServlet"})
public class SeatSelectionServlet extends HttpServlet {

    //Will be triggered after the user has select the scehdule date and time and press the proceed btn
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try{
            HttpSession session = request.getSession();
            ScheduleDA scheduleDA = new ScheduleDA();
            //Get the complete schedule object based on movie id, date and time (include the Hall obj (seat layout) also)
            Schedule selectedSchedule = scheduleDA.retrieveSchedule((Movie)session.getAttribute("ssSelectedMovie"), LocalDate.parse(request.getParameter("selectedDate")), LocalTime.parse(request.getParameter("scheduleTime")));
            
            //after retrieve hall obj, we will create a 2d boolean array (row and col based on the seat layout inside the hall obj)
            //Initialise the 2D boolean array with all true(available)
            boolean[][] seatAvailability = new boolean[selectedSchedule.getHall().getRow()][selectedSchedule.getHall().getColumn()];
            for(int i=0; i<seatAvailability.length; i++){
                for(int j=0; j<seatAvailability[i].length; j++){
                    seatAvailability[i][j] = true;
                }
            }

            //Retrieve the seat no assigned to the ticket record that having the specified schedule no
            TicketDA ticketDA = new TicketDA();
            ArrayList<String> selectedSeat = ticketDA.retrieveSelectedSeat(selectedSchedule.getSchedule_no());
            int row =0;
            int column = 0;
            //Loop to set the respective position (based on the seat no) to false
            for(int i=0; i<seatAvailability.length; i++){
                for(int j=0; j<seatAvailability[i].length; j++){
                    for(int k=0; k<selectedSeat.size(); k++){
                        row = selectedSeat.get(k).charAt(0) - 65;
                        column = (selectedSeat.get(k).charAt(1) - 48) * 10 + selectedSeat.get(k).charAt(2) - 49;
                        seatAvailability[row][column] = false;
                    }
                }
            }

            //Lastly pass the 2d boolean to the jsp page to display the layout to user
            session.setAttribute("ssTicSelectedSchedule", selectedSchedule);
            session.setAttribute("seatAvaiable", seatAvailability);

            response.sendRedirect("SeatSelection.jsp");
            
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
}
