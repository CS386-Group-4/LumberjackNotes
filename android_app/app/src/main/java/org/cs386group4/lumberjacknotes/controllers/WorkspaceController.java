package org.cs386group4.lumberjacknotes.controllers;

import android.content.Context;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.cs386group4.lumberjacknotes.R;
import org.cs386group4.lumberjacknotes.models.Notes;
import org.cs386group4.lumberjacknotes.models.UserProfile;
import org.cs386group4.lumberjacknotes.ui.MainActivity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WorkspaceController
{
    Notes currentNote;

    UserProfile dummyUserProfile = UserProfile.getInstance();

    public WorkspaceController(MainActivity mainActivity, int notePosition)
    {
        initNewNoteButton(mainActivity);

        currentNote = dummyUserProfile.getWrittenNotes().get(notePosition);
    }

    private void initNewNoteButton(MainActivity mainActivity)
    {
        FloatingActionButton newNoteButton = mainActivity.findViewById(R.id.new_note_button);

        newNoteButton.setOnClickListener(view ->
        {
            new Notes(UserProfile.getInstance()).setContent("New note");
            System.out.println(UserProfile.getInstance().getWrittenNotes().size());
        });
    }

    public void saveNoteToStorage(MainActivity mainActivity)
    {
        try (FileOutputStream fileOutputStream = mainActivity.openFileOutput("UserProfile.txt",
                Context.MODE_PRIVATE);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream))
        {
            // Creates file where the UserProfile will be stored locally
            objectOutputStream.writeObject(dummyUserProfile);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Notes getCurrentNote()
    {
        return currentNote;
    }
}
