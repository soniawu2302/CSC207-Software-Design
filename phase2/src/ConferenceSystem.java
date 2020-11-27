import Controller.*;
import Gateway.Serialization;
import UseCases.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 * The ConferenceSystem class stores all controllers, import/save states,
 * and it allows the entire program to work.
 * @author Group_0112
 * @version 1.0
 * @since November 19th, 2020
 */

public class ConferenceSystem {

    private SpeakerManager speakerManager;
    private RoomManager roomManager;
    private OrganizerManager organizerManager;
    private EventManager eventManager;
    private ChatManager chatManager;
    private AttendeeManager attendeeManager;

    private OrganizerSystem organizerSystem;
    private MessageSystem messageSystem;
    private LogInAndRegistrationSystem logInAndRegistrationSystem;
    private EventSystem eventSystem;
    private AttendeeSystem attendeeSystem;
    private SpeakerSystem speakerSystem;

    /**
     * Constructor
     */
    public ConferenceSystem(){
        importState();// this function will bring back the managers and initialize them
        logInAndRegistrationSystem = new LogInAndRegistrationSystem(attendeeManager, organizerManager, speakerManager);
        messageSystem = new MessageSystem(speakerManager,organizerManager, eventManager, chatManager, attendeeManager);
        eventSystem = new EventSystem(speakerManager, roomManager, organizerManager, eventManager, attendeeManager);
        organizerSystem = new OrganizerSystem(speakerManager, roomManager,organizerManager, eventManager, chatManager, attendeeManager, messageSystem, eventSystem);
        attendeeSystem = new AttendeeSystem(speakerManager, organizerManager, chatManager,attendeeManager, messageSystem, eventSystem, roomManager, eventManager);
        speakerSystem = new SpeakerSystem(speakerManager, organizerManager, chatManager, attendeeManager, messageSystem, eventSystem);
    }

    /**
     * Serialize all managers (use cases)
     */
    private void importState() {
        Serialization io = new Serialization();
        this.speakerManager = io.importStateSpeakerManager();
        this.roomManager =io.importStateRoomManager();
        this.organizerManager = io.importStateOrganizerManager();
        this.eventManager = io.importStateEventManager();
        this.chatManager = io.importStateChatManager();
        this.attendeeManager = io.importStateAttendeeManager();
    }

    /**
     * Saves all states of use cases.
     * @throws IOException  throw IOException to avoid errors that might occur while running the program
     */
    private void saveState() throws IOException {
        Serialization io = new Serialization();
        io.saveStateSpeakerManager(speakerManager);
        io.saveStateRoomManager(roomManager);
        io.saveStateOrganizerManager(organizerManager);
        io.saveStateEventManager(eventManager);
        io.saveStateChatManager(chatManager);
        io.saveStateAttendeeManager(attendeeManager);

    }

    /**
     * Runs the program
     * @throws IOException throw IOException to avoid errors that might occur while running the program
     * @throws ParseException throw ParseException to avoid errors that might occur while running the program
     */
    public void run () throws IOException, ParseException {
        deletePastEvents();
        boolean shutdown = false;
        Debugger debugger = new Debugger();
        debugger.printStateofSystem(organizerManager,speakerManager,attendeeManager,eventManager,roomManager);
        while (!shutdown) {
            String userID = logInAndRegistrationSystem.start();
            if (userID.equals("SHUTDOWN")){
                shutdown = true;
            }
            else if (organizerManager.userExist(userID)) {
                shutdown = organizerSystem.start(userID);
            }
            else if (attendeeManager.userExist(userID)) {
                shutdown = attendeeSystem.start(userID);
            }
            else {
                shutdown = speakerSystem.start(userID);
            }
            saveState();
        }
        saveState();
    }

    /**
     * Deletes past event before the program starts running.
     */
    private void deletePastEvents() {
        Date now = new Date();  //gets the current date
        ArrayList<Integer> listofEventIDS = eventManager.getListOfEventIDs();   //gets list of all eventIDs in the system
        //checks every eventID, and if its in the past, then deletes everything related to that event
        for (Integer eventID: listofEventIDS) {
            //if the event happened before right now, delete the event, and remove all attendees that are attending that event, organizer, speaker and free up the room.
            if (eventManager.getEvent(eventID).getTime().before(now)){
                ArrayList<String> peopleAttending = eventManager.getEvent(eventID).getAttendees();  //list of userid of all people attending

                for (String attendeeID: peopleAttending){
                    //check if attendee is a organizer attending the event
                    if (organizerManager.userExist(attendeeID)){
                        organizerManager.removeEvent(eventID, attendeeID);
                    }
                    //if its not a organizer, it must be a attendee
                    else {
                        attendeeManager.removeEvent(eventID,attendeeID);
                    }
                }
                //NOTE: THIS WORKS RIGHT NOW BECAUSE WE ONLY HAVE 1 SPEAKER, IN PHASE 2 WE WILL HAVE MULTIPLE SPEAKERS
                ArrayList<String> speakers = eventManager.getEvent(eventID).getSpeakerID();
                for (String speaker:speakers) {
                    speakerManager.getSpeaker(speaker).removeAssignEvent(eventID);
                }

                String organizerOfThisEvent = eventManager.getEvent(eventID).getOrganizerID();  //gets the userid of the organizer of this event
                //in the organizer's list of events that he/she created, this event is removed.
                organizerManager.removeEvent(eventID, organizerOfThisEvent);
                //removes the event from the room. NOTE THIS IS NOT NECESSARY AS YOU WILL NEVER BE ABLE TO ASSIGN A ROOM IN THE PAST, BUT JUST AS A SAFETY MEASURE.
                roomManager.removeEventFromRoom(eventManager.getEvent(eventID).getLocation(), eventID, eventManager.getTime(eventID));
                // and finally removes the event object itself from the event manager.
                eventManager.removeEvent(eventID);
            }
        }
    }
}

