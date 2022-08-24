/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import model.CustomerDA;
import model.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author geremy
 */
@WebServlet(name = "editProfile", urlPatterns = {"/editProfile"})
public class editProfile extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        
        try{
        Customer c=(Customer) session.getAttribute("ssLogUser");
        
        String cust_id = c.getCust_ID();
        String cust_name = request.getParameter("name");
        String phone_no = request.getParameter("mobileNumber");
        String email = request.getParameter("email");
        String ic_no = request.getParameter("icNo");
        String cust_password = c.getCust_password();
        String securityQuestion=request.getParameter("securityQuestion");
        String answer =request.getParameter("answer");
        int point=c.getPoint();
        
        List list=new LinkedList();
        boolean valid=true;
        
        if(valid!=Customer.chkEmail(email)){
            list.add("Invalid email");
            request.setAttribute("errorList",list);
        }
        
        //check if cust had edit the email
        if(!c.getEmail().equals(email)){
            //check if email is registered in the database
            CustomerDA da;
            try {
                da = new CustomerDA();
                Customer registeredCust=new Customer();
                try {
                    registeredCust=da.getRecordEmail(email);
                } catch (SQLException ex) {
                    Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(registeredCust!=null){
                    list.add("Email has already been registered");
                    request.setAttribute("errorList",list);
                    valid=false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(editProfile.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
       
        
        if(valid){
            try{
                CustomerDA custDA = new CustomerDA();

                Customer customer = new Customer(cust_id, cust_name, ic_no, email, cust_password, phone_no ,securityQuestion, answer, point);

                custDA.updateRecord(customer);

                session.setAttribute("ssLogUser", customer);
                response.sendRedirect("viewProfile.jsp");
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
        }else{
            RequestDispatcher rd=request.getRequestDispatcher("editProfile.jsp");
            rd.forward(request,response);
        }
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
