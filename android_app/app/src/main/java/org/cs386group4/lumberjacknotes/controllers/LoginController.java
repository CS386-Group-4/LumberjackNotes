package org.cs386group4.lumberjacknotes.controllers;

import android.content.Intent;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import org.cs386group4.lumberjacknotes.R;
import org.cs386group4.lumberjacknotes.ui.LoginActivity;
import org.cs386group4.lumberjacknotes.ui.MainActivity;

public class LoginController
{
    public LoginController(LoginActivity loginActivity)
    {
        initLoginButton(loginActivity);
        initRegisterButton(loginActivity);
    }

    private void initLoginButton(LoginActivity loginActivity)
    {
        MaterialButton loginButton = loginActivity.findViewById(R.id.login_button);

        loginButton.setOnClickListener(view ->
        {
            // TODO: Handle login
            Intent intent = new Intent(loginActivity, MainActivity.class);
            loginActivity.startActivity(intent);
        });
    }

    private void initRegisterButton(LoginActivity loginActivity)
    {
        MaterialButton registerButton = loginActivity.findViewById(R.id.register_button);

        registerButton.setOnClickListener(view ->
        {
            // TODO: Handle registration
            Toast.makeText(loginActivity, "Register button pressed", Toast.LENGTH_SHORT).show();
        });
    }
}
