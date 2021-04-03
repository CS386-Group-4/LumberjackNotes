package org.cs386group4.lumberjacknotes.models;

import java.io.Serializable;
import java.util.ArrayList;

public class UserProfile implements Serializable
{
    private String[] personalDetails;
    private String[] enrolledGroups;
    // Declares max amount of notes that can be created by a user
    public final int maxWrittenNotes = 100000;
    private ArrayList<Notes> writtenNotes = new ArrayList<Notes>();

    /**
     * This function serves as a default constructor for UserProfile objects
     */
    public UserProfile()
    {
        personalDetails = new String[]{"FirstName LastName", "email", "password"};
        enrolledGroups = new String[]{"N/A"};
    }

    /**
     * Getter method that returns the writtenNotes array of Notes objects
     * @return writtenNotes array
     */
    public ArrayList<Notes> getWrittenNotes()
    {
        return writtenNotes;
    }

    /**
     * This function creates adds a note to the writtenNotes ArrayList
     * @param newNote Note object to be added to the ArrayList of writtenNotes
     */
    public void addNote(Notes newNote)
    {
        writtenNotes.add(newNote);
    }

    public void editProfile()
    {

    }


    public void logOut()
    {

    }
}
