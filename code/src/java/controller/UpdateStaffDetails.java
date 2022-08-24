package controller;
import model.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "UpdateStaffDetails", urlPatterns = {"/UpdateStaffDetails"})
public class UpdateStaffDetails extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                
        String forwardTo = request.getParameter("forwardTo");
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        try {
            StaffDA staffDA = new StaffDA();
            
            HttpSession session = request.getSession();
            Staff staff = (Staff) session.getAttribute("ssLogStaff");
            
            if (staff != null) {
                staff.setStaffName(request.getParameter("name"));
                String email = request.getParameter("email");
                String defaultPW = request.getParameter("checkPW");
                String newPW = request.getParameter("newPW");
                String question = request.getParameter("questions");
                String answer = request.getParameter("answer");
                
                List<Staff> staffList = new ArrayList<Staff>();
                staffList = staffDA.getStaff();
                Staff chkStaff = new Staff();
                for (int i = 0; i < staffList.size(); i++) {
                    chkStaff = staffList.get(i);
                    if (email.equals(staff.getStaffEmail())) {
                        break;
                    } else if (chkStaff.getStaffEmail().equals(email)) {
                        out.println("<script>");
                        out.println("location='StaffProfile.jsp';");
                        out.println("alert('Email already registered.');");
                        out.println("</script>");
                        out.close();
                        response.sendRedirect("StaffProfile.jsp");
                    } 
                }
                
                staff.setStaffEmail(email);
                
                if (newPW.isEmpty()) {
                    staff.setStaffPassword(defaultPW);
                } else {
                    staff.setStaffPassword(newPW);
                }
                if (question != null && question != "") {
                    staff.setSecurityQuestion(request.getParameter("questions"));
                }
                if (answer != null && answer != "") {
                    staff.setAnswer(answer);
                }
                staff.setStaffPhoneNo(request.getParameter("phoneNo"));
                
                staffDA.updateStaff(staff);
            }
            
        } catch(Exception ex) {
            out.println("ERROR: " + ex.toString() + "<br /><br />");
            
            StackTraceElement[] elements = ex.getStackTrace();
            for(StackTraceElement e: elements){
                out.println("File Name: " + e.getFileName() + "<br />");
                out.println("Class Name: " + e.getClassName() + "<br />");
                out.println("Method Name: " + e.getMethodName() + "<br />");
                out.println("Line Number: " + e.getLineNumber() + "<br /><br />");                
            }
            out.close();
        }
        
        response.sendRedirect("StaffProfile.jsp");
    }
}
