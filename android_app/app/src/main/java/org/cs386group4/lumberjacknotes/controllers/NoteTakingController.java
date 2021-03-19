package org.cs386group4.lumberjacknotes.controllers;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatEditText;

import org.cs386group4.lumberjacknotes.R;

public class NoteTakingController
{
    public NoteTakingController(ViewGroup notetakingRoot)
    {
        AppCompatEditText editor = notetakingRoot.findViewById(R.id.notetaking_editor);

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
            }

            @Override
            public void afterTextChanged(Editable s)
            {
            }
        });
    }
}
