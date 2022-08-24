package model;

import java.io.Serializable;

public class Actor implements Serializable{
  private String actorID;
  private String actorName;

    public Actor() {
    }

    public Actor(String actorID) {
        this.actorID = actorID;
    }

    public Actor(String actorID, String actorName) {
        this.actorID = actorID;
        this.actorName = actorName;
    }

    public String getActorID() {
        return actorID;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorID(String actorID) {
        this.actorID = actorID;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }
  
}
