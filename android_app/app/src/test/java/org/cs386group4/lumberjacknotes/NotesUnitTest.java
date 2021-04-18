package org.cs386group4.lumberjacknotes;

import org.cs386group4.lumberjacknotes.models.Notes;
import org.cs386group4.lumberjacknotes.models.UserProfile;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class NotesUnitTest
{
    // Declares global variables to be used for testing the Notes.java model file
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
     * This method tests that unique IDs are created by creating two unique IDs and checking that they are not equal. A
     * successful test would result in the two uniqueIDs created not being equal.
     */
    @Test
    public void createUniqueIDTest()
    {
        int firstUniqueID = note0.createUniqueID();
        int secondUniqueID = note1.createUniqueID();

        assertNotEquals(firstUniqueID, secondUniqueID);
    }

    /**
     * This method tests that a note can be deleted by adding a note to an ArrayList, recording the size after the
     * addition, calling the deleteNote() method to delete the note that was added, and recording the size after the
     * deletion. A successful test would result in the size before deletion being equal to the size after deletion plus
     * one.
     */
    @Test
    public void deleteNoteTest()
    {
        Notes testDeleteNote = new Notes(UserProfile.getInstance());

        UserProfile.getInstance().addNote(testDeleteNote);

        int sizeBeforeDeletion = UserProfile.getInstance().getWrittenNotes().size();

        note1.deleteNote(testDeleteNote);

        int sizeAfterDeletion = UserProfile.getInstance().getWrittenNotes().size();

        assertEquals(sizeBeforeDeletion, sizeAfterDeletion + 1);
    }

    /**
     * This method tests that the content of a note can be returned by setting the content of a note to a specific
     * string and then comparing that string to the return value of the getContent() method call on the same note. A
     * successful test would result in the return value of the getContent() method call and the string being equal.
     */
    @Test
    public void getContentTest()
    {
        globalNotesTestArrayList.get(0).setContent("Get Content Test");

        assertEquals(globalNotesTestArrayList.get(0).getContent(), "Get Content Test");
    }

    /**
     * This method tests that the name of a note can be returned by setting the name of a note to a specific string and
     * then comparing that string to the return value of the getName() method call on the same note. A successful test
     * would result in the return value of the getName() method call and the string being equal.
     */
    @Test
    public void getNameTest()
    {
        globalNotesTestArrayList.get(0).setName("Get Name Test");

        assertEquals(globalNotesTestArrayList.get(0).getName(), "Get Name Test");
    }

    /**
     * This method tests the getUniqueID() method by comparing the unique IDs returned by two getUniqueID() calls on
     * two separate Notes objects. A successful test would result in the return values of both getUniqueID() calls not
     * being equal.
     */
    @Test
    public void getUniqueIDTest()
    {
        int firstUniqueID = note0.createUniqueID();
        int secondUniqueID = note1.createUniqueID();

        note1.setUniqueID(firstUniqueID);
        note2.setUniqueID(secondUniqueID);

        assertNotEquals(note1.getUniqueID(), note2.getUniqueID());
    }

    /**
     * This method tests that the user profile's notes ArrayList can be returned. A successful test would result in the
     * user profile's written notes list being equal to the testArrayList assigned to the user profile's written notes
     * list.
     */
    @Test
    public void getWrittenNotesTest()
    {
        ArrayList<Notes> testArrayList = new ArrayList<Notes>();

        testArrayList = UserProfile.getInstance().getWrittenNotes();

        assertEquals(UserProfile.getInstance().getWrittenNotes(), testArrayList);
    }

    /**
     * This method tests that the access controls of a note can be returned. A successful test would result in the
     * return value of the isAccessControl() method being false, as the default access control for a note is false.
     */
    @Test
    public void isAccessControlsTest()
    {
        assertFalse(globalNotesTestArrayList.get(0).isAccessControls());
    }

    /**
     * This method tests that searchNotes() method returns an ArrayList containing notes with the same title as the
     * search string. A successful test would result in the return value of the getName() method acted upon an object of
     * the searched notes list (testSearchArrayList) being equal with the original search string.
     */
    @Test
    public void searchNotesTest()
    {
        globalNotesTestArrayList.get(0).setName("Search Notes Test");

        ArrayList<Notes> testSearchArrayList = new ArrayList<Notes>();

        testSearchArrayList = note1.searchNotes("Search Notes Test");

        assertEquals(testSearchArrayList.get(0).getName(), "Search Notes Test");
    }

    /**
     * This method tests that the access controls of a note can be modified. A successful test results in the return
     * value of the isAccessControls() method being true or false depending to what it was set to by the
     * setAccessControls() method.
     */
    @Test
    public void setAccessControlsTest()
    {
        globalNotesTestArrayList.get(0).setAccessControls(true);

        assertTrue(globalNotesTestArrayList.get(0).isAccessControls());

        globalNotesTestArrayList.get(0).setAccessControls(false);

        assertFalse(globalNotesTestArrayList.get(0).isAccessControls());
    }

    /**
     * This method tests that the content of the note can be modified. A successful test results in the return value of
     * the getContent() method being equal to the string set as the note content by the setContent() method.
     */
    @Test
    public void setContentTest()
    {
        globalNotesTestArrayList.get(0).setContent("Set Content Test");

        assertEquals(globalNotesTestArrayList.get(0).getContent(), "Set Content Test");
    }

    /**
     * This method tests that the name of the note can be modified. A successful test results in the return value of the
     * getName() method being equal to the string set as the note content by the setName() method.
     */
    @Test
    public void setNameTest()
    {
        globalNotesTestArrayList.get(0).setName("Get Name Test");

        assertEquals(globalNotesTestArrayList.get(0).getName(), "Get Name Test");
    }

    /**
     * This method tests that the unique ID of a note can be modified. A successful test results in the return value of
     * the getUniqueID() method being equal to the created unique ID that was set as the unique ID of the note using the
     * setUniqueID() method.
     */
    @Test
    public void setUniqueIDTest()
    {
        int testUniqueID = note0.createUniqueID();

        globalNotesTestArrayList.get(0).setUniqueID(testUniqueID);

        assertEquals(testUniqueID, globalNotesTestArrayList.get(0).getUniqueID());
    }
}
