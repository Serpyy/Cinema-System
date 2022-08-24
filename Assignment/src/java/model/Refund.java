package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Refund implements Serializable{
    private String refund_no;
    private Payment payment;
    private Staff staff;
    private LocalDate refund_date;
    private char status;
    private String reason;
    private static int nextRefund_No = 1;
    
    //this constructor is used to get record form the database
    public Refund(String refund_no, Payment payment, Staff staff, LocalDate refund_date, char status, String reason) {
        this.refund_no = refund_no;
        this.payment = payment;
        this.staff = staff;
        this.refund_date = refund_date;
        this.status = status;
        this.reason = reason;
    }
        
     //this constructor is used to save record created form the database
    public Refund(Payment payment, Staff staff, String reason){
        this.payment = payment;
        this.staff = staff;
        this.refund_date = LocalDate.now();
        this.status  = 'P';
        this.refund_no= String.format("R%6d", nextRefund_No);
        this.reason = reason;
        nextRefund_No++;
    }    

    public String getRefund_no() {
        return refund_no;
    }

    public Payment getPayment() {
        return payment;
    }

    public Staff getStaff() {
        return staff;
    }
    
    public LocalDate getRefund_date(){
        return refund_date;
    }
    
    public String getStatus() {
        String process = "error";
        
        if(status == 'P')
            process = "Processing";
        else if(status == 'A')
            process = "Approved";            
        else if(status == 'U')
            process = "Unapproved";            
        
        return process;
    }    

    public String getReason() {
        return reason;
    }

    public static int getNextRefund_No() {
        return nextRefund_No;
    }

    public void setRefund_no(String refund_no) {
        this.refund_no = refund_no;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public void setRefund_date(LocalDate refund_date) {
        this.refund_date = refund_date;
    }

    public void setStatus(String ssStatus) {
         if(ssStatus.toUpperCase().equals("PROCESSING"))
            this.status = 'P';
        else if(ssStatus.toUpperCase().equals("APPROVED"))
             this.status = 'A';            
        else if(ssStatus.toUpperCase().equals("UNAPPROVED"))
            this.status = 'U';       
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public static void setNextRefund_No(int nextRefund_No) {
        Refund.nextRefund_No = nextRefund_No;
    }
}
