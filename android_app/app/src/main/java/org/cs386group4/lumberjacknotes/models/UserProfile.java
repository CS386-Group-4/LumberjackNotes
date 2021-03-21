package org.cs386group4.lumberjacknotes.models;

public class UserProfile
{
    String[] personalDetails;
    String[] enrolledGroups;

    Notes notes = new Notes();
    Notes[] notesArrayToBeUploaded = notes.getWrittenNotes();

    void editProfile()
    {

    }

    void logOut()
    {

    }

}
