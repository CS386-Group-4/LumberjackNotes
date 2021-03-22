package org.cs386group4.lumberjacknotes.models;

import java.util.Random;
import java.lang.String;
import java.util.UUID;

public class Notes
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
        // This will initialize the global userProfile object to the userProfile parameter
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
        // Sets the bound for the highest possible generated uniqueID to one million (max amount of written notes)
        final int maxUniqueIDNumber = userProfile.maxWrittenNotes;
        // Assigns the uniqueID with a random number between 0 and 999,999
        int uniqueID = randInt.nextInt(maxUniqueIDNumber);
        // Declares looping variable
        int index;
        // This for loop checks to see if the generated uniqueID is already in use
        for(index = 0; index < userProfile.getWrittenNotes().length - 1; index++)
        {
            /*
             If the uniqueID generated matches an existing uniqueID, assign uniqueID with a new random number and start
             the loop from the beginning.
             */
            if(userProfile.getWrittenNotes()[index].uniqueID == uniqueID)
            {
                uniqueID = randInt.nextInt(maxUniqueIDNumber);
                index = 0;
            }
        }
        return uniqueID;
    }

    /**
     * This function will delete a note by retrieving the index it is at in the writtenNotes array and overwriting it.
     * @param noteToBeDeleted The note that the user wants to delete.
     */
    public void deleteNote(Notes noteToBeDeleted)
    {
        // Initializes local variable to the uniqueID of the note that will be deleted
        int uniqueID = noteToBeDeleted.uniqueID;
        // Uses the getNote() helper function to retrieve the index of the array where the note is stored
        int noteToBeDeletedIndex = getNote(uniqueID);
        // Declares looping variable
        int index;

        /*
         This loop will remove the note from the array by starting at the index of the note. The contents of the array
         will then be shifted over once to the left in order to overwrite the note.
         */
        for(index = noteToBeDeletedIndex; index < userProfile.getWrittenNotes().length - 1; index++)
        {
            // If the index is between the noteToBeDeletedIndex and 999,998th value, shift the contents of the array.
            if(index != userProfile.getWrittenNotes().length - 1)
            {
                userProfile.getWrittenNotes()[index] = userProfile.getWrittenNotes()[index + 1];
            }
            // If the index is at the end of the array, set the end to null.
            else
            {
                userProfile.getWrittenNotes()[index] = null;
            }
        }
    }

    /**
     * TODO
     * @param fileType
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
     * This function is a helper method for deleting a note. This will return the index for a note.
     * @param uniqueID The uniqueID of a Notes object.
     * @return The index of a note in the array.
     */
    public int getNote(int uniqueID)
    {
        // Declares looping variable
        int index;
        // Declares a number to be returned in case of an error
        final int errorNumber = -1;

        // This loop finds the index of the note in the array and returns it
        for(index = 0; index < userProfile.getWrittenNotes().length - 1; index++)
        {
            if(userProfile.getWrittenNotes()[index].uniqueID == uniqueID)
            {
                return index;
            }
        }
        // If the index of the note in the array cannot be found, a number outside the bounds of the array is returned
        return errorNumber;
    }

    /**
     *
     * @return
     */
    public int getUniqueID()
    {
        return uniqueID;
    }

    /**
     * This function returns a copy of the written notes array. Currently used to retrieve a copy of the array from
     * other classes, like the userProfile class.
     * @return Copy of user notes array;
     */
    public Notes[] getWrittenNotes()
    {
//        // May delete this due to an implementation change
//        // Declares temporary array that will be returned with the contents of the writtenNotes array
//        Notes arrayToBeReturned[] = new Notes[maxWrittenNotes];
//        // Declares looping variable
//        int index;
//        // This loop copies the contents of the writtenNotes array to the arrayToBeReturned array
//        for(index = 0; index < arrayToBeReturned.length - 1; index++)
//        {
//            arrayToBeReturned[index] = writtenNotes[index];
//        }
//        return arrayToBeReturned;
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

    /**
     * This function searches through the writtenNotes array for notes that have a matching name to the searchString. A
     * new array of the matching notes is then returned.
     * @param searchString Name of notes that the user is looking for.
     * @return Array of Notes objects that have a name containing the searchString.
     */
    public Notes[] searchNotes(String searchString)
    {
        // New array of Notes objects that will hold the notes with names that match our search string.
        Notes[] searchedNotes = new Notes[userProfile.maxWrittenNotes];

        // Declares looping variables
        int index;
        // Initializes variable to be used to copy to searchedNotes array
        int copyIndex = 0;

        // This for loop iterates through the writtenNotes array and checks the names of the notes
        for(index = 0; index < userProfile.getWrittenNotes().length - 1; index++)
        {
            /*
             This statement states that if the lowercase note name contains the lowercase searchString, add the note to
             the searchedNotes array.
             */
            if(userProfile.getWrittenNotes()[index].name.toLowerCase().contains(searchString.toLowerCase()))
            {
                searchedNotes[copyIndex] = userProfile.getWrittenNotes()[index];
                copyIndex++;
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
     *
     * @param uniqueID
     */
    public void setUniqueID(int uniqueID)
    {
        this.uniqueID = uniqueID;
    }
}
