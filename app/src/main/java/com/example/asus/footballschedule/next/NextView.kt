package com.example.asus.footballschedule.next

import com.example.asus.footballschedule.Presenter
import com.example.asus.footballschedule.model.Schedule

interface NextView : Presenter{
    fun showSchedule(data : List<Schedule>)
}