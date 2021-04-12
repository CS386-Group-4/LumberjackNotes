package org.cs386group4.lumberjacknotes.controllers;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.cs386group4.lumberjacknotes.R;
import org.cs386group4.lumberjacknotes.controllers.adapters.NotesAdapter;
import org.cs386group4.lumberjacknotes.controllers.adapters.SearchAdapter;
import org.cs386group4.lumberjacknotes.models.Notes;
import org.cs386group4.lumberjacknotes.models.UserProfile;
import org.cs386group4.lumberjacknotes.ui.MainActivity;

public class NoteTakingController
{
    Notes currentNote;

    public NoteTakingController(WorkspaceController workspaceController, ViewGroup notetakingRoot)
    {
        AppCompatEditText editor = notetakingRoot.findViewById(R.id.notetaking_editor);

        currentNote = workspaceController.getCurrentNote();
        editor.setText(currentNote.getContent());

        editor.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
            }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count)
            {
                currentNote.setContent(text.toString());
            }

            @Override
            public void afterTextChanged(Editable s)
            {
            }
        });
    }

    public void setCurrentNote(Notes note)
    {
        this.currentNote = note;
    }

    // Still needs work to display search results in the note taking environment; Currently does nothing
    public void initSearchResults(MainActivity mainActivity)
    {
//        if (UserProfile.getInstance().getWrittenNotes().size() == 0)
//        {
//            new Notes(UserProfile.getInstance()).setContent("First note\nStart typing now...");
//        }

        SearchAdapter searchAdapter = new SearchAdapter(UserProfile.getInstance().getWrittenNotes());
        RecyclerView notesList = mainActivity.findViewById(R.id.notes_list);

        notesList.addItemDecoration(new DividerItemDecoration(mainActivity, DividerItemDecoration.VERTICAL));
        notesList.setLayoutManager(new LinearLayoutManager(mainActivity));
        notesList.setAdapter(searchAdapter);
    }
}
