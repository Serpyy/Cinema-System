package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Staff;
import model.StaffDA;


@WebServlet(name = "AddStaff", urlPatterns = {"/AddStaff"})
public class AddStaff extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        try{
            //retrieve parameter
            String staffID = request.getParameter("staffID");
            String name = request.getParameter("name");
            String role = request.getParameter("role");
            String email = request.getParameter("email");
            String phoneNo = request.getParameter("phoneNo");
            Staff staff = new Staff(staffID, name, role, email, phoneNo);
            StaffDA staffDA = new StaffDA();
            
            List<Staff> staffList = new ArrayList<Staff>();
            staffList = staffDA.getStaff();
            for (int i = 0; i < staffList.size(); i++) {
                if (email.equals(staffList.get(i).getStaffEmail())) {
                    out.println("<script>");
                    out.println("location='MaintainStaff';");
                    out.println("alert('Email already registered.');");
                    out.println("</script>");
                    out.close();
                    response.sendRedirect("MaintainStaff");
                }
            }
            staffDA.addStaff(staff);
            response.sendRedirect("MaintainStaff");
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
