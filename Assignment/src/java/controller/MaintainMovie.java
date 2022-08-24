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
import model.CastDA;
import model.Movie;
import model.MovieDA;
import model.Schedule;
import model.ScheduleDA;

@WebServlet(name = "MaintainMovie", urlPatterns = {"/MaintainMovie"})
public class MaintainMovie extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        RequestDispatcher rd=request.getRequestDispatcher("MaintainMovie.jsp");  
        
        try{
            ArrayList<Schedule> schedule= null; 
            MovieDA movieDA = new MovieDA();
            CastDA castDA = new CastDA();
            ScheduleDA scheduleDA = new ScheduleDA();
            //get all schedule
            schedule = scheduleDA.getSchedule();
            //get all movie
            ArrayList<Movie> movies=movieDA.getMovie();
            request.setAttribute("ssMovieList",movies);
            request.setAttribute("ssScheduleList",schedule);
        
            for(int i =0; i<movies.size();i++){
                ArrayList<String> casts = castDA.getCast(movies.get(i).getMovieID());
                request.setAttribute("cast" + i,casts);
            }
        
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
