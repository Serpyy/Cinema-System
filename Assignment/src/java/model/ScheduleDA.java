package model;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ScheduleDA {

    private String host = "jdbc:derby://localhost:1527/assignmentdb";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "Schedule";
    private Connection conn;
    private PreparedStatement stmt;
    private Statement statement;
    private MovieDA movieDA;
    private HallDA hallDA; 

    public ScheduleDA() throws SQLException{
         createConnection();
        movieDA = new MovieDA();
        hallDA = new HallDA();
    }
    
//get records of refund of the selected customer 
    public Schedule getRecord(String schedule_no) throws SQLException{
        Schedule schedule = null; 
        String queryStr = "SELECT * FROM " +tableName + " WHERE schedule_no = ?";
        
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, schedule_no);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                Movie movie = movieDA.getCurrentMovie(rs.getString("movie_Id"));
                Hall hall = hallDA.retrieveHall(rs.getString("hall_no"));
                schedule = new Schedule(schedule_no, movie, hall, rs.getTime("schedule_time").toLocalTime(), rs.getDate("schedule_date").toLocalDate());
            }           
        
        return schedule;
    }


    //get all schedule
    public ArrayList<Schedule> getSchedule() throws SQLException{ 
        ArrayList<Schedule> scheduleList = new ArrayList<Schedule>();
        String sqlQuery = "SELECT * from " + tableName;
        Schedule schedule = null;
        Movie movie = null;
        Hall hall=null;
            stmt = conn.prepareStatement(sqlQuery);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                movie = new Movie(rs.getString(2));
                hall= new Hall(rs.getString(3));
                schedule = new Schedule(rs.getString(1),movie,hall,rs.getTime(4).toLocalTime(),rs.getDate(5).toLocalDate());
                scheduleList.add(schedule);
            }
        return scheduleList;
    }   
    
    //get last schedule ID
    public String getLastSchedule() throws SQLException{
        String scheduleID=null;
        String sqlQuery = "SELECT MAX(schedule_no) FROM " + tableName;    
            stmt = conn.prepareStatement(sqlQuery);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
               scheduleID=rs.getString(1);
            }
        
        return scheduleID;
    }
    
    //add schedule
    public void addSchedule(Schedule schedule) throws SQLException{
        String sqlStr="INSERT INTO " + tableName + " VALUES(?,?,?,?,?)";
        Date scheduleDate = Date.valueOf(schedule.getSchedule_date());
        Time scheduleTime = Time.valueOf(schedule.getSchedule_time());
            stmt=conn.prepareStatement(sqlStr);   
            stmt.setString(1,schedule.getSchedule_no());
            stmt.setString(2,schedule.getMovie().getMovieID());
            stmt.setString(3,schedule.getHall().getHall_no());
            stmt.setTime(4,scheduleTime);
            stmt.setDate(5,scheduleDate);
            stmt.executeUpdate();
    }   
     
    //update schedule
    public void editSchedule(Schedule schedule) throws SQLException{ 
        String sqlQuery = "UPDATE " + tableName + " SET movie_id = ?, hall_no = ?, schedule_time = ?, schedule_date = ? WHERE schedule_no = ?";
        Date scheduleDate = Date.valueOf(schedule.getSchedule_date());
        Time scheduleTime = Time.valueOf(schedule.getSchedule_time());
            stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1,schedule.getMovie().getMovieID());
            stmt.setString(2,schedule.getHall().getHall_no());
            stmt.setTime(3,scheduleTime);
            stmt.setDate(4,scheduleDate);
            stmt.setString(5,schedule.getSchedule_no());
            stmt.executeUpdate();     
    }

    public Schedule retrieveSchedule(Movie selectedMovie, LocalDate selectedDate, LocalTime selectedTime) throws SQLException{
        String queryStr = "SELECT * FROM " + tableName + " WHERE MOVIE_ID = ? AND schedule_date = ? AND schedule_time = ?";
        Schedule selectedSchedule = null;
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, selectedMovie.getMovieID());
            stmt.setDate(2, Date.valueOf(selectedDate));
            stmt.setTime(3, Time.valueOf(selectedTime));

            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                String hallNo = rs.getString(3);
                //Retrieve the complete hall object based on the given ID
                Hall selectedHall  = hallDA.retrieveHall(hallNo);
                selectedSchedule = new Schedule(rs.getString(1), selectedMovie, selectedHall, selectedTime, selectedDate);
            }
        return selectedSchedule;
    }
    
    public ArrayList<LocalDate> retrieveScheduleDate(String movieId) throws SQLException {
        String queryStr = "SELECT DISTINCT SCHEDULE_DATE FROM " + tableName + " WHERE MOVIE_ID = ? AND SCHEDULE_DATE >= CURRENT_DATE ORDER BY SCHEDULE_DATE"; 
        ArrayList<LocalDate> scheduleDate = new ArrayList<LocalDate>();      
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, movieId); 
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) { 
                scheduleDate.add(rs.getDate(1).toLocalDate());
            }
        return scheduleDate;
    }
    
    public ArrayList<LocalTime> retrieveScheduleTime(String movieId, LocalDate scheduleDate) throws SQLException{
        String queryStr = "SELECT SCHEDULE_TIME FROM " + tableName + " WHERE MOVIE_ID = ? AND SCHEDULE_DATE = ? ORDER BY SCHEDULE_TIME";
        ArrayList<LocalTime> scheduleTime = new ArrayList<LocalTime>();

            stmt = conn.prepareStatement(queryStr); 
            stmt.setString(1, movieId); 
            stmt.setDate(2, Date.valueOf(scheduleDate)); 
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) { //loop until the row of result set is empty
                if(scheduleDate.compareTo(LocalDate.now()) == 0){ //If user select the date same with today's date
                    if(rs.getTime(1).toLocalTime().compareTo(LocalTime.now()) > 0){ //Only display the showing time that is over the current time
                        scheduleTime.add(rs.getTime(1).toLocalTime());
                    }
                }
                else{
                    scheduleTime.add(rs.getTime(1).toLocalTime());
                }
            }
        return scheduleTime;
    }
    
    public ArrayList<Schedule> getScheduleByScheduleNo (ArrayList<String> scheduleNo) throws SQLException{
        ArrayList<Schedule> schedule = new ArrayList<Schedule>();
        String queryStr ="";   
        for(int i=0; i<scheduleNo.size(); i++){
            queryStr = "SELECT * FROM " + tableName + " WHERE schedule_no = '" + scheduleNo.get(i) + "'";
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(queryStr);
            Schedule tempSchedule = null;
            if(rs.next()){
                String movieTitle = movieDA.getMovieTitle(rs.getString(2));

                tempSchedule = new Schedule(rs.getString(1), new Movie(rs.getString(2), movieTitle), new Hall(rs.getString(3)), rs.getTime(4).toLocalTime(), rs.getDate(5).toLocalDate());
                schedule.add(tempSchedule);
            }
        }
        
        return schedule;
        
    }
    
    
    //delete schedule
    public void deleteSchedule(String scheduleNo) throws SQLException{
        String sqlQuery = "DELETE FROM " + tableName + " WHERE schedule_no = ?";    
            stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1,scheduleNo);
            stmt.executeUpdate();   
    }
    
    public ArrayList<Schedule> getSchedules(String movieId) throws SQLException {

        ArrayList<Schedule> s = new ArrayList<Schedule>();
        String sqlQueryStr = "SELECT * from  SCHEDULE WHERE MOVIE_ID = ?";
         ResultSet rs ;
        stmt = conn.prepareStatement(sqlQueryStr);
        stmt.setString(1, movieId);
        rs = stmt.executeQuery();
        while (rs.next()) {
            s.add( new Schedule(rs.getString(1), getMovie(rs.getString(2)),LocalTime.parse(rs.getString(4)), LocalDate.parse(rs.getString(5))));
        }

        return s;
    }

    
    public Movie getMovie(String movie_ID) throws SQLException  {
        String queryStr = "SELECT * FROM  MOVIE  WHERE MOVIE_ID = ?";
        Movie movie = null;
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1,movie_ID);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
               movie = new Movie(movie_ID,rs.getString(2));
            }
        return movie;
    }
    
    
    public Schedule getScheduleRecord(String schedule_no) throws SQLException{
        Schedule schedule = null; 
        String queryStr = "SELECT * FROM " +tableName + " WHERE schedule_no = ?";
        
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, schedule_no);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                Movie movie = movieDA.getCurrentMovie(rs.getString("movie_Id"));
                Hall hall = hallDA.retrieveHall(rs.getString("hall_no"));
                schedule = new Schedule(schedule_no, movie, hall, rs.getTime("schedule_time").toLocalTime(), rs.getDate("schedule_date").toLocalDate());
            }           
        
        return schedule;
    }
            
    private void createConnection() throws SQLException {
            conn = DriverManager.getConnection(host, user, password); //possible to return SQLException
    }

    private void shutDown() throws SQLException{
        if (conn != null) {
            conn.close();
        }
    }
    
}
