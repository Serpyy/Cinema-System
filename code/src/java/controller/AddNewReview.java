package controller;
import model.*;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "AddNewReview", urlPatterns = {"/AddNewReview"})
public class AddNewReview extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String forwardTo = request.getParameter("forwardTo");
        
        Customer customer = new Customer();
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        try {
            ReviewDA reviewDA = new ReviewDA();
            
            String reviewNo = reviewDA.getLastReview();
            String comment = request.getParameter("comment");
            if (comment == null || comment.equals("")) {
                comment = "No comment available.";
            } 
            int rating = Integer.parseInt(request.getParameter("rate"));
            
            HttpSession session = request.getSession();
            
            customer = (Customer)session.getAttribute("ssLogUser");
            
            
            Review review = new Review(reviewNo, comment, rating, customer);
            reviewDA.addReview(review);
            
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
        
        response.sendRedirect("CustomerReview.jsp");
    }
}
