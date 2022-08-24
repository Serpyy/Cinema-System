package model;

import java.io.Serializable;

public class Ticket implements Serializable{
    private String ticket_no;
    private Payment payment;
    private Schedule schedule;
    private double price;
    private String category;
    private String seat_no;

    public Ticket() {
    }

    public Ticket(String ticket_no,Schedule schedule){
        this.ticket_no = ticket_no;
        this.schedule = schedule;
    }

    public Ticket(String ticket_no, String seat_no, Schedule schedule) {
        this.ticket_no = ticket_no;
        this.seat_no = seat_no;
        this.schedule = schedule;
    }   


    public Ticket(Payment payment, Schedule schedule, double price, String category, String seat_no) {
        this.ticket_no = String.format("%s%s", seat_no, schedule.getSchedule_no());
        this.payment = payment;
        this.schedule = schedule;
        this.price = price;
        this.category = category;
        this.seat_no = seat_no;
    }
    
    public Ticket(Schedule schedule, double price, String category, String seat_no){
        this.ticket_no = String.format("%s%s", seat_no, schedule.getSchedule_no());
        this.schedule = schedule;
        this.price = price;
        this.category = category;
        this.seat_no = seat_no;
    }

    public Ticket(String ticket_no, Payment payment, Schedule schedule, double price, String category, String seat_no) {
        this.ticket_no = String.format("%s%s", seat_no, schedule.getSchedule_no());
        this.payment = payment;
        this.schedule = schedule;
        this.price = price;
        this.category = category;
        this.seat_no = seat_no;
    }
    
    //Getter
    public String getTicket_no() {
        return ticket_no;
    }

    public Payment getPayment() {
        return payment;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getSeat_no() {
        return seat_no;
    }

    //Setter
    public void setTicket_no(String ticket_no) {
        this.ticket_no = ticket_no;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSeat_no(String seat_no) {
        this.seat_no = seat_no;
    }
        
}
