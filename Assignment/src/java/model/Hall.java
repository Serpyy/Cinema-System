package model;

import java.io.Serializable;

public class Hall implements Serializable {
    private String hall_no;
    private int row;
    private int column;

    public Hall(){

    }
    
    public Hall(String hall_no){
        this.hall_no = hall_no;
    }

    public Hall(String hall_no, int row, int column){
        this.hall_no = hall_no;
        this.row = row;
        this.column = column;
    }

    public String getHall_no(){
        return hall_no;
    }

    public void setHall_no(String hall_no){
        this.hall_no = hall_no;
    }

    public int getRow(){
        return row;
    }

    public void setRow(int row){
        this.row = row;
    }

    public int getColumn(){
        return column;
    }

    public void setColumn(int column){
        this.column = column;
    }
        
}
