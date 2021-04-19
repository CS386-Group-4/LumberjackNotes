package org.cs386group4.lumberjacknotes;

import org.cs386group4.lumberjacknotes.models.Notes;
import org.cs386group4.lumberjacknotes.models.UserProfile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserNotesIntegrationTest
{
    private UserProfile userProfile;
    private Notes noteOne;

    @Before
    public void setUp()
    {
        userProfile = UserProfile.getInstance();
        userProfile.getWrittenNotes().clear();
        noteOne = new Notes(userProfile);
    }

    @Test
    public void bridgeRelationshipTest()
    {
        // Test UserProfile bridge in Notes
        assertEquals(userProfile, noteOne.getUserProfile());

        // Test Notes bridge in UserProfile
        assertEquals(noteOne, userProfile.getWrittenNotes().get(0));
    }
}