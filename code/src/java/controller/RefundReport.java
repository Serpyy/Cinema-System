
package controller;

import model.Refund;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.RefundDA;


@WebServlet(name = "RefundReport", urlPatterns = {"/RefundReport"})
public class RefundReport extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            RefundDA refund = new RefundDA();
            ArrayList<Refund> list = refund.getRefunds();
            
            int totalR = list.size();
            Refund[] refundlist = new Refund[totalR];
            for (int i = 0; i < list.size(); i++) {
                refundlist[i] = list.get(i);
            } 
            HttpSession s = request.getSession();
            s.setAttribute("RefundList", refundlist);
            response.sendRedirect("RefundReport.jsp");

        } catch (Exception ex) {
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