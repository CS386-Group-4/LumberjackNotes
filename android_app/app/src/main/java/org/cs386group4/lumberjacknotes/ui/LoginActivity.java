package org.cs386group4.lumberjacknotes.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.cs386group4.lumberjacknotes.R;
import org.cs386group4.lumberjacknotes.controllers.LoginController;

public class LoginActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        new LoginController(this);
    }
}