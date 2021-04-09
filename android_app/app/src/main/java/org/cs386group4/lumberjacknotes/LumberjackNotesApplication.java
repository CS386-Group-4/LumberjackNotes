package org.cs386group4.lumberjacknotes;

import android.app.Application;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;

public class LumberjackNotesApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        initAmplify();
    }

    /**
     * Initialize AWS Amplify framework (Cognito)
     */
    private void initAmplify()
    {
        try
        {
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.configure(getApplicationContext());
        }
        catch (AmplifyException exc)
        {
            exc.printStackTrace();
        }
    }
}
