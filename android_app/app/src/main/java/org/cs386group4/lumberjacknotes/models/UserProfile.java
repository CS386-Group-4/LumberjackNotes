package org.cs386group4.lumberjacknotes.models;

public class UserProfile
{
    private String[] personalDetails;
    private String[] enrolledGroups;
    // Declares max amount of notes that can be created by a user
    public final int maxWrittenNotes = 100;
    private Notes[] writtenNotes = new Notes[maxWrittenNotes];

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
    public Notes[] getWrittenNotes()
    {
        return writtenNotes;
    }

    /**
     * This function creates a new Notes object and appends it to the writtenNotes array.
     * @param newNote Name given to the note by the user
     */
    public void addNote(Notes newNote)
    {
        int index;

        // This loop appends the new note to the array of written notes
        for(index = 0; index < writtenNotes.length; index++)
        {
            if(writtenNotes[index] == null)
            {
                writtenNotes[index] = newNote;
                break;
            }
        }
    }

    public void editProfile()
    {

    }


    public void logOut()
    {

    }
}
