package com.example.asus.footballschedule.retrofit

import com.example.asus.footballschedule.model.ScheduleResponse
import com.example.asus.footballschedule.model.TeamResponse
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query


interface GetDataService{
    @GET("/api/v1/json/1/eventspastleague.php?id=4328")
    fun getAllPastSchedule(): Call<ScheduleResponse>

    @GET("/api/v1/json/1/eventsnextleague.php?id=4328")
    fun getAllNextSchedule(): Call<ScheduleResponse>

    @GET("/api/v1/json/1/searchteams.php")
    fun getTeam(@Query("t") t : String) : Call<TeamResponse>
}