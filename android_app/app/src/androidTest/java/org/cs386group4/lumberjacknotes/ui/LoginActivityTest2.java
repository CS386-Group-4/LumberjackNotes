package org.cs386group4.lumberjacknotes.ui;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.cs386group4.lumberjacknotes.R;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest2
{

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void loginActivityTest2()
    {
        ViewInteraction editText = onView(
                allOf(withId(R.id.email_field), withText("E-mail"),
                        withParent(withParent(withId(R.id.email_field_container))),
                        isDisplayed()));
        editText.check(matches(isDisplayed()));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.password_field), withText("Password"),
                        withParent(withParent(withId(R.id.password_field_container))),
                        isDisplayed()));
        editText2.check(matches(isDisplayed()));

        ViewInteraction button = onView(
                allOf(withId(R.id.login_button), withText("Login"),
                        withParent(allOf(withId(R.id.login_container),
                                withParent(withId(R.id.login_root)))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.register_button), withText("Sign Up"),
                        withParent(allOf(withId(R.id.login_container),
                                withParent(withId(R.id.login_root)))),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.email_field),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.email_field_container),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText.perform(replaceText("a"), closeSoftKeyboard());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.password_field),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.password_field_container),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText2.perform(replaceText("d"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.register_button), withText("Sign Up"),
                        childAtPosition(
                                allOf(withId(R.id.login_container),
                                        childAtPosition(
                                                withId(R.id.login_root),
                                                0)),
                                8),
                        isDisplayed()));
        materialButton.perform(click());

        pressBack();

        pressBack();
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position)
    {

        return new TypeSafeMatcher<View>()
        {
            @Override
            public void describeTo(Description description)
            {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view)
            {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
