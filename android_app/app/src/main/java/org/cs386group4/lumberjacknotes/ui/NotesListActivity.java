package org.cs386group4.lumberjacknotes.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import org.cs386group4.lumberjacknotes.R;
import org.cs386group4.lumberjacknotes.controllers.adapters.NotesAdapter;
import org.cs386group4.lumberjacknotes.models.Notes;
import org.cs386group4.lumberjacknotes.models.UserProfile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class NotesListActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        // Load the UserProfile data once; future modifications stored in memory
        loadUserProfile(this);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        // Reinitialize notes list whenever navigating back to this activity
        initNotesList();
    }

    /**
     * Initialize the RecyclerView and NotesAdapter to display the list of notes
     */
    private void initNotesList()
    {
        if (UserProfile.getInstance().getWrittenNotes().size() == 0)
        {
            new Notes(UserProfile.getInstance()).setContent("First note\nStart typing now...");
        }

        NotesAdapter notesAdapter = new NotesAdapter(UserProfile.getInstance().getWrittenNotes());
        RecyclerView notesList = findViewById(R.id.notes_list);

        notesList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        notesList.setLayoutManager(new LinearLayoutManager(this));
        notesList.setAdapter(notesAdapter);
    }

    private void loadUserProfile(@NonNull Context context)
    {

        try (FileInputStream fileInputStream = context.openFileInput("UserProfile.txt");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream))
        {
            // Opens and reads file where the UserProfile will be stored locally
            objectInputStream.readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}