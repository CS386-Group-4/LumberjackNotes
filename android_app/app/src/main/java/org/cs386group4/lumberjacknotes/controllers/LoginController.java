package org.cs386group4.lumberjacknotes.controllers;

import android.animation.LayoutTransition;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.constraintlayout.motion.widget.MotionLayout;

import com.amplifyframework.core.Amplify;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import org.cs386group4.lumberjacknotes.R;
import org.cs386group4.lumberjacknotes.ui.LoginActivity;
import org.cs386group4.lumberjacknotes.ui.NotesListActivity;

/**
 * Controller to handle logging in with the {@link LoginActivity}
 */
public class LoginController
{
    private boolean transitionedToStart = true;

    /**
     * Initialize the login controller
     * @param loginActivity valid instance to get a {@link Context} from
     */
    public LoginController(LoginActivity loginActivity)
    {
        MotionLayout motionLayout = loginActivity.findViewById(R.id.login_container);

        motionLayout.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        // Initialize login and register buttons
        initMotionLayout(loginActivity, motionLayout);
        initLoginButton(loginActivity);
        initRegisterButton(loginActivity, motionLayout);
    }

    private void initMotionLayout(Activity activity, MotionLayout motionLayout)
    {
        TextInputLayout passwordConfirmContainer = activity.findViewById(R.id.passwordconfirm_field_container);

        motionLayout.addTransitionListener(new MotionLayout.TransitionListener()
        {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int startId, int endId) {}

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float progress)
            {
                // Enable or disable password confirmation text box
                if (progress == 0)
                    passwordConfirmContainer.setEnabled(false);
                else if (progress == 1)
                    passwordConfirmContainer.setEnabled(true);
            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {}

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int triggerId, boolean positive, float progress)
            {}
        });

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
    private void initRegisterButton(LoginActivity loginActivity, MotionLayout motionLayout)
    {
        MaterialButton registerButton = loginActivity.findViewById(R.id.register_button);

        // Handle registration upon user clicking the register button
        registerButton.setOnClickListener(view ->
        {
            // Handle MotionLayout transition
            if (transitionedToStart)
                motionLayout.transitionToEnd();
            else
                motionLayout.transitionToStart();

            // Set transition state
            transitionedToStart = !transitionedToStart;
        });
    }
}
