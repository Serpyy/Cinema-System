
package controller;

import model.Movie;
import model.MovieDA;
import model.ActorDA;
import model.CastDA;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(location="/tmp", fileSizeThreshold=1024*1024, maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
@WebServlet(name = "AddMovie", urlPatterns = {"/AddMovie"})
public class AddMovie extends HttpServlet {

        
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        RequestDispatcher rd=request.getRequestDispatcher("AddMovie.jsp");
                
        try{
            MovieDA movieDA = new MovieDA();
            ActorDA actorDA = new ActorDA();
            //get last movie ID
            String lastMovie = movieDA.getLastMovie();
            String[] parts = lastMovie.split("M");
            int movieInt = Integer.parseInt(parts[1]);
            movieInt++;
            String movieNo = String.format("%03d",movieInt);
            String movieID = "M"+ movieNo; 
            //get all actor for search
            ArrayList<String> actor=actorDA.getActor();

            request.setAttribute("ssLastMovieID", movieID);
            request.setAttribute("ssActorList", actor);
        
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
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();   
        
        try{
            String actorID=null;
            String dupActorID=null;
            ArrayList<String> actorIDList = new ArrayList<String>();
            ArrayList<String> actor = new ArrayList<String>();
            ArrayList<String> duplicateActor = new ArrayList<String>();
            MovieDA movieDA = new MovieDA(); 
            ActorDA actorDA = new ActorDA();
            CastDA castDA = new CastDA();
            int j=0;
            //retrieve all parameter
            String movieID = request.getParameter("movieID");
            String movieTitle = request.getParameter("movieTitle");
            Part poster = request.getPart("poster");
            InputStream inputStream = poster.getInputStream();
            int duration = Integer.parseInt(request.getParameter("duration"));
            String ageRating = request.getParameter("ageRating");
            LocalDate releaseDate = LocalDate.parse(request.getParameter("releaseDate"));
            String cast[] = request.getParameter("cast").split(",");
            String director = request.getParameter("director");
            String distributor = request.getParameter("distributor");
            String synopsis = request.getParameter("synopsis");
        
            Movie movie = new Movie(movieID,movieTitle,duration,releaseDate,synopsis,director,distributor,ageRating, inputStream, poster.getSize());
            //add movie
            movieDA.addMovie(movie);        
            //get all cast for this movie
            String [] casts=request.getParameter("cast").split(",");
            //get all actor
            actor=actorDA.getActor();
            ArrayList<String> casts1 = new ArrayList<String>(Arrays.asList(casts));     
            for(int i=0;i<casts1.size();i++){
                //if actor has been registered only add to cast
                if(actor.contains(casts1.get(i))){
                    dupActorID = actorDA.getActorID(casts1.get(i));
                    castDA.addCast(movieID,dupActorID);
                }else{
                //if actor has not been registered add to cast and staff
                    actorID=actorDA.addActor(casts1.get(i));
                    actorIDList.add(actorID);
                    castDA.addCast(movieID,actorIDList.get(j));
                    j++;
                }
            }
        
            response.sendRedirect("MaintainMovie");
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
    


