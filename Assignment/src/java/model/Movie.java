package model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.time.LocalDate;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import sun.misc.BASE64Encoder;

public class Movie implements Serializable{
  private String movieID;
  private String movieTitle;
  private int duration;
  private LocalDate releaseDate;
  private String synopsis;
  private String director;
  private String distributor;
  private String ageRating;
  private ImageIcon poster;
  private InputStream inputStream;
  private long size;

  
    public Movie() {
    }

    public Movie(String movieID) {
        this.movieID = movieID;
    }

    public Movie(LocalDate releaseDate){
        this.releaseDate = releaseDate;
    }

    public Movie(String movieID,String movieTitle){
        this.movieID =movieID;
        this.movieTitle = movieTitle;
    }

    public Movie(String movieID, String movieTitle, int duration, LocalDate releaseDate, String synopsis, String director, String distributor, String ageRating, ImageIcon poster, InputStream inputStream, long size) {
        this.movieID = movieID;
        this.movieTitle = movieTitle;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.synopsis = synopsis;
        this.director = director;
        this.distributor = distributor;
        this.ageRating = ageRating;
        this.poster = poster;
        this.inputStream = inputStream;
        this.size = size;
    }

    public Movie(String movieID, String movieTitle, int duration, LocalDate releaseDate, String synopsis, String director, String distributor, String ageRating, ImageIcon poster) {
        this.movieID = movieID;
        this.movieTitle = movieTitle;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.synopsis = synopsis;
        this.director = director;
        this.distributor = distributor;
        this.ageRating = ageRating;
        this.poster = poster;
    }

    public Movie(String movieID, String movieTitle, int duration, LocalDate releaseDate, String synopsis, String director, String distributor, String ageRating, InputStream inputStream, long size) {
        this.movieID = movieID;
        this.movieTitle = movieTitle;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.synopsis = synopsis;
        this.director = director;
        this.distributor = distributor;
        this.ageRating = ageRating;
        this.inputStream = inputStream;
        this.size = size;
    }

    public Movie(String movieID, String movieTitle, int duration, LocalDate releaseDate, String synopsis, String director, String distributor, String ageRating) {
        this.movieID = movieID;
        this.movieTitle = movieTitle;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.synopsis = synopsis;
        this.director = director;
        this.distributor = distributor;
        this.ageRating = ageRating;
    }

    public String getMovieID() {
        return movieID;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public int getDuration() {
        return duration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getDirector() {
        return director;
    }

    public String getDistributor() {
        return distributor;
    }

    public String getAgeRating() {
        return ageRating;
    }

    public ImageIcon getPoster() {
        return poster;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public long getSize() {
        return size;
    }
        
    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

    public void setAgeRating(String ageRating) {
        this.ageRating = ageRating;
    }

    public void setPoster(ImageIcon poster) {
        this.poster = poster;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getImgString() throws java.io.IOException{
        String strImage = "";
        
        if(poster != null){
            ByteArrayOutputStream byteArrOutStream = new ByteArrayOutputStream();

            int width = poster.getImage().getWidth(null);
            int height = poster.getImage().getHeight(null);

            BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            buffImg.createGraphics().drawImage(poster.getImage(), 0, 0, null);

            byte[] imgByteArr = null;
            
            if(buffImg != null){
                ImageIO.write(buffImg, "png", byteArrOutStream);
                imgByteArr = byteArrOutStream.toByteArray();  

                BASE64Encoder base64Encoder = new BASE64Encoder();
                StringBuilder imageString = new StringBuilder();
                imageString.append("data:image/png;base64,");
                imageString.append(base64Encoder.encode(imgByteArr));
                strImage = imageString.toString();                      
            }
        }
        
        return strImage; 
    }

}
