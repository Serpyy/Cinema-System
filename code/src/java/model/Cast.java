package model;

import java.io.Serializable;

public class Cast implements Serializable{
    private Movie movie;
    private Actor actor;
    
    public Cast() {
    }

    public Cast(Movie movie, Actor actor) {
        this.movie = movie;
        this.actor = actor;
    }

    public Movie getMovie() {
        return movie;
    }

    public Actor getActor() {
        return actor;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }
    
}
