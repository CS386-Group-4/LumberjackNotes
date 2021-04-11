package org.cs386group4.lumberjacknotes.controllers;

import android.animation.LayoutTransition;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.constraintlayout.motion.widget.MotionLayout;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
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
    private boolean isLogin = true;

    // Declares variables for use with grabbing user input for the sign in process
    EditText signinEmailText;
    EditText signinPasswordText;
    private String signinEmail;
    private String signinPassword;
    // Declares variables for use with grabbing user input for the sign up process
    EditText signupEmailText;
    EditText signupPasswordText;
    EditText signupPasswordConfirmText;
    private String signupEmail;
    private String signupPassword;
    private String signupPasswordConfirm;
    // Declares variables for use with grabbing user input for the sign up authentication process
    EditText signupAuthenticationText;
    private String authenticationString;

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


        // Initializes variables for use with grabbing user input for the sign in process
        signinEmailText = loginActivity.findViewById(R.id.email_field);
        signinPasswordText = loginActivity.findViewById(R.id.password_field);
        signinEmail = signinEmailText.getText().toString();
        signinPassword = signinPasswordText.getText().toString();
        // Initializes variables for use with grabbing user input for the sign up process
        signupEmailText = signinEmailText;
        signupPasswordText = signinPasswordText;
        signupPasswordConfirmText = loginActivity.findViewById(R.id.passwordconfirm_field);
        signupEmail = signupEmailText.getText().toString();
        signupPassword = signupPasswordText.getText().toString();
        signupPasswordConfirm = signupPasswordConfirmText.getText().toString();
        // Initializes variables for use with grabbing user input for the sign up authentication process
        signupAuthenticationText = loginActivity.findViewById(R.id.verification_edit_text);

        // TODO: Cognito authentication
        Amplify.Auth.fetchAuthSession(
                result -> Log.i("AmplifyQuickstart", result.toString()),
                error -> Log.e("AmplifyQuickstart", error.toString())
        );
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
            // Initializes user input variables to user input text
            signinEmail = signinEmailText.getText().toString();
            signinPassword = signinPasswordText.getText().toString();
            signupEmail = signupEmailText.getText().toString();
            signupPassword = signupPasswordText.getText().toString();
            signupPasswordConfirm = signupPasswordConfirmText.getText().toString();
            signupAuthenticationText = loginActivity.findViewById(R.id.verification_edit_text);

            // If the user is signing in
            if (isLogin)
            {
                // Use AWS Amplify to sign in to the application
                Amplify.Auth.signIn(
                        signinEmail,
                        signinPassword,
                        result -> Log.i("AuthQuickstart", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete"),
                        error -> Log.e("AuthQuickstart", error.toString())
                );
                // Upon successful sign in, go to the notes list
                Intent intent = new Intent(loginActivity, NotesListActivity.class);
                loginActivity.startActivity(intent);
            }
            // Otherwise, the user must sign up for an account
            else
            {
                // Creates an account for the user on our AWS Amplify deployment
                AuthSignUpOptions options = AuthSignUpOptions.builder()
                        .userAttribute(AuthUserAttributeKey.email(), signupEmail)
                        .build();
                Amplify.Auth.signUp(signupEmail, signupPassword, options,
                        result -> Log.i("AuthQuickStart", "Result: " + result.toString()),
                        error -> Log.e("AuthQuickStart", "Sign up failed", error)
                );

                // Brings up AlertDialog needed to confirm the user's account
                registrationAlertDialog(loginActivity);
                // IF the user has inputted the verification code from their email, continue
                if(authenticationString != null)
                {
                    // Confirms the users account on our AWS Amplify deployment
                    Amplify.Auth.confirmSignUp(
                            signupEmail,
                            authenticationString,
                            result -> Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete"),
                            error -> Log.e("AuthQuickstart", error.toString())
                    );
                    // Upon successful sign up, go to the notes list
                    Intent intent = new Intent(loginActivity, NotesListActivity.class);
                    loginActivity.startActivity(intent);
                }
            }
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
            if (isLogin)
                motionLayout.transitionToEnd();
            else
                motionLayout.transitionToStart();

            // Set transition state
            isLogin = !isLogin;
        });
    }

    /**
     * Creates an Android AlertDialog to retrieve user input for sign up authentication
     * @param loginActivity Current context
     */
    public void registrationAlertDialog(LoginActivity loginActivity)
    {
        // Initializes AlertDialog builder variable and sets its title
        AlertDialog.Builder authenticationButton = new AlertDialog.Builder(loginActivity);
        authenticationButton.setTitle("Enter email authentication code:");
        // Initializes the AlertDialog view
        final View customLayout = loginActivity.getLayoutInflater().inflate(R.layout.account_verification_dialog, null);
        authenticationButton.setView(customLayout);

        // Creates confrim button and saves user input to the authentication string variable
        authenticationButton.setPositiveButton("Confirm", (dialog, which) ->
        {
            EditText confirmationCode = customLayout.findViewById(R.id.verification_edit_text);
            authenticationString = confirmationCode.getText().toString();
        });
//        // Placeholder code for resending authorization. AWS Amplify has no documentation for implementation in Java.
//        authenticationButton.setNegativeButton("Resend", (dialog, which) ->
//        {
//            // TODO
//        });
        // Creates cancel button on Alert Dialog; Does not need an implementation
        authenticationButton.setNeutralButton("Cancel", (dialog, which) ->
        {
        });
        // Creates and displays dialog from AlertDialog builder variable (authentication builder)
        AlertDialog dialog = authenticationButton.create();
        dialog.show();
    }
}
