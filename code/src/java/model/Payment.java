package model;

import java.io.Serializable;
import java.time.*;

public class Payment implements Serializable{
    private String payment_no;
    private Card card;
    private double amount;
    private LocalTime payment_time;
    private LocalDate payment_date;
    private int child_qty;
    private int adult_qty;
    private static int next_payment_code;
    
    public Payment() {
    }
    
    public Payment(LocalDate payment_date,String payment_no) {
        this.payment_date = payment_date;
        this.payment_no = payment_no;
        
    }   
    
    public Payment(String payment_no, double amount) {
        this.payment_no = payment_no;
        this.amount = amount;
    }   

    public Payment(double amount, int child_qty, int adult_qty){
        this.amount = amount;
        this.child_qty = child_qty;
        this.adult_qty = adult_qty;
    }


    public Payment(Card card, double amount, LocalTime payment_time, LocalDate payment_date, int child_qty, int adult_qty) {
        this.payment_no = String.format("P%05d", next_payment_code);
        this.card = card;
        this.amount = amount;
        this.payment_time = payment_time;
        this.payment_date = payment_date;
        this.child_qty = child_qty;
        this.adult_qty = adult_qty;
        next_payment_code++;
    }
    
    public Payment(String payment_no, Card card, double amount, LocalTime payment_time, LocalDate payment_date, int child_qty, int adult_qty) {
        this.payment_no = payment_no;
        this.card = card;
        this.amount = amount;
        this.payment_time = payment_time;
        this.payment_date = payment_date;
        this.child_qty = child_qty;
        this.adult_qty = adult_qty;
        next_payment_code++;
    }
    
    
    
    //Getter
    public String getPayment_no() {
        return payment_no;
    }

    public Card getCard() {
        return card;
    }

    public double getAmount() {
        return amount;
    }

    public LocalTime getPayment_time() {
        return payment_time;
    }

    public LocalDate getPayment_date() {
        return payment_date;
    }

    public int getChild_qty() {
        return child_qty;
    }

    public int getAdult_qty() {
        return adult_qty;
    }

    public static int getNext_payment_code() {
        return next_payment_code;
    }
    
    //Setter
    public void setPayment_no() { //no parameter passed in since paymentNo is auto generated
        this.payment_no = String.format("P%05d", next_payment_code);
        next_payment_code++;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPayment_time(LocalTime payment_time) {
        this.payment_time = payment_time;
    }

    public void setPayment_date(LocalDate payment_date) {
        this.payment_date = payment_date;
    }

    public void setChild_qty(int child_qty) {
        this.child_qty = child_qty;
    }

    public void setAdult_qty(int adult_qty) {
        this.adult_qty = adult_qty;
    }

    public static void initialiseNext_payment_code(int next_payment_code) {
        Payment.next_payment_code = next_payment_code;
    }
    
}
