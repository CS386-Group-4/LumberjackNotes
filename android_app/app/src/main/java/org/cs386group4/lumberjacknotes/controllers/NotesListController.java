package org.cs386group4.lumberjacknotes.controllers;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.cs386group4.lumberjacknotes.R;
import org.cs386group4.lumberjacknotes.controllers.adapters.NotesAdapter;
import org.cs386group4.lumberjacknotes.models.Notes;
import org.cs386group4.lumberjacknotes.models.UserProfile;
import org.cs386group4.lumberjacknotes.ui.NotesListActivity;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class NotesListController
{
    NotesListActivity notesListActivity;

    public NotesListController(NotesListActivity notesListActivity)
    {
        this.notesListActivity = notesListActivity;
    }

    /**
     * Initialize the RecyclerView and NotesAdapter to display the list of notes
     */
    public void initNotesList(NotesListActivity notesListActivity)
    {
        if (UserProfile.getInstance().getWrittenNotes().size() == 0)
        {
            new Notes(UserProfile.getInstance()).setContent("First note\nStart typing now...");
        }

        NotesAdapter notesAdapter = new NotesAdapter(UserProfile.getInstance().getWrittenNotes());
        RecyclerView notesList = notesListActivity.findViewById(R.id.notes_list);

        notesList.addItemDecoration(new DividerItemDecoration(notesListActivity, DividerItemDecoration.VERTICAL));
        notesList.setLayoutManager(new LinearLayoutManager(notesListActivity));
        notesList.setAdapter(notesAdapter);
    }

    public void loadUserProfile(@NonNull Context context)
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
//
//    private void initNewNoteButton(MainActivity mainActivity)
//    {
//        FloatingActionButton newNoteButton = mainActivity.findViewById(R.id.new_note_button);
//
//        newNoteButton.setOnClickListener(view ->
//        {
//            new Notes(UserProfile.getInstance()).setContent("New note");
//            System.out.println(UserProfile.getInstance().getWrittenNotes().size());
//        });
//    }
}
