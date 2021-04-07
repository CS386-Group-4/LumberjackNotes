package org.cs386group4.lumberjacknotes.models;

import java.io.Serializable;
import java.util.ArrayList;

public class UserProfile implements Serializable
{
    // Declares max amount of notes that can be created by a user
    public static final int MAX_WRITTEN_NOTES = 100000;

    // Declares arrays for holding account information, as well as an ArrayList for storing user notes
    private String[] personalDetails;
    private String[] enrolledGroups;
    private ArrayList<Notes> writtenNotes = new ArrayList<Notes>();
    // This is the only UserProfile instance available to the app (UserProfile can't be instantiated elsewhere)
    private static UserProfile instance = new UserProfile();

    /**
     * This method serves as a default constructor for UserProfile objects
     */
    private UserProfile()
    {
        personalDetails = new String[]{"FirstName LastName", "email", "password"};
        enrolledGroups = new String[]{"N/A"};
    }

    /**
     * This method adds a note to the writtenNotes ArrayList
     * @param newNote Note object to be added to the ArrayList of writtenNotes
     */
    public void addNote(Notes newNote)
    {
        writtenNotes.add(newNote);
    }

    /**
     * TODO
     * This method allows the user to edit the personalDetails array
     */
    public void editProfile()
    {
        // TODO
    }

    /**
     * Allows other parts of the program to retrieve the only available UserProfile instance
     * @return UserProfile Singleton instance
     */
    public static UserProfile getInstance()
    {
        return instance;
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
     * TODO
     * This method allows the user to logout of their Lumberjack Notes account
     */
    public void logOut()
    {
        // TODO
    }

    /**
     * This method allows us to enforce our Singleton design pattern regarding a UserProfile object. This will replace
     * the object read from a stream when serializing our UserProfile for local storage purposes. The object gets
     * replaced with the Singleton instance.
     * @return Singleton instance of UserProfile object
     */
    protected Object readResolve()
    {
        instance.personalDetails = this.personalDetails;
        instance.enrolledGroups = this.enrolledGroups;
        instance.writtenNotes = this.writtenNotes;

        return instance;
    }
}