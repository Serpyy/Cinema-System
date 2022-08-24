package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Schedule implements Serializable{
    private String schedule_no;
    private Movie movie;
    private Hall hall;
    private LocalTime schedule_time;
    private LocalDate schedule_date;

    public Schedule(){

    }
    
    public Schedule(String schedule_no){
        this.schedule_no = schedule_no;
    }
    
    public Schedule(String schedule_no,Movie movie,Hall hall,LocalTime schedule_time,LocalDate schedule_date){
        this.schedule_no=schedule_no;
        this.movie=movie;
        this.hall=hall;
        this.schedule_time = schedule_time;
        this.schedule_date = schedule_date; 
    }

    public Schedule(String schedule_no,Movie movie,  LocalTime schedule_time,LocalDate schedule_date){
        this.schedule_no = schedule_no;
        this.movie = movie;
        this.schedule_time = schedule_time;
        this.schedule_date = schedule_date;
    }
    
   
    
    public String getSchedule_no(){
        return schedule_no;
    }
    
    public void setSchedule_no(String schedule_no){
        this.schedule_no = schedule_no;
    }
    
    public Movie getMovie(){
        return movie;
    }
    
    public void setMovie(Movie movie){
        this.movie = movie;
    }
    
    public Hall getHall(){
        return hall;
    }
    
    public void setHall(Hall hall){
        this.hall = hall;
    }
    
    public LocalTime getSchedule_time(){
        return schedule_time;
    }
    
    public void setSchedule_time(LocalTime schedule_time){
        this.schedule_time = schedule_time;
    }
    
    public LocalDate getSchedule_date(){
        return schedule_date;
    }
    
    public void setSchedule_date(LocalDate schedule_date){
        this.schedule_date = schedule_date;
    }
    
}
