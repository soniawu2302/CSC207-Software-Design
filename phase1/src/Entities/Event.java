package Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Event entity records basic information of an event, including event ids, title, time, location,
 * speakerID, attendees, organizerID.
 * @author Group_0112
 * @version 1.0
 * @since November 19th, 2020
 */

public class Event implements Serializable {

    private int id;
    private String title;
    private Date time;
    private String location;
    private String speakerID;
    private ArrayList<String> attendees;
    private String organizerID;


    /**
     * Constructor
     * @param title the title of this event
     * @param time the starting time of this event
     * @param location the location of this event
     * @param organizerID the user_id of organizer who hosts this event
     */
    public Event(String title, Date time, String location, String organizerID) {
        this.id = id_counter + 1;
        id_counter++;
    public Event(String title, Date time, String location, String organizerID, int id) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.location = location;
        this.organizerID = organizerID;
        this.attendees = new ArrayList<>();
    }

    /**
     * Gets the id of the event
     * @return id The id of the event
     */
    public int getID() {
        return this.id;
    }

    /**
     * Gets the title of the event
     * @return title The title of the event
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Gets the starting time of the event
     * @return time The starting time of the event
     */
    public Date getTime() { return this.time; }

    /**
     * Gets the location of the event
     * @return location The location of the event
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Get the speaker of the event
     * @return speakerID The user_id of the speaker of the event
     */
    public String getSpeakerID() {
        return this.speakerID;
    }

    /**
     * Gets a list of all user_id of the attendees in the event
     * @return ArrayList<String> A list of attendees' user_id
     */
    public ArrayList<String> getAttendees() {
        return this.attendees;
    }

    /**
     * Gets the organizer who hosts the event
     * @return organizerID The user_id of the organizer
     */
    public String getOrganizerID() {
        return this.organizerID;
    }

    /**
     * Sets title of the event
     * @param title The title of the event
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets time of the event
     * @param time The starting time of the event
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * Sets location of the event
     * @param location The location of the event
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Sets Speaker of the event by its user_id
     * @param speaker The user_id of the speaker
     */
    public void setSpeaker(String speaker) {
        this.speakerID = speaker;
    }

    /**
     * Sets Organizer to the event
     * @param organizer The organizer who hosts the event
     */
    public void setOrganizer(String organizer) {
        this.organizerID = organizer;
    }

    /**
     * Adds Attendee to the event by its user_id
     * @param attendeeID The user_id of the attendee
     */
    public void addAttendee(String attendeeID) {
        this.attendees.add(attendeeID);
    }

    /**
     * Removes Attendee from the event by its user_id
     * @param attendeeID The user_id of the attendee
     * @return boolean Returns true if it removed the attendee, false otherwise
     */
    public boolean removeAttendee(String attendeeID) {
        return this.attendees.remove(attendeeID);
    }

    /**
     * Checks if there's a Speaker for the event
     * @return boolean Returns true if there's a Speaker for the event, false otherwise
     */
    public boolean noSpeaker(){
        return this.speakerID == null;
    }

    @Override
    public String toString() {
        return "Event{ id=" + id + " | title='" + title +" | time=" + time + ", location='" + location + " | speakerID='" + speakerID + " | attendees=" + attendees + ", organizerID='" + organizerID +  '}';
    }
}