package org.cs386group4.lumberjacknotes;

import org.cs386group4.lumberjacknotes.models.Notes;
import org.cs386group4.lumberjacknotes.models.UserProfile;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class UserProfileUnitTest
{
    // Declares global variables to be used for testing the UserProfile.java model file
    ArrayList<Notes> globalNotesTestArrayList = new ArrayList<Notes>();
    Notes note0;
    Notes note1;
    Notes note2;
    Notes note3;

    /**
     * This method sets up operations to be done to aid other tests before each test executes.
     */
    @Before
    public void setUp()
    {
        note0 = new Notes(UserProfile.getInstance());
        note1 = new Notes(UserProfile.getInstance());
        note2 = new Notes(UserProfile.getInstance());
        note3 = new Notes(UserProfile.getInstance());
        globalNotesTestArrayList.add(note0);
        globalNotesTestArrayList.add(note1);
        globalNotesTestArrayList.add(note2);
        globalNotesTestArrayList.add(note3);
    }

    /**
     * This method tests that a note can be added to the user profile's ArrayList. A successful test would result in the
     * size of the ArrayList before adding a note plus one being equal to the size of the ArrayList after adding the
     * note being equal. The note is then deleted from the user profile's ArrayList.
     */
    @Test
    public void addNoteTest()
    {
        Notes addNoteTestNote = new Notes(UserProfile.getInstance());

        int sizeBeforeAddingNote = UserProfile.getInstance().getWrittenNotes().size();

        UserProfile.getInstance().addNote(addNoteTestNote);

        int sizeAfterAddingNote = UserProfile.getInstance().getWrittenNotes().size();

        assertEquals(sizeBeforeAddingNote + 1, sizeAfterAddingNote);

        note1.deleteNote(addNoteTestNote);
    }

    /**
     * This method tests that the user profile's notes ArrayList can be returned. A successful test would result in the
     * user profile's written notes list being equal to the testArrayList assigned to the user profile's written notes
     * list.
     */
    @Test
    public void getUserProfileWrittenNotes()
    {
        ArrayList<Notes> testArrayList = new ArrayList<Notes>();

        testArrayList = UserProfile.getInstance().getWrittenNotes();

        assertEquals(UserProfile.getInstance().getWrittenNotes(), testArrayList);
    }
}
