package com.example.asus.footballschedule.past

import com.example.asus.footballschedule.Presenter
import com.example.asus.footballschedule.model.Schedule

interface PastView : Presenter{
    fun showSchedule(data : List<Schedule>)
}