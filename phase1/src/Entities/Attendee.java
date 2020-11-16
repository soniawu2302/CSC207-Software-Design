package Entities;

import java.util.ArrayList;
import java.io.*;

/** The Attendee program implements an application that simply records users
 * and their list of signed-event. Events can be added or removed.
 * @author Natalie (Yun-Ho)
 * @version 1.0
 * @since November 16, 2020
 */

public class Attendee extends User{

    private ArrayList<Integer> signedUpEvents;

    /** Constructor
     * @param user_id the user id of this user. User id is an unique integer for each user.
     * @param name    the registered name of this user.
     * @param passwords the registered passwords of this user.
     */
    public Attendee(String user_id, String name, String passwords){
        super(user_id, name, passwords);
        this.signedUpEvents = new ArrayList<>();
    }

    /** Returns an arraylist of ids of signed-up event by the user.
     * @return ArrayList of all IDs of signed-up events
     * @see Event
     */
    public ArrayList<Integer> getSignedUpEvents() {
        return signedUpEvents;
    }

    /** Adds an event by event id to the list of signed-up events of the attendee.
     * @param EventId  the id of an event that is wished to be added
     * @see Event
     */
    public void addEvent(Integer EventId){
        this.signedUpEvents.add(EventId);
    }

    /** Removes an event by event id from the list of signed-up events of the attendee.
     * @param EventId  the id of an event that is wished to be removed
     * @return boolean  returns true if the event has been successfully removed, false otherwise.
     * @see Event
     */
    public boolean removeEvent(Integer EventId){
        return this.signedUpEvents.remove(EventId);
    }
    }