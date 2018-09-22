package com.example.asus.footballschedule.test

import android.support.test.espresso.IdlingResource
import java.lang.IllegalArgumentException
import java.util.concurrent.atomic.AtomicInteger

class TestIdlingRes(private var resourceName : String) : IdlingResource{
    private val counter : AtomicInteger
    private var resourceCallback: IdlingResource.ResourceCallback? = null

    init{
        resourceName = checkNotNull(resourceName)
        counter = AtomicInteger(0);
    }
    override fun getName(): String {
        return "jancok"
    }

    override fun isIdleNow(): Boolean {
        return counter.get() == 0
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.resourceCallback = callback
    }

    fun increment(){
        counter.getAndDecrement()
    }

    fun decrement(){
        var counterVal = counter.decrementAndGet()
        if(counterVal == 0){
            if(null != resourceCallback){
                resourceCallback!!.onTransitionToIdle()
            }
        }

        if(counterVal < 0){
            throw IllegalArgumentException("Counter Has Been Corupted")
        }
    }

}