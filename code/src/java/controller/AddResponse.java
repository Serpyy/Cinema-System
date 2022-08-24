package controller;
import model.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "AddResponse", urlPatterns = {"/AddResponse"})
public class AddResponse extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String forwardTo = request.getParameter("forwardTo");
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        try {
            ReviewDA reviewDA = new ReviewDA();
            String reviewNo = request.getParameter("reviewNo");
            String reply = request.getParameter("actualResponse");
            
            reviewDA.addResponse(reviewNo, reply);
            
        } catch (Exception ex){
            out.println("ERROR: " + ex.toString() + "<br /><br />");
            
            StackTraceElement[] elements = ex.getStackTrace();
            for(StackTraceElement e: elements){
                out.println("File Name: " + e.getFileName() + "<br />");
                out.println("Class Name: " + e.getClassName() + "<br />");
                out.println("Method Name: " + e.getMethodName() + "<br />");
                out.println("Line Number: " + e.getLineNumber() + "<br /><br />");                
            }
            out.close();
        }
        
        response.sendRedirect("StaffReview.jsp");
    }
}