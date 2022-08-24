package model;

import java.io.Serializable;
import java.util.Objects;

public class Review implements Serializable {
    private String review_no;
    private String comment;
    private int rating;
    private Customer customer;
    private String response;
    public static int nextReviewNo = 1;
    
    public Review() {
        
    }
    
    public Review(int rating, Customer customer) {
        this.rating = rating;
        this.customer = customer;
    }
    
    public Review(String review_no, String comment, int rating, Customer customer) {
        this.comment = comment;
        this.rating = rating;
        this.customer = customer;
        this.nextReviewNo = Integer.parseInt(review_no.substring(2,5)) + 1;
        this.review_no = String.format("RV%0" + review_no.substring(2,5).length() + "d", this.nextReviewNo);
    }
    
    public Review(String review_no, String comment, int rating, Customer customer, String response) {
        this.review_no = review_no;
        this.comment = comment;
        this.rating = rating;
        this.customer = customer;
        this.response = response;
    }
   
    public String getReviewNo() {
        return review_no;
    }
    public void setReviewNo(String review_no) {
        this.review_no = review_no;
    }
    
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    
    public Customer getCustomer(){
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
   
    public String getResponse() {
        return response;
    }
    public void setResponse(String response) {
        this.response = response;
    }
}