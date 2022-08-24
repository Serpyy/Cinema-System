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
import model.HallDA;
import model.Movie;
import model.MovieDA;
import model.Schedule;
import model.ScheduleDA;

@WebServlet(name = "MaintainSchedule", urlPatterns = {"/MaintainSchedule"})
public class MaintainSchedule extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        RequestDispatcher rd=request.getRequestDispatcher("MaintainSchedule.jsp");
        
        try{
            ArrayList<Schedule> schedule= null;
            ArrayList<String> hall = null;
            ScheduleDA scheduleDA = new ScheduleDA();
            MovieDA movieDA = new MovieDA();
            HallDA hallDA = new HallDA();
            
            //get all hall id
            hall = hallDA.getHallNo();
            //get all movie
            ArrayList<Movie> movie = movieDA.getMovie();
            //get all schedule
            schedule = scheduleDA.getSchedule();
            //get last schedule id
            String lastSchedule = scheduleDA.getLastSchedule();
            String[] parts = lastSchedule.split("SC");
            int scheduleInt = Integer.parseInt(parts[1]);
            scheduleInt++;
            String scheduleNo = String.format("%05d",scheduleInt);
            String scheduleID = "SC"+ scheduleNo; 
            
            request.setAttribute("ssScheduleList",schedule);
            request.setAttribute("ssLastSchedule",scheduleID);
            request.setAttribute("ssMovieList",movie);
            request.setAttribute("ssHallNo",hall);
            
            rd.forward(request,response); 
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
