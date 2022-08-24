package model;

import java.io.Serializable;

public class Customer implements Serializable {
    private String cust_ID;
    private String cust_name;
    private String ic_no;
    private String email;
    private String cust_password;
    private String phone_no;
    private String security_question;
    private String answer;
    private int point;
    private static int nextCustID=1;
    

    public Customer() {
        }

    public Customer(String cust_ID) {
        this.cust_ID = cust_ID;
    }

    public Customer(String cust_name, String ic_no, String email, String cust_password, String phone_no, String security_question, String answer,int point) {
        this.cust_ID = String.format("C%04d",nextCustID);
        this.cust_name = cust_name;
        this.ic_no = ic_no;
        this.email = email;
        this.cust_password = cust_password;
        this.phone_no = phone_no;
        this.security_question = security_question;
        this.answer = answer;
        this.point = point;
        ++nextCustID;  
    }
   
    public Customer(String cust_ID, String cust_name, String ic_no, String email, String cust_password, String phone_no, String security_question,String answer, int point) {
        this.cust_ID = cust_ID;
        this.cust_name = cust_name;
        this.ic_no = ic_no;
        this.email = email;
        this.cust_password = cust_password;
        this.phone_no = phone_no;
        this.security_question = security_question;
        this.answer = answer;
        this.point = point;
   }

    public String getCust_ID() {
        return cust_ID;
    }

    public void setCust_ID(String cust_ID) {
        this.cust_ID = cust_ID;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getIc_no() {
        return ic_no;
    }

    public void setIc_no(String ic_no) {
        this.ic_no = ic_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCust_password() {
        return cust_password;
    }

    public void setCust_password(String cust_password) {
        this.cust_password = cust_password;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getSecurity_question() {
        return security_question;
    }

    public void setSecurity_question(String security_question) {
        this.security_question = security_question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public static int getNextCustID() {
        return nextCustID;
    }

    public static void setNextCustID(int nextCustID) {
        Customer.nextCustID = nextCustID;
    }

    public static boolean chkPassword(String password){
        if(password.length()<=7)
        {       
            return false;
        }
        else if(password.length()>=17){
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public static boolean chkEmail(String email){
        if(email.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")){
            return true;
        }
        else {
        	System.out.println("Invalid email! Please enter again!\n");
            return false;
        }
    }

}
