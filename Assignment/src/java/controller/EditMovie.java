package controller;

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
import model.ActorDA;
import model.CastDA;
import model.Movie;
import model.MovieDA;

@WebServlet(name = "EditMovie", urlPatterns = {"/EditMovie"})
@MultipartConfig(location="/tmp", fileSizeThreshold=1024*1024, maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class EditMovie extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter(); 
        RequestDispatcher rd=request.getRequestDispatcher("EditMovie.jsp");
        
        try{
            ActorDA actorDA = new ActorDA();
            MovieDA movieDA = new MovieDA();
            CastDA castDA = new CastDA();
 
            //retrieve parameter
            String movieID=request.getParameter("movieID");
            //get selected movie by id
            Movie movie=movieDA.getCurrentMovie(movieID);
            //get all actor list
            ArrayList<String> actor=actorDA.getActor();
            //get this movie cast
            ArrayList<String> casts=castDA.getCast(movieID);
            
            request.setAttribute("ssActorList", actor);
            request.setAttribute("ssCurrentMovie",movie);
            request.setAttribute("ssCurrentCast",casts);
            
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
            ArrayList<String> actorIDList = new ArrayList<String>();
            ArrayList<String> allCast = new ArrayList<String>();
            MovieDA movieDA = new MovieDA();
            Movie movie =null;
            int j=0;
            
            //retrieve all parameter
            String movieID = request.getParameter("movieID");
            String movieTitle = request.getParameter("movieTitle");
            int duration = Integer.parseInt(request.getParameter("duration"));
            String ageRating = request.getParameter("ageRating");
            LocalDate releaseDate = LocalDate.parse(request.getParameter("releaseDate"));
            String cast[] = request.getParameter("cast").split(",");
            String director = request.getParameter("director");
            String distributor = request.getParameter("distributor");
            String synopsis = request.getParameter("synopsis");
            Part filePart = request.getPart("poster");
            //update movie details without poster
            if(filePart.getSubmittedFileName()==""){
                movie = new Movie(movieID,movieTitle,duration,releaseDate,synopsis,director,distributor,ageRating);
                movieDA.updateMovieNP(movie);     
            }else{
            //update movie details with poster
                InputStream inputStream = filePart.getInputStream();  
                movie = new Movie(movieID,movieTitle,duration,releaseDate,synopsis,director,distributor,ageRating, inputStream, filePart.getSize());
                movieDA.updateMovie(movie);  
            }

            ActorDA actorDA = new ActorDA();
            CastDA castDA = new CastDA();
            ArrayList<String> actor = new ArrayList<String>();
            ArrayList<String> duplicateActor = new ArrayList<String>();
            ArrayList<String> currentCast = new ArrayList<String>();
            String[]casts=request.getParameter("cast").split(",");
            //get all actor
            actor=actorDA.getActor();
            //get all actorID
            allCast=castDA.getAllCast();
            //get all casts for this movie
            currentCast = castDA.getCast(movieID);
            ArrayList<String> casts1 = new ArrayList<String>(Arrays.asList(casts)); 
            
            //add new casts if actor has been registered
            for(int i=0;i<casts1.size();i++){
                if(actor.contains(casts1.get(i))){
                    if(!currentCast.contains(casts1.get(i))){
                        actorID=actorDA.getActorID(casts1.get(i));
                        castDA.addCast(movieID,actorID);
                    }
                }
            }
            //add new casts and actor if actor has not been registered    
            for(int i=0;i<casts1.size();i++){
                if(!actor.contains(casts1.get(i))){
                    actorID=actorDA.addActor(casts1.get(i));
                    actorIDList.add(actorID);         
                    castDA.addCast(movieID,actorIDList.get(j));
                    j++;
                }
            }
            j=0;
            //delete casts only if removed from cast list for this movie
            for(int i=0;i<currentCast.size();i++){
                if(!casts1.contains(currentCast.get(i))){
                    j=0;
                    for(int k=0;k<allCast.size();k++){
                        if(allCast.get(k).equals(currentCast.get(i))){
                            j++;
                        }
                    }
                    if(j==2){
                        actorID=actorDA.getActorID(currentCast.get(i));
                        castDA.deleteCast(movieID, actorID);
                    }else{
                        actorID=actorDA.getActorID(currentCast.get(i));
                        castDA.deleteCast(movieID, actorID);
                        actorDA.deleteActor(actorID);
                    }
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
