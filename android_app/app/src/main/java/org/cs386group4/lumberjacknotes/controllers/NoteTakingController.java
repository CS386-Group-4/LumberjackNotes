package org.cs386group4.lumberjacknotes.controllers;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatEditText;

import org.cs386group4.lumberjacknotes.R;
import org.cs386group4.lumberjacknotes.models.Notes;

public class NoteTakingController
{
    Notes note;

    public NoteTakingController(WorkspaceController workspaceController, ViewGroup notetakingRoot)
    {
        AppCompatEditText editor = notetakingRoot.findViewById(R.id.notetaking_editor);

        this.note = note;

        editor.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
            }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count)
            {
                // TODO: Put text into model
                //Log.e("NoteTakingController", "text: " + text.toString());
                note.setContent(text.toString());
            }

            @Override
            public void afterTextChanged(Editable s)
            {
            }
        });
    }

    public void setCurrentNote(Notes note)
    {
        this.note = note;
    }

    //
}
