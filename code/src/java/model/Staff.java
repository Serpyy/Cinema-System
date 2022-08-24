package model;

import java.io.Serializable;

public class Staff implements Serializable {
    private String staff_ID;
    private String staff_name;
    private String staff_email;
    private String staff_password;
    private String role;
    private String staff_phone_no;
    private String security_question;
    private String answer;
    
    public Staff() {
        
    }
    
    public Staff(String staff_ID,String staff_name) {
        this.staff_ID = staff_ID;
        this.staff_name = staff_name;
    } 


    public Staff(String staff_ID, String staff_name, String role, String staff_email, String staff_phone_no) {
        this.staff_ID = staff_ID;
        this.staff_name = staff_name;
        this.role = role;
        this.staff_email = staff_email;
        this.staff_phone_no = staff_phone_no;
    }
    
    public Staff(String staff_ID, String staff_name, String staff_email, String staff_password, String role, String staff_phone_no, String security_question, String answer) {
        this.staff_ID = staff_ID;
        this.staff_name = staff_name;
        this.staff_email = staff_email;
        this.staff_password = staff_password;
        this.role = role;
        this.staff_phone_no = staff_phone_no;
        this.security_question = security_question;
        this.answer = answer;
    } 
    
    public String getStaffId() {
        return staff_ID;
    }
    public void setStaffId(String staff_ID) {
        this.staff_ID = staff_ID;
    }
    
    public String getStaffName() {
        return staff_name;
    }
    public void setStaffName(String staff_name) {
        this.staff_name = staff_name;
    }
    
    public String getStaffEmail() {
        return staff_email;
    }
    public void setStaffEmail(String staff_email) {
        this.staff_email = staff_email;
    }
    
    public String getStaffPassword() {
        return staff_password;
    }
    public void setStaffPassword(String staff_password) {
        this.staff_password = staff_password;
    }
    
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getStaffPhoneNo() {
        return staff_phone_no;
    }
    public void setStaffPhoneNo(String staff_phone_no) {
        this.staff_phone_no = staff_phone_no;
    }
    
    public String getSecurityQuestion() {
        return security_question;
    }
    public void setSecurityQuestion(String security_question) {
        this.security_question = security_question;
    }
    
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
