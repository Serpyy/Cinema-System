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



@WebServlet(name = "StaffLogin", urlPatterns = {"/StaffLogin"})
public class StaffLogin extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(); 
        RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
                
        try{
            List list=new LinkedList();
            String email=request.getParameter("email");
            String password=request.getParameter("password");
            
            StaffDA staffDA = new StaffDA();
            Staff staff = staffDA.checkStaff(email,password);
            if (staff!=null){
                //if staff exist
                if(staff.getSecurityQuestion()==null){
                    //if security question not set
                    session.setAttribute("ssLogStaff", staff);
                    response.sendRedirect("SetSecurityQuestion.jsp");
                }else{
                    //if security question set
                    session.setAttribute("ssLogStaff", staff);
                    response.sendRedirect("StaffProfile.jsp");
                }
            }else{
                //if staff does not exist and return error
                list.add("Invalid email or password");
                request.setAttribute("errorList2",list);
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
