/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import model.Customer;
import model.CustomerDA;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
/**
 *
 * @author geremy
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession();
        //String id = request.getParameter("id");
        String cust_name = request.getParameter("name");
        String phone_no = request.getParameter("mobileNumber");
        String email = request.getParameter("email");
        String ic_no = request.getParameter("icNo");
        String password = request.getParameter("password");
        String confirmPassword=request.getParameter("confirmPassword");
        //int securityQuestionOption = Integer.parseInt(request.getParameter("securityQuestion"));
        String answer = request.getParameter("answer");
        String securityQuestion=request.getParameter("securityQuestion");
        int point=0;
        
        try{
        List list=new LinkedList();
            boolean valid=true;
        
        if(valid!=Customer.chkEmail(email)){
            list.add("Invalid email");
            request.setAttribute("errorList",list);
        }
        
        if(valid!=Customer.chkPassword(password)){
            list.add("Invalid password");
            request.setAttribute("errorList",list);
        }
        
        if(!password.equals(confirmPassword)){
            list.add("Password does not match");
            request.setAttribute("errorList",list);
            valid=false;
        }
        
        //check if email is registered in the database
        CustomerDA da;
        da = new CustomerDA();
        Customer registeredCust=new Customer();
            registeredCust=da.getRecordEmail(email);    
        if(registeredCust!=null){
            list.add("Email has already been registered");
            request.setAttribute("errorList",list);
            valid=false;
        }
 
        if(valid){
            //create new customerDA
            CustomerDA custDA = new CustomerDA();

            //get the last customerID from database
            String cust_ID=custDA.getLastCustomer();

            //get the next customer ID from the last Customer ID using increment
            int nextCustID = Integer.parseInt(cust_ID.substring(1,5)) + 1;
            String newCust_ID = String.format("C%0" + cust_ID.substring(1,5).length() + "d", nextCustID);

            Customer customer = new Customer(newCust_ID, cust_name, ic_no, email, password,phone_no,securityQuestion, answer,point);

            //add customer into database
            custDA.addRecord(customer);

            out.println("Successfully added to the database. <br />");

            //Set session of current customer
            Customer cust = custDA.currentCustomer(email, password);
            session.setAttribute("ssLogUser", cust);
            response.sendRedirect("Home.jsp");
        }else{
            RequestDispatcher rd=request.getRequestDispatcher("register.jsp");
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