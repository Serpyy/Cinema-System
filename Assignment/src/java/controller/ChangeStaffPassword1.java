package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Staff;
import model.StaffDA;


@WebServlet(name = "ChangeStaffPassword1", urlPatterns = {"/ChangeStaffPassword1"})
public class ChangeStaffPassword1 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();  
        RequestDispatcher rd=request.getRequestDispatcher("ChangeStaffPassword1.jsp");
        
        try{
            Staff newStaff = null;
            StaffDA staffDA= new StaffDA();
            //change staff password
            String password = request.getParameter("password");
            Staff staff = (Staff)session.getAttribute("ssCurrentStaff");
            newStaff = new Staff(staff.getStaffId(),staff.getStaffName(),staff.getStaffEmail(),password,staff.getRole(),staff.getStaffPhoneNo(),staff.getSecurityQuestion(),staff.getAnswer());
            staffDA.editStaffPassword(newStaff);
            
            response.sendRedirect("login.jsp");
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
