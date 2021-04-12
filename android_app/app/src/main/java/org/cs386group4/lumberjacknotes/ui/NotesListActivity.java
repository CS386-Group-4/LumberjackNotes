package org.cs386group4.lumberjacknotes.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.amplifyframework.auth.options.AuthSignOutOptions;
import com.amplifyframework.core.Amplify;
import com.google.android.material.button.MaterialButton;

import org.cs386group4.lumberjacknotes.R;
import org.cs386group4.lumberjacknotes.controllers.NotesListController;
import org.cs386group4.lumberjacknotes.controllers.adapters.NotesAdapter;
import org.cs386group4.lumberjacknotes.models.Notes;
import org.cs386group4.lumberjacknotes.models.UserProfile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class NotesListActivity extends AppCompatActivity
{
    NotesListController notesListController;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        notesListController = new NotesListController(this);
        // Load the UserProfile data once; future modifications stored in memory
        notesListController.loadUserProfile(this);

        initTopBar();
    }

    /**
     * Initialize the top bar (Toolbar) which will contain a menu
     */
    private void initTopBar()
    {
        // Set the activity's action bar to the Toolbar in the layout
        setSupportActionBar(findViewById(R.id.toolbar));

        // Refresh the menu on the activity's action bar
        this.invalidateOptionsMenu();
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        // Reinitialize notes list whenever navigating back to this activity
        notesListController.initNotesList(this);

//        // New note button is currently having issues
//        initNewNoteButton(mainNotesActivity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate notes_list_menu and apply to activity's action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notes_list_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        switch(menuItem.getItemId())
        {
            case R.id.notes_list_sign_out:
                Amplify.Auth.signOut(
                        AuthSignOutOptions.builder().globalSignOut(true).build(),
                        () -> Log.i("AuthQuickstart", "Signed out globally"),
                        error -> Log.e("AuthQuickstart", error.toString())
                );
                // Upon successful sign out, go to the notes list
                Intent intent = new Intent(this, LoginActivity.class);
                this.startActivity(intent);
                this.finish();
                return true;

            default:
                return false;
        }
    }
}