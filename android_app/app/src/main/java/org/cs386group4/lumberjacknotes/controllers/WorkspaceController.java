package org.cs386group4.lumberjacknotes.controllers;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.cs386group4.lumberjacknotes.R;
import org.cs386group4.lumberjacknotes.models.Notes;
import org.cs386group4.lumberjacknotes.models.UserProfile;
import org.cs386group4.lumberjacknotes.ui.MainActivity;

import java.io.File;

public class WorkspaceController
{
    Notes currentNote;

    UserProfile dummyUserProfile = new UserProfile();

    public WorkspaceController(MainActivity mainActivity)
    {
        initNewNoteButton(mainActivity);
    }

    private void initNewNoteButton(MainActivity mainActivity)
    {
        FloatingActionButton newNoteButton = mainActivity.findViewById(R.id.new_note_button);

        newNoteButton.setOnClickListener(view ->
        {
            // TODO: Create new note
        });
    }

    public void saveNoteToStorage(MainActivity mainActivity)
    {
        int index;

        File file;

        for(index = 0; index< dummyUserProfile.getWrittenNotes().length - 1; index++)
        {
            file = new File(mainActivity.getFilesDir(), Integer.toString(dummyUserProfile.getWrittenNotes()[index].getUniqueID()));
        }
    }
}
