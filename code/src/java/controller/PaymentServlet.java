package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.YearMonth;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Card;
import model.CardDA;
import model.Customer;

@WebServlet(name = "PaymentServlet", urlPatterns = {"/PaymentServlet"})
public class PaymentServlet extends HttpServlet {

    //Will be trigger after user has press the proceed btn inside the transaction summary page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer cust = (Customer) session.getAttribute("ssLogUser");
        ArrayList<Card> cards = new ArrayList<Card>(); //To store the cards info that is being registered to this user
        
        try{
            //Retrieve the cards info that is being registered to this user
            CardDA cardDA = new CardDA();
            cards = cardDA.retrieveCards(cust); 

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
        
        session.setAttribute("ssTicCards", cards); //save the cards info retrieved from DB into the session
        session.setAttribute("ssTicTempCard", new Card()); //Assign a blank Card object to bind to the text field in Payment.jsp (by default, user has not select anything from the list)
        
        response.sendRedirect("Payment.jsp"); //Redirect to the payment page
    }

    //Will be triggered if user opt for different option inside the selection list or when user has press the proceed btn
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<Card> cards = (ArrayList<Card>) session.getAttribute("ssTicCards");
        
        if(request.getParameter("submitBtn") ==null){ //if the form is not triggered by the submit btn (triggered by javascript which means when user chg the selection list's selection)
            int selectedCardIndex = Integer.parseInt(request.getParameter("cardSelection")); //to know which option is being selected
            Card selectedCardDetails = (Card) session.getAttribute("ssTicTempCard"); //To store the card details that user has selected
            
            selectedCardDetails = cards.get(selectedCardIndex);
            session.setAttribute("ssTicTempCard", selectedCardDetails); //Store the card details into the session (to be displayed in the payment page)
            session.setAttribute("paymentErrMsg", "Valid"); //So that the error message's alert box will not be triggered
            
            response.sendRedirect("Payment.jsp"); //Redirect to the payment page
        }else{  //User pressed the submit button
            String errMsg = "";
            if(request.getParameter("cardSelection").equals("register")){ //if user choose to register a new card
                //Take the content that user input from the text box
                String name = request.getParameter("name");
                String cardNo = request.getParameter("cardNo");
                String expDate = request.getParameter("expDate");
                String cvv = request.getParameter("cvv");
                
                //Call some methods to check for the entered details
                boolean valid = true; 
                if(!validateName(name)){
                    valid = false;
                }else if(!validateCardNo(cardNo)){
                    valid = false;
                }else if(!validateCvv(cvv)){
                    valid = false;
                }else if(!validateExpiryDate(expDate)){
                    valid = false;
                }

                if(valid){ //means all of the format is valid and now want to check for card existance
                    boolean exist = false;
                    try{
                        CardDA cardDA = new CardDA();
                        exist = cardDA.chkCardExistance(cardNo);
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
                    
                    if(exist){
                        valid = false;
                        errMsg = "This card has been registered. Please RETRY with another card!";
                        session.setAttribute("paymentErrMsg", errMsg); //Assign an errMsg to this session object so that the message will be displayed inside the alert box after redirect to the payment page
                        session.setAttribute("ssTicTempCard", new Card()); //Assign a blank Card object to bind to the text field in jsp
                        response.sendRedirect("Payment.jsp"); //Redirect to the payment page 
                    }else{ //means all of the details is accurate (Store the new card details into database and proceed to next page)
                        Customer cust = (Customer) session.getAttribute("ssLogUser");
                        
                        //Convert the expDate from String to YearMonth
                        //extract the month and year from the string tempDate
                        String monthS = expDate.substring(0, 2);
                        String yearS = expDate.substring(3, 7);
                        //convert the extracted string to int data type
                        int month = Integer.parseInt(monthS);
                        int year = Integer.parseInt(yearS);
                        //save the month and year that user input to the YearMonth object
                        YearMonth expiryDate = YearMonth.of(year, month);
                        
                        Card card = new Card(cardNo, cust, cvv, name, expiryDate); //Create a card object to store the new card's details 
                        try{
                            //To add the new card into the db
                            CardDA cardDA = new CardDA();
                            cardDA.addCard(card);
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
                        
                        session.setAttribute("ssTicTempCard", card); //to store the card details in session so that next servlet can find it
                        //Redirect to next servlet to save the ticket and payment details into the database
                        response.sendRedirect("StorePaymentTicketDetailsServlet");
                    }
                }else{ //means the input format entered is invalid 
                    errMsg = "Invalid details entered. Please REENTER!";
                    session.setAttribute("paymentErrMsg", errMsg); //Assign an errMsg to this session object so that the message will be displayed inside the alert box after redirect to the payment page
                    session.setAttribute("ssTicTempCard", new Card()); //Assign a blank Card object to bind to the text field in jsp
                    response.sendRedirect("Payment.jsp"); //Redirect to the payment page 
                }
            }else{ //if user want to pay with current card and now we want to validate the CVV entered
                int selectedCardIndex = Integer.parseInt(request.getParameter("cardSelection"));
                String cvvEntered = request.getParameter("cvv"); //get the cvv that user has entered
                Card selectedCardDetails = cards.get(selectedCardIndex);
                if(selectedCardDetails.getCvv().equals(cvvEntered)){ //If user entered the valid CVV
                    session.setAttribute("ssTicTempCard", selectedCardDetails); //to store the card details in session so that next servlet can find it
                    //Redirect to next servlet to save the ticket and payment details into the database
                    response.sendRedirect("StorePaymentTicketDetailsServlet");
                }else{ //means the CVV entered is invalid
                    errMsg = "Invalid CVV number. Please REENTER!";
                    session.setAttribute("paymentErrMsg", errMsg); //Assign an errMsg to this session object so that the message will be displayed inside the alert box after redirect to the payment page
                    response.sendRedirect("Payment.jsp"); //Redirect to the payment page 
                }
            }
        }
    }
    
     //Some of the validation method used to validate the details that user entered when registering a new card
    //validate name
    public static boolean validateName(String name){
        if(name.charAt(0)==' ') { //checking for null input
            return false;
        }
        //checking the name format (only allow letter, space or '/')
        for(int i =0; i< name.length(); i++) {
            if(!Character.isLetter(name.charAt(i)) && name.charAt(i)!=' ' && name.charAt(i)!='/') {
                return false;
            }
        }
        return true;
    }
    
    //validate card number 
    public static boolean validateCardNo(String cardNo) {
        if(cardNo.length()!=16) { //card number must exact 16 digits
                return false;
        }

        for(int i=0; i<cardNo.length(); i++) { //check whether the accNo is only contains of digits
                if(!Character.isDigit(cardNo.charAt(i))) {
                        return false;
                }
        }

        //check the format of the credit card number (code extracted from OOP's Practical 1 Q3)
        long sum1 = 0;
        long temp1 = Long.parseLong(cardNo); //convert the cardNo to integer data type and store it to temp1

        for (int i = 1; i < 16; i += 2){
           sum1 += temp1 % 10; //add up the last digit 
           temp1 /= 100;   // move left two digits, base ten
        }

        long sum2 = 0;
        long temp2 = Long.parseLong(cardNo) / 10;  //move left 1 digit

        for (int i = 1; i < 16; i += 2) {
           long digit = (temp2 % 10) * 2; //take the last digit then times 2    
           sum2 += digit % 10;   //add the last digit of ans
           digit /= 10;	//move the ans to the left of 1 digit 		
           sum2 += digit % 10;   //add the first digit of the ans 

           temp2 /= 100;  // move left two digits, base ten
        }

        long finalSum = sum1 + sum2;

        if (finalSum % 10 != 0) {//means if the last digit of the final sum not equals to zero (invalid credit card num)
           return false;
        }

        return true; //if all of the input formats are correct
    }

    //validate cvv number
    public static boolean validateCvv(String cvv) {
        if(cvv.length()!=3) { //cvv must exact 3 digits
            return false;
        }

        for(int i=0; i<cvv.length(); i++) { //check whether the CVV is only contains of digits
            if(!Character.isDigit(cvv.charAt(i))) {
                    return false;
            }
        }

        return true;
    }

    //validate card expiry date
    public static boolean validateExpiryDate(String expDate) {
        if(expDate.length()!=7) { //the expDate's string must exact 7 digits
            return false;
        }

        //validate the user input format (ensure user has input all int and a '/' at 3rd character)
        for(int i=0; i<expDate.length(); i++) {
            if(i==2) {
                    if(expDate.charAt(i) != '/') {
                            return false;
                    } 	
            }
            else {
                    if(!Character.isDigit(expDate.charAt(i))) {
                            return false;
                    }
            } 
        }

        //extract the month and year from the string
        String monthS = expDate.substring(0, 2);
        String yearS = expDate.substring(3, 7);
        //convert the extracted string to int data type
        int month = Integer.parseInt(monthS);
        int year = Integer.parseInt(yearS);
        
        if(month<1 || month >12 || year <2022 || year > 2040){ //chk for valid month and year value
            return false;
        }
        //store the extracted day and month to YearMonth object (to validate the range)
        YearMonth temp = YearMonth.of(year, month);

        //validate the range of month and year
        if(temp.isBefore(YearMonth.now())) {
                return false;
        }

        return true;
    }

}
