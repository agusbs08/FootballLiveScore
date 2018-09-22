package com.example.asus.footballschedule.model

import com.google.gson.annotations.SerializedName

data class ScheduleResponse(
        @SerializedName("events")
        val schedulese: List<Schedule>)