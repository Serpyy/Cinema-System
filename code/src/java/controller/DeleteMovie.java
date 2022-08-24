package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ActorDA;
import model.CastDA;
import model.MovieDA;

@WebServlet(name = "DeleteMovie", urlPatterns = {"/DeleteMovie"})
public class DeleteMovie extends HttpServlet {
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
   
        try{
            //retrieve parameter
            String movieID = request.getParameter("movieID");
            ActorDA actorDA = new ActorDA();
            CastDA castDA = new CastDA();
            MovieDA movieDA = new MovieDA();
            //get all actor for this movie
            ArrayList<String> deleteActor= castDA.getCastID(movieID);
            //check if actor is only reegistered for this movie
            for(int i=0;i<deleteActor.size();i++){
                boolean dup = castDA.checkCast(deleteActor.get(i));
                if(dup==false){
                    deleteActor.remove(i);
                    i--;
                }
            }
            //delete all related cast
            castDA.deleteCastWithMovie(movieID);
            //delete all related actor that is not registered in another movie
            for(int i=0;i<deleteActor.size();i++){
                actorDA.deleteActor(deleteActor.get(i));
            }
            //delete movie
            movieDA.deleteMovie(movieID);

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
