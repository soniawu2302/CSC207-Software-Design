package Entities;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The Chat program implements an application that simply records messages between two users.
 * @author Group_0112
 * @version 1.0
 * @since November 19th, 2020
 */

public class Chat implements Serializable {
    //id of one of the person
    private String id1;
    //id of the other person
    private String id2;
    //list of messages between the 2 people. In ascending order, meaning that the first message is the oldest one
    private ArrayList<Message> messages;

    /**
     * Constructor
     * @param id1
     * @param id2
     */
    public Chat(String id1, String id2){
        this.id1 = id1;
        this.id2 = id2;
        messages = new ArrayList<>();
    }

    //returns the id of the first user
    public String getId1() {
        return id1;
    }

    //returns the id of the second user
    public String getId2() {
        return id2;
    }

    //returns the list of messages between the 2 people
    public ArrayList<Message> getMessages() {
        return messages;
    }

    //adds a new message to the messages list.
    public void addMessages(String sender, String recipient, String context){
        Message newMessage = new Message(sender, recipient, context);
        messages.add(newMessage);
    }
}
