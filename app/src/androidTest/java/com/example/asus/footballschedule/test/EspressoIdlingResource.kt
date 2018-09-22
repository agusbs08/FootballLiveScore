package com.example.asus.footballschedule.test

object EspressoIdlingResource{
    private val resource =  "GLOBAL"
    private val mCountingIdlingResource = TestIdlingRes(resource)

    fun increment(){
        mCountingIdlingResource.increment()
    }

    fun decrement(){
        mCountingIdlingResource.decrement()
    }

    fun getIdlingResource(): TestIdlingRes = mCountingIdlingResource
}