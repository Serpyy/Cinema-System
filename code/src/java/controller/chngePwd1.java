package controller;

import model.CustomerDA;
import model.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "chngePwd1", urlPatterns = {"/chngePwd1"})
public class chngePwd1 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();  
        
        try{
        List list=new LinkedList();
        boolean valid=true;
        
        
        Customer c=(Customer) session.getAttribute("ssLogUser");

        String newPassword=request.getParameter("password");
        String confirmNewPassword=request.getParameter("confirmPassword");

        String cust_id = c.getCust_ID();
        String cust_name = c.getCust_name();
        String phone_no = c.getPhone_no();
        String email=c.getEmail();
        String ic_no = c.getIc_no();
        String securityQuestion=c.getSecurity_question();
        String answer = c.getAnswer();

        int point=c.getPoint();

        //check if password matches
        if(!newPassword.equals(confirmNewPassword)){
            //add error msg
            list.add("Password does not match");
            request.setAttribute("errorList",list);
            valid=false;
        }

        //if it is valid
        if(valid==true){
                CustomerDA custDA = new CustomerDA();

                Customer customer = new Customer(cust_id, cust_name, ic_no, email, newPassword, phone_no ,securityQuestion, answer, point);
                custDA.updateRecord(customer);

                session.setAttribute("ssLogUser", customer);
                response.sendRedirect("login.jsp");
        }
        else{
            RequestDispatcher rd=request.getRequestDispatcher("changePassword2.jsp");
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
