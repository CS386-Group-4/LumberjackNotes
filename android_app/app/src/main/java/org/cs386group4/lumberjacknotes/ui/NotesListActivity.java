package org.cs386group4.lumberjacknotes.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.cs386group4.lumberjacknotes.R;

public class NotesListActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);
    }
}