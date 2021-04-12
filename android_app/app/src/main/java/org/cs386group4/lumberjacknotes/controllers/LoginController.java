package org.cs386group4.lumberjacknotes.controllers;

import android.animation.LayoutTransition;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.motion.widget.MotionLayout;

import com.amplifyframework.auth.AuthException;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.auth.result.AuthSignInResult;
import com.amplifyframework.auth.result.AuthSignUpResult;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.Consumer;
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
    private final @NonNull LoginActivity loginActivity;
    private final @NonNull MotionLayout motionLayout;

    // Whether the activity is set to handle logging in or signing up
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
    private String signupPasswordConfirm; // TODO: Use this to validate password
    // Declares variables for use with grabbing user input for the sign up authentication process
    EditText signupAuthenticationText;

    /**
     * Initialize the login controller
     * @param loginActivity valid instance to get a {@link Context} from
     */
    public LoginController(@NonNull LoginActivity loginActivity)
    {
        this.loginActivity = loginActivity;
        this.motionLayout = loginActivity.findViewById(R.id.login_container);

        motionLayout.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        // Initialize login and register buttons
        initMotionLayout(loginActivity, motionLayout);
        initLoginButton();
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

        // Fetch authentication session from AWS
        Amplify.Auth.fetchAuthSession(
                result ->
                {
                    AWSCognitoAuthSession cognitoAuthSession = (AWSCognitoAuthSession) result;

                    switch (cognitoAuthSession.getIdentityId().getType())
                    {
                        case SUCCESS:
                            Log.i("AuthQuickStart", "IdentityId: " + cognitoAuthSession.getIdentityId().getValue());
                            break;
                        case FAILURE:
                            assert cognitoAuthSession.getIdentityId().getError() != null; // Is not null because of Type.FAILURE
                            Log.i("AuthQuickStart", "IdentityId not present because: " + cognitoAuthSession.getIdentityId().getError().toString());
                            // TODO: Display warning to user
                    }
                },
                error ->
                {
                    Log.e("AmplifyQuickstart", error.toString());
                    // TODO: Display warning to user
                });
    }

    private static void initMotionLayout(Activity activity, MotionLayout motionLayout)
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
     */
    private void initLoginButton()
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

            Log.e("initLoginButton", "isLogin: " + isLogin);

            if (isLogin)
            {
                Amplify.Auth.signIn(
                        signinEmail,
                        signinPassword,
                        onSignInSuccess,
                        onSignInError);
            }
            else
            {
                // Creates an account for the user on our AWS Amplify deployment
                AuthSignUpOptions options = AuthSignUpOptions.builder()
                        .userAttribute(AuthUserAttributeKey.email(), signupEmail)
                        .build();

                Amplify.Auth.signUp(
                        signupEmail,
                        signupPassword,
                        options,
                        onSignUpSuccess,
                        onSignUpError);
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
     * Creates an {@link AlertDialog} to retrieve user input for sign up authentication
     */
    public void registrationAlertDialog()
    {
        // Always run AlertDialog code on UI thread (main thread) to avoid exceptions
        loginActivity.runOnUiThread(() ->
        {
            // Initializes AlertDialog builder variable and sets its title
            AlertDialog.Builder authenticationButton = new AlertDialog.Builder(loginActivity);
            authenticationButton.setTitle("Enter email authentication code:");
            // Initializes the AlertDialog view
            final View customLayout = loginActivity.getLayoutInflater().inflate(R.layout.account_verification_dialog, null);
            authenticationButton.setView(customLayout);

            // Creates confirm button and saves user input to the authentication string variable
            authenticationButton.setPositiveButton("Confirm", (dialog, which) ->
            {
                EditText confirmationCode = customLayout.findViewById(R.id.verification_edit_text);
                //authenticationString = confirmationCode.getText().toString();
                confirmConfirmationCode(confirmationCode.getText().toString());
            });

            // TODO: Code for resending authorization. AWS Amplify has no documentation for implementation in Java.
            authenticationButton.setNegativeButton("Resend", (dialog, which) ->
            {
                Toast.makeText(loginActivity, "Resend button clicked!", Toast.LENGTH_SHORT).show();
            });

            // Creates cancel button on Alert Dialog; Does not need an implementation
            authenticationButton.setNeutralButton("Cancel", (dialog, which) ->
            {
                // TODO: Remove toast
                Toast.makeText(loginActivity, "Cancel button clicked!", Toast.LENGTH_SHORT).show();
            });

            // Creates and displays dialog from AlertDialog builder variable (authentication builder)
            AlertDialog dialog = authenticationButton.create();
            dialog.show();
        });
    }

    private void confirmConfirmationCode(String confirmationCode)
    {
        // Confirms the users account on our AWS Amplify deployment
        Amplify.Auth.confirmSignUp(
                signupEmail,
                confirmationCode,
                onSignUpSuccess,
                onSignUpError);
    }

    /**
     * Move from the current {@link LoginActivity} to the notes list activity ({@link NotesListActivity})
     * @param isSignUp Whether the user has just signed up in order to display a custom message to the user
     */
    private void goToNextActivity(boolean isSignUp)
    {
        if (isSignUp)
        {
            // Must show Toast on UI thread (main thread)
            loginActivity.runOnUiThread(() -> Toast.makeText(loginActivity,  "Thank you for signing up to" +
                    " Lumberjack Notes! Your account is now confirmed.", Toast.LENGTH_SHORT).show());

        }

        // Go to the notes list activity
        Intent intent = new Intent(loginActivity, NotesListActivity.class);
        loginActivity.startActivity(intent);

        // Finish the current activity and destroy it; no navigating backwards to this activity from the next activity
        loginActivity.finish();
    }

    private final Consumer<AuthSignInResult> onSignInSuccess = result ->
    {
        Log.i("onSignInSuccess", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete");
        Log.i("onSignInSuccess", result.toString());

        switch (result.getNextStep().getSignInStep())
        {
            case CONFIRM_SIGN_IN_WITH_SMS_MFA_CODE:
            case CONFIRM_SIGN_IN_WITH_CUSTOM_CHALLENGE:
            case CONFIRM_SIGN_IN_WITH_NEW_PASSWORD:
            case RESET_PASSWORD:
                break;
            case CONFIRM_SIGN_UP:
                registrationAlertDialog();
                break;
            case DONE:
                goToNextActivity(false);
                break;
        }
    };

    private final Consumer<AuthException> onSignInError = error ->
    {
        Log.e("onSignInError", error.toString());

        // TODO: Handle UserNotFoundException
        // TODO: Handle UserNotConfirmedException
    };

    private final Consumer<AuthSignUpResult> onSignUpSuccess = result ->
    {
        Log.i("onSignUpSuccess", result.toString());

        switch (result.getNextStep().getSignUpStep())
        {
            case CONFIRM_SIGN_UP_STEP:
                registrationAlertDialog();
                break;
            case DONE:
                goToNextActivity(true);
                break;
        }
    };

    private final Consumer<AuthException> onSignUpError = error ->
    {
        Log.e("onSignUpError", error.toString());
    };
}