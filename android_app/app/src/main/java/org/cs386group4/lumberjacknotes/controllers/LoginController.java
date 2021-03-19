package org.cs386group4.lumberjacknotes.controllers;

import android.content.Intent;

import com.google.android.material.button.MaterialButton;

import org.cs386group4.lumberjacknotes.R;
import org.cs386group4.lumberjacknotes.ui.LoginActivity;
import org.cs386group4.lumberjacknotes.ui.MainActivity;

public class LoginController
{
    public LoginController(LoginActivity loginActivity)
    {
        MaterialButton loginButton = loginActivity.findViewById(R.id.login_login_button);

        loginButton.setOnClickListener(view ->
        {
            Intent intent = new Intent(loginActivity, MainActivity.class);
            loginActivity.startActivity(intent);
        });
    }
}
