package controller;

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
import model.Staff;
import model.StaffDA;

@WebServlet(name = "ChangeStaffPassword", urlPatterns = {"/ChangeStaffPassword"})
public class ChangeStaffPassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();  
        RequestDispatcher rd=request.getRequestDispatcher("ChangeStaffPassword.jsp");
        
        try{
            List list=new LinkedList();
            String email= request.getParameter("email");
            StaffDA staffDA = new StaffDA();
            Staff staff = staffDA.checkEmail(email);
            if(staff!=null){
                //proceed if email exist and get current staff record
                if(staff.getSecurityQuestion()==null){
                    list.add("You have not set up your security question.");
                    request.setAttribute("errorList",list);
                    rd.forward(request,response);
                }else{
                    session.setAttribute("ssCurrentStaff",staff);
                    response.sendRedirect("ChangeStaffPassword1.jsp");
                }
            }else{
                //return error
                list.add("Invalid email");
                request.setAttribute("errorList",list);
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

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();  
        RequestDispatcher rd=request.getRequestDispatcher("ChangeStaffPassword1.jsp");
        
        try{
            StaffDA staffDA = new StaffDA();
            List list=new LinkedList();
            String answer = request.getParameter("answer");
            Staff staff = (Staff)session.getAttribute("ssCurrentStaff");
            if(staff.getAnswer().toUpperCase().equals(answer.toUpperCase())){
                //proceed if answer correct
                response.sendRedirect("ChangeStaffPassword2.jsp");
            }else{
                //return error
                list.add("Invalid answer");
                request.setAttribute("errorList",list);
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
