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

/**
 *
 * @author geremy
 */
@WebServlet(name = "chngePwd", urlPatterns = {"/chngePwd"})
public class chngePwd extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();  
        
        try{
      
                CustomerDA retrieveDetails = new CustomerDA();
                String inputEmail = request.getParameter("email"); 
                Customer customer=retrieveDetails.getRecordEmail(inputEmail);
                //check for valid email
                List list=new LinkedList();
                boolean valid=true;
                valid=Customer.chkEmail(inputEmail);
                
                if(!valid){
                    //add error msg
                    list.add("Invalid email");
                    request.setAttribute("errorList",list);
                    RequestDispatcher rd=request.getRequestDispatcher("changePassword.jsp");
                    rd.forward(request,response);
                }
                else{
                    session.setAttribute("ssLogUser",customer);
                    response.sendRedirect("changePassword1.jsp");
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
    
    @Override
       protected void doPost(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            
            try{
            String inputAnswer=request.getParameter("answer");
            
            List list=new LinkedList();
            boolean valid=true;
            
            HttpSession session = request.getSession();
            Customer c=(Customer) session.getAttribute("ssLogUser");
            
            String securityQuestion=c.getSecurity_question();
            String answer = c.getAnswer();

            //check if answer is correct
            if(!inputAnswer.toUpperCase().equals(answer.toUpperCase())){
                //add error msg
                list.add("Answer to security question is incorrect");
                request.setAttribute("errorList",list);
                RequestDispatcher rd=request.getRequestDispatcher("changePassword1.jsp");
                rd.forward(request,response);
            }else{
                response.sendRedirect("changePassword2.jsp");
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
