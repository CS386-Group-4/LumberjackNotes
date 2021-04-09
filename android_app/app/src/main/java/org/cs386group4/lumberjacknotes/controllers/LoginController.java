package org.cs386group4.lumberjacknotes.controllers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.google.android.material.button.MaterialButton;

import org.cs386group4.lumberjacknotes.R;
import org.cs386group4.lumberjacknotes.ui.LoginActivity;
import org.cs386group4.lumberjacknotes.ui.NotesListActivity;

/**
 * Controller to handle logging in with the {@link LoginActivity}
 */
public class LoginController
{
    /**
     * Initialize the login controller
     * @param loginActivity valid instance to get a {@link Context} from
     */
    public LoginController(LoginActivity loginActivity)
    {
        // Initialize login and register buttons
        initLoginButton(loginActivity);
        initRegisterButton(loginActivity);

        // TODO: Cognito authentication
        Amplify.Auth.fetchAuthSession(
                result -> Log.i("AmplifyQuickstart", result.toString()),
                error -> Log.e("AmplifyQuickstart", error.toString())
        );
    }

    /**
     * Initialize the login button such as by adding a click listener to it
     * @param loginActivity valid {@link LoginActivity} instance to get a {@link Context} from
     */
    private void initLoginButton(LoginActivity loginActivity)
    {
        MaterialButton loginButton = loginActivity.findViewById(R.id.login_button);

        // Handle logging in upon user clicking the login button
        loginButton.setOnClickListener(view ->
        {
            // TODO: Handle real login from cloud database
            Intent intent = new Intent(loginActivity, NotesListActivity.class);
            loginActivity.startActivity(intent);
        });
    }

    /**
     * Initialize the register button such as by adding a click listener to it
     * @param loginActivity valid instance to get a {@link Context} from
     */
    private void initRegisterButton(LoginActivity loginActivity)
    {
        MaterialButton registerButton = loginActivity.findViewById(R.id.register_button);

        // Handle registration upon user clicking the register button
        registerButton.setOnClickListener(view ->
        {
            // TODO: Handle registration with cloud
            Toast.makeText(loginActivity, "Register button pressed", Toast.LENGTH_SHORT).show();
        });
    }
}
