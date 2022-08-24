package controller;

import model.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CustomerDA;

@WebServlet(name = "CustomerReport", urlPatterns = {"/CustomerReport"})
public class CustomerReport extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {

            CustomerDA customer = new CustomerDA();
            ArrayList<Customer> list = customer.getCustomer();
            
            int totalC = list.size();
            Customer[] customerlist = new Customer[totalC];
            for (int i = 0; i < list.size(); i++) {
                customerlist[i] = list.get(i);
            } 
            HttpSession s = request.getSession();
            s.setAttribute("CustomerList", customerlist);
            response.sendRedirect("CustomerRecord.jsp");

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
