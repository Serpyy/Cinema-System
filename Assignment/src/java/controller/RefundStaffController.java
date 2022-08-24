package controller;

import model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "RefundStaffController", urlPatterns = {"/RefundStaffController"})
public class RefundStaffController extends HttpServlet {
     
    private CustomerDA customerDA;
    private RefundDA refundDA;
    private TicketDA ticketDA;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try{
            refundDA = new RefundDA();
            customerDA = new CustomerDA();
            ticketDA = new TicketDA();

            HttpSession session = request.getSession();
            Staff staff = (Staff)session.getAttribute("ssLogStaff");
            String user_ID = staff.getStaffId();
            //String user_ID = request.getParameter("txtID");

            ArrayList<Refund> refundList = refundDA.getRecord();
            ArrayList<Refund> sortedRefundList =  new ArrayList<Refund>();


                for(Refund refund: refundList){
                    if(refund.getStaff().getStaffId().equals(user_ID)){
                        sortedRefundList.add(refund);
                    }
                }

                ArrayList<Refund> assignedList =  new ArrayList<Refund>();
                ArrayList<Refund> completeList =  new ArrayList<Refund>();

                  for (Refund refund : sortedRefundList) {
                      if(refund.getStatus().charAt(0) == 'P'){
                          assignedList.add(refund);
                      }else{
                           completeList.add(refund);
                      }
                }

                session.setAttribute("ssUser_ID", user_ID);
                session.setAttribute("ssAssignedListList", assignedList);
                session.setAttribute("sscompleteList", completeList);
                response.sendRedirect("RefundStaff.jsp");
               
        }catch(Exception ex){
                out.print("ERROR " + ex.toString() + "<br /><br />");

                StackTraceElement[] elements = ex.getStackTrace();
                for(StackTraceElement e: elements){
                    out.println("File Name: " + e.getFileName() + "<br />");
                    out.println("Class Name: " + e.getClassName() + "<br />");
                    out.println("Method Name: " + e.getMethodName() + "<br />");
                    out.println("Line Number: " + e.getLineNumber()+ "<br /><br />");
                }
            }
        
        out.close();
    }
    
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            HttpSession session = request.getSession();
        
        try{
                refundDA = new RefundDA();
                customerDA = new CustomerDA();
                ticketDA = new TicketDA();
            
             String action = request.getParameter("action");
        String refund_no = request.getParameter("txtRefund");
        Refund updatedThisRefund = null;
        
        ArrayList<Refund> refundList = (ArrayList<Refund>) refundDA.getRecord();
        
        for(Refund refund : refundList){
            if(refund.getRefund_no().equals(refund_no)){
                updatedThisRefund = refund;
            }
        }
        
        if("Approve".equals(action)){
            System.out.println("Approve the request");
            updatedThisRefund.setStatus("APPROVED");
            refundDA.updateRecord(updatedThisRefund);
        }else{
            System.out.println("Unapprove the request");
             updatedThisRefund.setStatus("UNAPPROVED");
            refundDA.updateRecord(updatedThisRefund);
        }
        
        session.setAttribute("ssUpdatedRefund", updatedThisRefund);
        response.sendRedirect("RefundUpdatedRecord.jsp");
        
        }catch(Exception ex){
                out.print("ERROR " + ex.toString() + "<br /><br />");

                StackTraceElement[] elements = ex.getStackTrace();
                for(StackTraceElement e: elements){
                    out.println("File Name: " + e.getFileName() + "<br />");
                    out.println("Class Name: " + e.getClassName() + "<br />");
                    out.println("Method Name: " + e.getMethodName() + "<br />");
                    out.println("Line Number: " + e.getLineNumber()+ "<br /><br />");
                }
            }
        
        out.close();
    }
}

