package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.*;

@WebServlet(name = "PurchaseHistoryServlet", urlPatterns = {"/PurchaseHistoryServlet"})
public class PurchaseHistoryServlet extends HttpServlet {

    //Will be triggered after user has opt to access to the payment history page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
//        //Will be deleted later cuz other ppl will pass to me
//        try{
//        CustomerDA customerDA = new CustomerDA();
//        Customer tempCustomer = customerDA.getRecord("C0012");
//        session.setAttribute("ssLogUser", tempCustomer);
//        }catch(Exception ex){
//            ex.getMessage();
//        }

        
        //To retrieve all the payments info that the customer has made tgt with the respective movie details
        try{
            Customer cust = (Customer) session.getAttribute("ssLogUser"); //to know who is the currently logged in customer
            //Define the DA classes that needded to be used to retrieve the required info 
            CardDA cardDA = new CardDA();
            PaymentDA paymentDA = new PaymentDA();
            RefundDA refundDA = new RefundDA();
            TicketDA ticketDA = new TicketDA();
            ScheduleDA scheduleDA = new ScheduleDA();
            MovieDA movieDA = new MovieDA();
            //Array lists to store the info retrieved from DB
            ArrayList<Card> cards = new ArrayList<Card>();
            ArrayList<Payment> payments = new ArrayList<Payment>();
            ArrayList<String> scheduleNo = new ArrayList<String>();
            ArrayList<Schedule> schedules = new ArrayList<Schedule>();

            cards = cardDA.retrieveCards(cust); //retrieve the card that currently registered to this cust
            if(cards.isEmpty()){ //means the cust has no card and does not make any payment yet (so the record shuld be empty)
                session.setAttribute("ssPurHisRecordExistance", "Invalid"); //To display errMsg to user after redirect to the jsp page
                session.setAttribute("ssPurHisSelectedIndex", "-1");
            }else{ //means user has make payment b4 (gt record inside the table)
                payments = paymentDA.getPaymentByCardNo(cards); //retrieve the paymentRecords based on the cardNo
                
                payments = refundDA.chkPaymentExistence(payments); //filter out the records that has alr been refunded

                scheduleNo = ticketDA.getScheduleNoByPaymentNo(payments);  //retrieve scheduleNo based on paymentRecords

                schedules = scheduleDA.getScheduleByScheduleNo(scheduleNo); //retrieve schedule records based on scheduleNo

                //Save the payment history of this customer into the session (to be used later)
                session.setAttribute("ssPurHisPayments", payments);
                session.setAttribute("ssPurHisSchedules", schedules);
                //Tell the jsp that there is payment records found
                session.setAttribute("ssPurHisRecordExistance", "Valid");
                session.setAttribute("ssPurHisSelectedIndex", "0");
                //declare 2 array list to link with the table cells!
                session.setAttribute("ssPurHisPaymentLink", payments);
                session.setAttribute("ssPurHisScheduleLink", schedules);
            }       
            response.sendRedirect("PurchaseHistory.jsp"); //Redirect user to the payment history page
        }catch(Exception ex){
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
    }

    //Will be triggered after user has chg the option inside the selection list
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        //Retrieve all of the payments and schedule details that we generated just now from the session
        ArrayList<Payment> allPayments = (ArrayList<Payment>)session.getAttribute("ssPurHisPayments");
        ArrayList<Schedule> allSchedules = (ArrayList<Schedule>)session.getAttribute("ssPurHisSchedules");
        //The array list that used to store the payment and schedule details that is going to be displayed inside the table
        ArrayList<Payment> selectedPayments = new ArrayList<Payment>();
        ArrayList<Schedule> selectedSchedules = new ArrayList<Schedule>();
        
