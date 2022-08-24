package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Customer;
import model.Payment;
import model.VoucherDA;

@WebServlet(name = "ValidateVoucherServlet", urlPatterns = {"/ValidateVoucherServlet"})
public class ValidateVoucherServlet extends HttpServlet {

    //Will be triggered after user has press the proceed btn insdie the transaction summary page 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Customer cust = (Customer) session.getAttribute("ssLogUser");
        String voucherCode = request.getParameter("voucherCode");

        if(!voucherCode.isEmpty()){ //if user has entered voucher code
            int disAmount = 0;
            try{
                //Validate the voucher code that user has entered and if it is valid, the method inside the DA will return us the discount amount
                VoucherDA voucherDA = new VoucherDA();
                disAmount = voucherDA.voucherValidation(voucherCode, cust.getCust_ID());
            }catch(SQLException ex){
                PrintWriter out = response.getWriter();
                out.println("ERROR: " + ex.toString() + "</br> </br>"); //normal error message (not so detail)
                
                 //To print very detail error message (able to trace the entire path that causing the error at the particular file, which line and etc)
                StackTraceElement[] elements = ex.getStackTrace();

                for(StackTraceElement e: elements){
                    out.println("File Name: " + e.getFileName() + "</br>");
                    out.println("Class Name: " + e.getClassName()+ "</br>");
                    out.println("Method Name: " + e.getMethodName()+ "</br>");
                    out.println("Line Number: " + e.getLineNumber()+ "</br> </br>");
                }
            }

            if(disAmount == 0){ //to redirect back to the summary page and prompt the alert box to let user to enter voucher code again (bcs invalid voucher)
                session.setAttribute("ssTicVoucherValidation", "Invalid");
                response.sendRedirect("TransactionSummary.jsp");
            }else{ //If the voucher code entered is valid (update the transaction amount stored inside the payment object and delete the respective voucher record from db)
                Payment payment = (Payment) session.getAttribute("ssTicPayment");
                double newestAmt = payment.getAmount() - disAmount;
                payment.setAmount(newestAmt);
                
                try{ //delete the redeemed voucher from db
                    VoucherDA voucherDA = new VoucherDA();
                    voucherDA.deleteVoucher(voucherCode);
                }catch(SQLException ex){
                    PrintWriter out = response.getWriter();
                    out.println("ERROR: " + ex.toString() + "</br> </br>"); //normal error message (not so detail)

                    //To print very detail error message (able to trace the entire path that causing the error at the particular file, which line and etc)
                    StackTraceElement[] elements = ex.getStackTrace();

                    for(StackTraceElement e: elements){
                        out.println("File Name: " + e.getFileName() + "</br>");
                        out.println("Class Name: " + e.getClassName()+ "</br>");
                        out.println("Method Name: " + e.getMethodName()+ "</br>");
                        out.println("Line Number: " + e.getLineNumber()+ "</br> </br>");
                    }
                }
                session.setAttribute("paymentErrMsg", "Valid"); //set this to "valid" so that the alert box of Payment page will not be triggered.
                response.sendRedirect("PaymentServlet"); //Redirect to payment Servlet
            }
        }else{
            session.setAttribute("paymentErrMsg", "Valid"); //set this to "valid" so that the alert box of Payment page will not be triggered.
            response.sendRedirect("PaymentServlet"); //Redirect to payment Servlet
        }
        
    }

}
