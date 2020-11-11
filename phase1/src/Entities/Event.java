package Entities;

import java.util.ArrayList;


public class Event {

    public static int id_counter;
    private int id;
    private String title;
    private String time;
    private String location;
    private String speakerID;
    private ArrayList<String> attendees;
    private String organizerID;


    public Event(String title, String time, String location, String speakerID, String organizerID) {
        this.id = id_counter + 1;
        id_counter++;
        this.title = title;
        this.time = time;
        this.location = location;
        this.speakerID = speakerID;
        this.organizerID = organizerID;
        this.attendees = new ArrayList<>();
    }

    //gets the id of the event
    public int getID() {
        return this.id;
    }

    //gts the title of event
    public String getTitle() {
        return this.title;
    }

    //gets the time of event
    public String getTime() {
        return this.time;
    }

    //gets the location of event
    public String getLocation() {
        return this.location;
    }

    //gets the speaker
    public String getSpeaker() {
        return this.speakerID;
    }

    //returns the entire list of attendees
    public ArrayList<String> getAttendees() {
        return this.attendees;
    }

    //gets the organizer by id
    public String getOrganizerID() {
        return this.organizerID;
    }

    //sets title of event
    public void setTitle(String title) {
        this.title = title;
    }

    //sets time of event
    public void setTime(String time) {
        this.time = time;
    }

    //sets location of event
    public void setLocation(String location) {
        this.location = location;
    }

    //sets speaker by id
    public void setSpeaker(String speaker) {
        this.speakerID = speaker;
    }

    //sets organizer
    public void setOrganizer(String organizer) {
        this.organizerID = organizer;
    }

    // adds attendee based on given id
    public void addAttendee(String attendeeID) {
        this.attendees.add(attendeeID);
    }

    // Remove attendee based on given id. returns true if it removed the attendee, false otherwise
    public boolean removeAttendee(String attendeeID) {
        return this.attendees.remove(attendeeID);
    }
}