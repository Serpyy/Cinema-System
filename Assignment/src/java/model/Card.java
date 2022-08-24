package model;

import java.time.YearMonth;
import java.io.Serializable;

public class Card implements Serializable{
    private String card_no;
    private Customer customer;
    private String cvv;
    private String cardholder_name;
    private YearMonth expiry_date;
    
    public Card() {
        card_no = "";
        cvv="";
        cardholder_name="";
    }

    public Card(String card_no) {
        this.card_no = card_no;
    }

    public Card(String card_no,String cardholder_name) {
        this.card_no = card_no;
        this.cardholder_name = cardholder_name;
    }   


    public Card(String card_no, Customer customer, String cvv, String cardholder_name, YearMonth expiry_date) {
        this.card_no = card_no;
        this.customer = customer;
        this.cvv = cvv;
        this.cardholder_name = cardholder_name;
        this.expiry_date = expiry_date;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getCardholder_name() {
        return cardholder_name;
    }

    public void setCardholder_name(String cardholder_name) {
        this.cardholder_name = cardholder_name;
    }

    public YearMonth getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(YearMonth expiry_date) {
        this.expiry_date = expiry_date;
    }
    
    public String getExpiry_dateInStr(){
        if(expiry_date!=null){
            return String.format("%02d/%04d", expiry_date.getMonthValue(), expiry_date.getYear());
        }else{
            return "";
        }
       
    }
}
