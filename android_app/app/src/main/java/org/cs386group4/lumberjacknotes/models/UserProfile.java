package org.cs386group4.lumberjacknotes.models;

public class UserProfile
{
    private String[] personalDetails;
    private String[] enrolledGroups;
    // Declares max amount of notes that can be created by a user
    public final int maxWrittenNotes = 1000000;
    private Notes[] writtenNotes = new Notes[maxWrittenNotes];


    public UserProfile()
    {

    }

    public Notes[] getWrittenNotes()
    {
        return writtenNotes;
    }

    public void editProfile()
    {

    }

    public void logOut()
    {

    }
}
