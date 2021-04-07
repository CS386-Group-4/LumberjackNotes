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
//////        UserProfile dummyUserProfile = new UserProfile();
////        UserProfile dummyUserProfile = UserProfile.getInstance();
////        new Notes(dummyUserProfile);
////        new Notes(dummyUserProfile);
////        new Notes(dummyUserProfile);
////        new Notes(dummyUserProfile);
////        new Notes(dummyUserProfile);

        if(UserProfile.getInstance().getWrittenNotes().size() == 0)
        {
            new Notes(UserProfile.getInstance()).setContent("First note");
        }

        NotesAdapter notesAdapter = new NotesAdapter(UserProfile.getInstance().getWrittenNotes());

        System.out.println(UserProfile.getInstance().getWrittenNotes().size());

        RecyclerView notesList = findViewById(R.id.notes_list);

        notesList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        notesList.setLayoutManager(new LinearLayoutManager(this));
        notesList.setAdapter(notesAdapter);
    }
}