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
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TakingNotes_Acceptance_Test
{
    /**
     * Login Credentials for the LoginScreen_Acceptance_Test_Positive
     */
    String testingEmail = "aem538@nau.edu";
    String testingPassword = "Test123!";

    @Rule
    public ActivityScenarioRule<LoginActivity> mActivityTestRule = new ActivityScenarioRule<>(LoginActivity.class);

    @Before
    public void setUp()
    {
        /**
         * Action: Enters credentials
         */
        enterLoginCredentials(testingEmail, testingPassword);

        /**
         * Action: Taps the Login Button
         */
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.login_button), isDisplayed()));
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(click());
    }

    @Test
    public void takingNotes_Acceptance_Test()
    {
        /**
         * Action: Taps the notes_list object
         */
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.notes_list)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        /**
         * Action: Replaces Default Message with "Testing"
         */
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.notetaking_editor), withText("First note\nStart typing now...")));
        appCompatEditText.check(matches(isDisplayed()));
        appCompatEditText.perform(replaceText("Testing"));
        pressBack();

        /**
         * Assertion: Checks if the text outside of notetaking_editor(note_content) was correctly replaced to "Testing"
         */
        ViewInteraction textView = onView(allOf(withId(R.id.note_content), withText("Testing"), isDisplayed()));
        textView.check(matches(withText("Testing")));

        /**
         * Action: Signs Out
         */
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.notes_list_sign_out), isDisplayed()));
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(click());
    }

    /**
     * This method uses the parameters Email and Password to enter the login Credentials into their respective
     * fields(if these are visible at the moment)
     */
    private static void enterLoginCredentials(String Email, String Password)
        {

            ViewInteraction textInputEditText = onView(
                    allOf(withId(R.id.email_field), isDisplayed()));
            textInputEditText.check(matches(isDisplayed()));
            textInputEditText.perform(replaceText(Email), closeSoftKeyboard());

            ViewInteraction textInputEditText2 = onView(
                    allOf(withId(R.id.password_field), isDisplayed()));
            textInputEditText2.check(matches(isDisplayed()));
            textInputEditText2.perform(replaceText(Password), closeSoftKeyboard());
        }
}
