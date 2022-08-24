package model;

import java.io.Serializable;

public class Voucher implements Serializable{
    private String voucher_no;
    private Customer customer;
    private int dis_amount;
    private static int nextVoucher_no; 
    
    public Voucher(){
    }
    
    //this constructor is used to save the record from database
    public Voucher(String voucher_no, Customer  customer, int dis_amount){
        this.voucher_no = voucher_no;
        this.customer = customer;
        this.dis_amount = dis_amount;
    }
       
    //this constructor is used to save the record created form the website
    public Voucher(Customer customer, int dis_amount ){
        this.customer = customer;
        this.dis_amount = dis_amount;
        this.voucher_no = String.format("V%06d", nextVoucher_no);
        nextVoucher_no++;
    }

    public String getVoucher_no() {
        return voucher_no;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getDis_amount() {
        return dis_amount;
    }

    public static int getNextVoucher_no() {
        return nextVoucher_no;
    }

    public void setVoucher_no(String voucher_no) {
        this.voucher_no = voucher_no;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setDis_amount(int dis_amount) {
        this.dis_amount = dis_amount;
    }

    public static void setNextVoucher_no(int nextVoucher_No) {
        Voucher.nextVoucher_no = nextVoucher_no;
    } 
    
}
