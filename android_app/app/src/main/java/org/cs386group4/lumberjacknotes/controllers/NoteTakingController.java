package org.cs386group4.lumberjacknotes.controllers;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatEditText;

import org.cs386group4.lumberjacknotes.R;
import org.cs386group4.lumberjacknotes.models.Notes;

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
}
