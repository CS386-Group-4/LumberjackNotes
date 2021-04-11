package org.cs386group4.lumberjacknotes.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.ViewGroup;
import android.widget.SearchView;

import org.cs386group4.lumberjacknotes.R;
import org.cs386group4.lumberjacknotes.controllers.NoteTakingController;
import org.cs386group4.lumberjacknotes.controllers.WorkspaceController;
import org.cs386group4.lumberjacknotes.models.UserProfile;

public class MainActivity extends AppCompatActivity
{
    WorkspaceController workspaceController;

//    Save save;

    int notePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the toolbar from layout
        Toolbar toolbar = findViewById(R.id.toolbar);

        // initialize toolbar
        setSupportActionBar(toolbar);
        this.invalidateOptionsMenu();

        notePosition = getIntent().getIntExtra("note_position", 0);

        // Initialize workspace controller
        workspaceController = new WorkspaceController(this, notePosition);

        // Initialize note taking controller
        ViewGroup notetakingRoot = findViewById(R.id.notetaking_root);
        new NoteTakingController(workspaceController, notetakingRoot);
    }

//    // Intended to update the note position so that a new note opens the most recent note instead of the first note
//    @Override
//    protected void onResume()
//    {
//        super.onResume();
//
//        notePosition = UserProfile.getInstance().getWrittenNotes().indexOf(UserProfile.getInstance().getWrittenNotes().size());
//    }

    @Override
    protected void onDestroy()
    {
        // TODO: Save notes cloud
        workspaceController.saveNoteToStorage(this);

        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.main_note_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }
}