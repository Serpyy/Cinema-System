package controller;

import model.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Voucher", urlPatterns = {"/Voucher"})
public class VoucherController extends HttpServlet {
    
    private VoucherDA voucherDA;
    private CustomerDA customerDA;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.print("<H1>Hello </H1>");
        
        try{
                voucherDA = new VoucherDA();
                customerDA = new CustomerDA();
        
                HttpSession session = request.getSession();
                Customer cust = (Customer)session.getAttribute("ssLogUser");
                String user_ID = cust.getCust_ID();
                            
                //String user_ID = request.getParameter("txtID");
                //Customer cust =  customerDA.getRecord(user_ID);
                
                ArrayList<Voucher> VoucherList= voucherDA.getRecord(cust);
                
                session.setAttribute("ssCustomer", cust);
                session.setAttribute("ssVoucherList", VoucherList);
                response.sendRedirect("VoucherList.jsp");
                 
        }catch(Exception ex){
            out.print("ERROR " + ex.toString() + "<br /><br />");
            
            StackTraceElement[] elements = ex.getStackTrace();
            for(StackTraceElement e: elements){
                out.println("File Name: " + e.getFileName() + "<br />");
                out.println("Class Name: " + e.getClassName() + "<br />");
                out.println("Method Name: " + e.getMethodName() + "<br />");
                out.println("Line Number: " + e.getLineNumber()+ "<br /><br />");
                out.println("To String: " + e.toString()+ "<br /><br />");
             }   
        }
        
        out.close();
    }
    
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        try{
             voucherDA = new VoucherDA();
            customerDA = new CustomerDA();
            
            HttpSession session = request.getSession();
            int discount = Integer.parseInt(request.getParameter("txtdis"));
            Customer cust = (Customer)session.getAttribute("ssCustomer");
          
            Voucher voucher = new Voucher(cust,discount);
            Voucher.setNextVoucher_no(voucherDA.getNextNumber());
            voucher.setVoucher_no(String.format("V%06d", voucherDA.getNextNumber()));
            voucherDA.addRecord(voucher);
            
            switch (discount) {
                case 5: cust.setPoint(cust.getPoint() - 50);break;
                case 10: cust.setPoint(cust.getPoint() - 100);break;
                case 15: cust.setPoint(cust.getPoint() - 150);break;
                default:
                    throw new AssertionError();
            }
            
            //Needed Update The point in cust
            customerDA.updatePointRecord(cust);
            
            out.print(Voucher.getNextVoucher_no() + "<br>");
            out.print(voucher.getVoucher_no()  + "<br>" );
            out.print(voucher.getCustomer().getCust_ID()  + "<br>");
            out.print(voucher.getDis_amount()  + "<br>");
            
            out.print(cust.getPoint()  + "<br>");
             
            session.setAttribute("ssCustomer", cust);
            session.setAttribute("sssAddedVouncher", voucher);

             response.sendRedirect("VoucherReceived.jsp");
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