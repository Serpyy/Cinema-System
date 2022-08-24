package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Staff;
import model.StaffDA;

@WebServlet(urlPatterns = {"/MaintainStaff"})
public class MaintainStaff extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        RequestDispatcher rd=request.getRequestDispatcher("MaintainStaff.jsp");
        
        try{
            ArrayList<Staff> staff= null;
            StaffDA staffDA = new StaffDA();
            
            //get all staff
            staff = staffDA.getStaff();
            
            //retrieve last staff id
            String lastStaff = staffDA.getLastStaff();
            String[] parts = lastStaff.split("S");
            int staffInt = Integer.parseInt(parts[1]);
            staffInt++;
            String staffNo = String.format("%03d",staffInt);
            String staffID = "S"+ staffNo; 
            
            request.setAttribute("ssStaffList",staff);
            request.setAttribute("ssLastStaff",staffID);
            
            rd.forward(request,response); 
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
