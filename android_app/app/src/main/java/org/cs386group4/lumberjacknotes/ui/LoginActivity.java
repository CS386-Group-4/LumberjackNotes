package org.cs386group4.lumberjacknotes.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import org.cs386group4.lumberjacknotes.R;
import org.cs386group4.lumberjacknotes.controllers.LoginController;

public class LoginActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Match status bar color to background if at least Android M
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.surface));

            // Set status bar to light mode if not in night mode
            if ((getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK)
                    != Configuration.UI_MODE_NIGHT_YES)
            {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }

        // Initialize login controller
        new LoginController(this);
    }
}