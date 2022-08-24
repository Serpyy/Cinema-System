package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CastDA;
import model.Customer;
import model.CustomerDA;
import model.*;
import model.MovieDA;
import model.ScheduleDA;

@WebServlet(name = "DateTimeSelectionServlet", urlPatterns = {"/DateTimeSelectionServlet"})
public class DateTimeSelectionServlet extends HttpServlet {

    //Will run to after user navigate from the home page to retrieve the schedule date of the selected movie
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
//        //The entire try will be deleted cuz they will be passed from other ppl
//        try{  
//            MovieDA movieDA = new MovieDA();
//            Movie tempMovie = movieDA.getCurrentMovie("M008");
//            session.setAttribute("ssSelectedMovie", tempMovie);
//
//            CustomerDA customerDA = new CustomerDA();
//            Customer tempCustomer = customerDA.getRecord("C0012");
//            session.setAttribute("ssLogUser", tempCustomer);
//        }catch(Exception ex) {
//            ex.getMessage();
//        }


        try{
            //Retrieve the movie details that user has selected
            Movie movie = (Movie) session.getAttribute("ssSelectedMovie");
            CastDA castDA = new CastDA();
            ArrayList<String> casts = castDA.getCast(movie.getMovieID()); //to retrieve the casts involved in this movie and later display in the jsp page
            session.setAttribute("ssTicMovieSelectedCasts", casts); //Save the cast details into the session
            //search the schedule of this movie from db
            ScheduleDA scheduleDA = new ScheduleDA();
            ArrayList<LocalDate> scheduleDate = scheduleDA.retrieveScheduleDate(movie.getMovieID());

            //Save the schedule date's list into session (to be displayed in JSP page)
            session.setAttribute("ssTicScheduleDate", scheduleDate);
            //So that the schedule time part (the statement after if in the JSP will not be execute for the first time (cuz user havent select the date)
            session.setAttribute("ssTicScheduleDateBtnChk", ""); 
            //Redirect to JSP to display the date to the user
            response.sendRedirect("DateTimeSelection.jsp");
        }catch(Exception ex) {
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

    //Will be executed after user has selected either one of the schedule date button 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            String selectedBtnId = request.getParameter("clickedBtn"); //to identify which Btn is triggering this method (user is selecting which time)
            LocalDate selectedDate = LocalDate.parse(request.getParameter(selectedBtnId));  //get the value (date) inside the selected button

            HttpSession session = request.getSession(); 
            Movie movie = (Movie) session.getAttribute("ssSelectedMovie"); //Obtain the movie details from the session
            session.setAttribute("ssTicSelectedDate", selectedDate);  //save the selectedDate into the session (to pass to the next page)
            session.setAttribute("ssTicScheduleDateBtnChk", selectedBtnId); //Save the selected btnId to the session so that its color can be changed accordingly

            //Retrieve the schedule time based on the selected schedule date and movie id
            ScheduleDA scheduleDA = new ScheduleDA();
            ArrayList<LocalTime> scheduleTime = scheduleDA.retrieveScheduleTime(movie.getMovieID(), selectedDate);

            //Store the schedule time array list into the session (to be displayed in the next page)
            session.setAttribute("ssTicScheduleTime", scheduleTime);

            response.sendRedirect("DateTimeSelection.jsp");
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
