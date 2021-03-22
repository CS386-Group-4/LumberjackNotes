package org.cs386group4.lumberjacknotes.controllers;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.cs386group4.lumberjacknotes.R;
import org.cs386group4.lumberjacknotes.models.Notes;
import org.cs386group4.lumberjacknotes.ui.MainActivity;

public class WorkspaceController
{
    Notes currentNote;

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
}