        int selectedIndex = Integer.parseInt(request.getParameter("historySelector")); //To know user has selected which option
        YearMonth currentYearMonth = YearMonth.now();
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        
        if(selectedIndex==0){ //Default selection (display all record to user)
            session.setAttribute("ssPurHisPaymentLink", allPayments);
            session.setAttribute("ssPurHisScheduleLink", allSchedules);
        }else if(selectedIndex == 1){ //Upcoming showing date
            //loop through each of the payment records and determine which record is having the showing date after the current date based on the movie schedule date
            for(int i=0; i < allPayments.size(); i++){ 
                if(allSchedules.get(i).getSchedule_date().isEqual(currentDate) ||  allSchedules.get(i).getSchedule_date().isAfter(currentDate)){
                    if(allSchedules.get(i).getSchedule_date().isEqual(currentDate)){
                        if(allSchedules.get(i).getSchedule_time().isAfter(currentTime)){
                            selectedPayments.add(allPayments.get(i));
                            selectedSchedules.add(allSchedules.get(i));
                        }
                    }
                    else{
                        selectedPayments.add(allPayments.get(i));
                        selectedSchedules.add(allSchedules.get(i));
                    }
                }
            }
            //Set the filtered array list into the session (o be displayed in the jsp page) 
            session.setAttribute("ssPurHisPaymentLink", selectedPayments);
            session.setAttribute("ssPurHisScheduleLink", selectedSchedules);
        }else if(selectedIndex == 2){ //Payment that made one month ago
            /*
            Exp: Current date: 25/03/2022
            Range: 01/02/2022 to 25/03/2022
            */
            LocalDate startDate = currentYearMonth.minusMonths(1).atDay(1);
            //Loop through each of the payment record and determine which record is fulfilling the condition
            for(int i=0; i < allPayments.size(); i++){
                if(allPayments.get(i).getPayment_date().isEqual(startDate) || allPayments.get(i).getPayment_date().isAfter(startDate)){
                   selectedPayments.add(allPayments.get(i));
                   selectedSchedules.add(allSchedules.get(i));
                }
            }
            //Set the filtered array list into the session (o be displayed in the jsp page) 
            session.setAttribute("ssPurHisPaymentLink", selectedPayments);
            session.setAttribute("ssPurHisScheduleLink", selectedSchedules);
        }else if(selectedIndex == 3){ //Payment that made 2-3 months ago
            /*
            Exp: Current date: 25/03/2022
            Range: 01/12/2021 to 31/01/2022
            */
            LocalDate startDate = currentYearMonth.minusMonths(3).atDay(1);
            LocalDate beforeEndDate = currentYearMonth.minusMonths(1).atDay(1);
            //Loop through each of the payment record and determine which record is fulfilling the condition
            for(int i=0; i<allPayments.size(); i++){
                if((allPayments.get(i).getPayment_date().isEqual(startDate) || allPayments.get(i).getPayment_date().isAfter(startDate) ) 
                        && allPayments.get(i).getPayment_date().isBefore(beforeEndDate)){
                    selectedPayments.add(allPayments.get(i));
                    selectedSchedules.add(allSchedules.get(i));
                }
            }
            //Set the filtered array list into the session (o be displayed in the jsp page) 
            session.setAttribute("ssPurHisPaymentLink", selectedPayments);
            session.setAttribute("ssPurHisScheduleLink", selectedSchedules);
        }else if(selectedIndex == 4){ //Payment that made 4-6 months ago
            /*
            Exp: Current date: 25/03/2022
            Range: 01/09/2021 to 30/11/2021
            */
            LocalDate startDate = currentYearMonth.minusMonths(6).atDay(1);
            LocalDate beforeEndDate = currentYearMonth.minusMonths(3).atDay(1);
            //Loop through each of the payment record and determine which record is fulfilling the condition
            for(int i=0; i<allPayments.size(); i++){
                if((allPayments.get(i).getPayment_date().isEqual(startDate) || allPayments.get(i).getPayment_date().isAfter(startDate) ) 
                        && allPayments.get(i).getPayment_date().isBefore(beforeEndDate)){
                    selectedPayments.add(allPayments.get(i));
                    selectedSchedules.add(allSchedules.get(i));
                }
            }
            //Set the filtered array list into the session (o be displayed in the jsp page) 
            session.setAttribute("ssPurHisPaymentLink", selectedPayments);
            session.setAttribute("ssPurHisScheduleLink", selectedSchedules);
        }
        session.setAttribute("ssPurHisRecordExistance", "Valid"); //So that the alert msg will not be triggered
        session.setAttribute("ssPurHisSelectedIndex", selectedIndex); //To make the option that user has selected to be selected by default

        response.sendRedirect("PurchaseHistory.jsp"); //Redirect to the jsp page
    }

}
