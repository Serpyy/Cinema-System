package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Staff;
import model.StaffDA;

@WebServlet(name = "SetSecurityQuestion", urlPatterns = {"/SetSecurityQuestion"})
public class SetSecurityQuestion extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();  
        
        try{
            Staff staff=null;
            Staff newStaff=null;
            StaffDA staffDA = new StaffDA();
            String question = request.getParameter("question");
            String answer = request.getParameter("answer");
            //set security question
            staff = (Staff)session.getAttribute("ssLogStaff");
            newStaff = new Staff(staff.getStaffId(),staff.getStaffName(),staff.getStaffEmail(),staff.getStaffPassword(),staff.getRole(),staff.getStaffPhoneNo(),question,answer);
            staffDA.editStaffSec(newStaff);
            session.setAttribute("ssLogStaff",newStaff);
            
            response.sendRedirect("StaffProfile.jsp");
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
