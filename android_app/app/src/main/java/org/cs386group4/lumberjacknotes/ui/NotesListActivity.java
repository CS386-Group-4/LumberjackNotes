package org.cs386group4.lumberjacknotes.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.cs386group4.lumberjacknotes.R;
import org.cs386group4.lumberjacknotes.controllers.adapters.NotesAdapter;
import org.cs386group4.lumberjacknotes.models.Notes;
import org.cs386group4.lumberjacknotes.models.UserProfile;

public class NotesListActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        testNotesAdapter();
    }

    private void testNotesAdapter()
    {
        UserProfile dummyUserProfile = new UserProfile();
        new Notes(dummyUserProfile);
        new Notes(dummyUserProfile);
        new Notes(dummyUserProfile);
        new Notes(dummyUserProfile);
        new Notes(dummyUserProfile);

        NotesAdapter notesAdapter = new NotesAdapter(dummyUserProfile.getWrittenNotes());

        RecyclerView notesList = findViewById(R.id.notes_list);
        notesList.setLayoutManager(new LinearLayoutManager(this));
        notesList.setAdapter(notesAdapter);
    }
}