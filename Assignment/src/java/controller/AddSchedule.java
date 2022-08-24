package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Hall;
import model.Movie;
import model.Schedule;
import model.ScheduleDA;

@WebServlet(name = "AddSchedule", urlPatterns = {"/AddSchedule"})
public class AddSchedule extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
 
        try{
            //retrieve parameter
            String scheduleID = request.getParameter("scheduleID");
            String movieID = request.getParameter("movieID");
            String hallNo = request.getParameter("hall");
            LocalTime time = LocalTime.parse(request.getParameter("time"));
            LocalDate date = LocalDate.parse(request.getParameter("date"));
            Movie movie = new Movie(movieID);
            Hall hall= new Hall(hallNo);
            Schedule schedule = new Schedule(scheduleID, movie, hall, time, date);
            ScheduleDA scheduleDA = new ScheduleDA();
            //add schedule
            scheduleDA.addSchedule(schedule);
            
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

