package org.cs386group4.lumberjacknotes.ui;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.cs386group4.lumberjacknotes.R;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginScreen_Acceptance_Test_Positive
{
    /**
     * Login Credentials
     */
    String testingEmail = "aem538@nau.edu";
    String testingPassword = "Test123!";

    @Rule
    public ActivityScenarioRule<LoginActivity> mActivityTestRule = new ActivityScenarioRule<>(LoginActivity.class);

    @Before
    public void setUp()
    {
        // Action: Enters credentials
        enterLoginCredentials(testingEmail, testingPassword);

        // Action: Taps the Login Button
        ViewInteraction materialButton = onView(allOf(withId(R.id.login_button), isDisplayed()));
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(click());
    }

    @Test
    public void loginScreen_Acceptance_Test()
    {
        // Assertion: Checks if the inside Title "LumberjackNotes" is being displayed
        ViewInteraction textView = onView(allOf(withText("Lumberjack Notes"), isDisplayed()));
        textView.check(matches(isDisplayed()));

        // Action: Signs Out
        signOut();
    }

    /**
     * This method uses the parameters Email and Password to enter the login Credentials into their respective
     * fields (if these are visible at the moment)
     */
    private static void enterLoginCredentials(String Email, String Password)
    {
        ViewInteraction textInputEditText = onView(allOf(withId(R.id.email_field), isDisplayed()));
        textInputEditText.check(matches(isDisplayed()));
        textInputEditText.perform(replaceText(Email), closeSoftKeyboard());

        ViewInteraction textInputEditText2 = onView(allOf(withId(R.id.password_field), isDisplayed()));
        textInputEditText2.check(matches(isDisplayed()));
        textInputEditText2.perform(replaceText(Password), closeSoftKeyboard());
    }

    private static void signOut()
    {
        ViewInteraction materialButton = onView(
                allOf(withContentDescription("More options"), isDisplayed()));
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.title), withText("Sign Out"),isDisplayed()));
        materialButton2.check(matches(isDisplayed()));
        materialButton2.perform(click());
    }

}