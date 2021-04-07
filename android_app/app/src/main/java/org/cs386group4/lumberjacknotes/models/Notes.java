package org.cs386group4.lumberjacknotes.models;

import java.io.Serializable;
import java.util.Random;
import java.lang.String;
import java.util.ArrayList;

public class Notes implements Serializable // Testing Serializable for Data Storage
{
    // Declares global variables
    private UserProfile userProfile;
    private String name;
    private String content;
    private boolean accessControls;
    private int uniqueID;
    private String fileType;

    /**
     * This function serves as an initialization constructor when creating new Notes objects.
     */
    public Notes(UserProfile userProfile)
    {
        this.userProfile = userProfile;
        // This will be the default name for notes not given a name
        name = "Untitled Note";
        // This will be the default contents of the note body
        content = "";
        // This will be the default for the accessControls of a Notes object (true == public; false == private)
        accessControls = false;
        // This will be the default uniqueID for a Notes object that has not been assigned a truly uniqueID
        uniqueID = -1;
        // This will be the default fileType when exporting a note
        fileType = "pdf";

        userProfile.addNote(this);
    }

    /**
     * This function is a helper method for creating a new note. This will randomly generate an ID for a new note and
     * check to see if it is unique.
     * @return newly generated uniqueID
     */
    public int createUniqueID()
    {
        // Creates a new Random Object for generating a uniqueID
        Random randInt = new Random();
        // Sets the bound for the highest possible generated uniqueID to 100,000 (max amount of written notes)
        final int maxUniqueIDNumber = UserProfile.MAX_WRITTEN_NOTES;
        // Assigns the uniqueID with a random number between 0 and 99,999
        int uniqueID = randInt.nextInt(maxUniqueIDNumber);
        int index;
        // This for loop checks to see if the generated uniqueID is already in use
        for(index = 0; index < userProfile.getWrittenNotes().size() - 1; index++)
        {
            /*
             If the uniqueID generated matches an existing uniqueID, assign uniqueID with a new random number and start
             the loop from the beginning.
             */
            if(userProfile.getWrittenNotes().get(index).uniqueID == uniqueID)
            {
                uniqueID = randInt.nextInt(maxUniqueIDNumber);
                index = 0;
            }
        }
        return uniqueID;
    }

    /**
     * This function will delete a note by passing in the object to the writtenNotes ArrayList.remove() method.
     * @param noteToBeDeleted The note that the user wants to delete.
     */
    public void deleteNote(Notes noteToBeDeleted)
    {
        userProfile.getWrittenNotes().remove(noteToBeDeleted);
    }

    /**
     * TODO
     * This method allows the user to export their note as a file
     * @param fileType Type of file to be exported
     */
    public void exportNote(String fileType)
    {
        // TODO
    }

    /**
     * Getter method that returns the content of a note
     * @return Note content/body
     */
    public String getContent()
    {
        return content;
    }

    /**
     * Getter method that returns the name of a Notes object
     * @return Name of note
     */
    public String getName()
    {
        return name;
    }

    /**
     * Getter method that returns a note's uniqueID
     * @return uniqueID of Notes object
     */
    public int getUniqueID()
    {
        return uniqueID;
    }

    /**
     * This function returns the written notes ArrayList. Currently used to retrieve a copy of the array from
     * other classes, like the userProfile class.
     * @return ArrayList of written notes
     */
    public ArrayList<Notes> getWrittenNotes()
    {
        return userProfile.getWrittenNotes();
    }

    /**
     * Getter method that returns a note's current accessControls status
     * @return Note access status
     */
    public boolean isAccessControls()
    {
        return accessControls;
    }

//    /**
//     *
//     * @param mainActivity
//     */
//    public void loadNotes(MainActivity mainActivity)
//    {
//        try
//        {
//            // Opens file where the UserProfile will be stored locally
//            FileInputStream fileInputStream = mainActivity.openFileInput("UserProfile.txt");
//            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//            // Opens UserProfile object from file and closes read access
//            userProfile = (UserProfile) objectInputStream.readObject();
//            // TODO: May remove this current note assignment elsewhere once implementations supports multiple notes
//            currentNote = userProfile.getWrittenNotes().get(0);
//            objectInputStream.close();
//            fileInputStream.close();
//        }
//        catch(IOException | ClassNotFoundException e)
//        {
//            e.printStackTrace();
//        }
//
//    }

    /**
     * This function searches through the writtenNotes arrayList for notes that have a matching name to the
     * searchString. A new ArrayList of the matching notes is then returned.
     * @param searchString Name of notes that the user is looking for.
     * @return ArrayList of Notes objects that have a name containing the searchString.
     */
    public ArrayList<Notes> searchNotes(String searchString)
    {
        // New ArrayList of Notes objects that will hold the notes with names that match our search string.
        ArrayList<Notes> searchedNotes = new ArrayList<Notes>();

        int index;

        for(index = 0; index < userProfile.getWrittenNotes().size() - 1; index++)
        {
            /*
             This statement states that if the lowercase note name contains the lowercase searchString, add the note to
             the searchedNotes ArrayList.
             */
            if(userProfile.getWrittenNotes().get(index).name.toLowerCase().contains(searchString.toLowerCase()))
            {
                searchedNotes.add(userProfile.getWrittenNotes().get(index));
            }
        }
        return searchedNotes;
    }

    /**
     * Setter method that sets the accessControls of a note to that of the parameter
     * @param accessControls New access status
     */
    public void setAccessControls(boolean accessControls)
    {
        this.accessControls = accessControls;
    }

    /**
     * Setter method that sets the content of a note to that of the parameter
     * @param content New note content/body
     */
    public void setContent(String content)
    {
        this.content = content;
    }

    /**
     * Setter method that sets the name of a note to that of the parameter
     * @param name New note name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Setter method that sets the uniqueID of a note to that of the parameter
     * @param uniqueID New note uniqueID
     */
    public void setUniqueID(int uniqueID)
    {
        this.uniqueID = uniqueID;
    }
}
