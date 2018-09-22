package com.example.asus.footballschedule.test

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.asus.footballschedule.R
import com.example.asus.footballschedule.home.HomeActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest{
    @Rule
    @JvmField var activityRule = ActivityTestRule(HomeActivity::class.java)

    /**
     * Skenario Instrumentasi
     * 1. Memastikan view dengan text "Everton" ditampilkan
     * 2. Memberi tindakan klik pada view dengan text "Everton"
     * 3. Memberi tindakan klik pada menu add_favorite
     * 4. Kembali ke halaman sebelumnya
     * 5. Memberi tindakan klik pada menu favorite
     * 6. Memastikan view dengan text "Everton" ditampilkan
     * 7. Memberi tindakan klik pada view dengan text "Everton"
     * 8. Memberi tindakan klik padamenu added_to_favorite
     * 9. Kembali ke halaman sebelumnya
     *
     */
    @Before
    fun registerIdlingResource(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource())
    }

    @After
    fun unregisterIdlingResource(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource())
    }
    @Test
    fun testHomeBehaviour(){
        Thread.sleep(2000)
        onView(withText("Everton")).check(matches(isDisplayed()))
        onView(withText("Everton")).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.add_to_favorite)).perform(click())
        //onView(withId(android.R.id.home)).perform(click())
        Espresso.pressBack()
        onView(withId(R.id.favorite)).perform(click())
        onView(withText("Everton")).check(matches(isDisplayed()))
        onView(withText("Everton")).perform(click())
        onView(withId(R.id.add_to_favorite)).perform(click())
        //onView(withId(android.R.id.home)).perform(click())
        Espresso.pressBack()
    }
}
