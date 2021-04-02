package org.cs386group4.lumberjacknotes.controllers;

import android.util.Log;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.cs386group4.lumberjacknotes.R;
import org.cs386group4.lumberjacknotes.models.Notes;
import org.cs386group4.lumberjacknotes.models.UserProfile;
import org.cs386group4.lumberjacknotes.ui.MainActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkspaceController
{
    Notes currentNote;

    UserProfile dummyUserProfile = new UserProfile();

    public WorkspaceController(MainActivity mainActivity)
    {
        loadNotes(mainActivity);
        initNewNoteButton(mainActivity);

        // If no notes were previously created and written to disk, create a blank note
        if (currentNote == null)
        {
            currentNote = new Notes(dummyUserProfile);
        }
    }

    private void initNewNoteButton(MainActivity mainActivity)
    {
        FloatingActionButton newNoteButton = mainActivity.findViewById(R.id.new_note_button);

        newNoteButton.setOnClickListener(view ->
        {
            // TODO: Create new note
        });
    }

    private void loadNotes(MainActivity mainActivity)
    {
        String[] noteFilenames = mainActivity.fileList();
        File currentFile;
        BufferedReader reader;
        StringBuilder fileContent;
        String line;

        // Loop through all note files in app directory
        for (String noteFilename : noteFilenames)
        {
            try
            {
                // Get the file corresponding to the current filename
                currentFile = new File(mainActivity.getFilesDir(), noteFilename);
                reader = new BufferedReader(new FileReader(currentFile));
                fileContent = new StringBuilder();

                // Read all lines from each file into a StringBuilder
                int lineIndex = 0;
                while ((line = reader.readLine()) != null)
                {
                    fileContent.append(line);
                }

                reader.close();

                // Load new Notes object with content from file
                currentNote = new Notes(dummyUserProfile);
                currentNote.setName(noteFilename);
                currentNote.setContent(fileContent.toString());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void saveNoteToStorage(MainActivity mainActivity)
    {
        int index;
        File file;
        BufferedWriter writer;
        Notes tmpNote = dummyUserProfile.getWrittenNotes().get(0);

        index = 0;
        while (tmpNote != null)
        {
            // Create File object for new note file
            file = new File(mainActivity.getFilesDir(), Integer.toString(tmpNote.getUniqueID()));

            // Write the contents of the Notes objects to their own files
            try
            {
                writer = new BufferedWriter(new FileWriter(file));
                writer.write(tmpNote.getContent());
                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            // Move to next note
            index++;
            tmpNote = dummyUserProfile.getWrittenNotes().get(index);
        }
    }

    public Notes getCurrentNote()
    {
        return currentNote;
    }
}
