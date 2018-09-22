package com.example.asus.footballschedule.detail

import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.asus.footballschedule.model.TeamResponse
import com.example.asus.footballschedule.retrofit.GetDataService
import kotlinx.android.synthetic.main.activity_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenter(private val view : DetailView, private val service : GetDataService,
                      private val bundle: Bundle){
    fun getData(){
        val home = service.getTeam(bundle.getString("homeTeamName"))
        home.enqueue(object : Callback<TeamResponse> {
            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                Log.e("team", "error get nya")
            }

            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                val team = response!!.body()!!.teams.get(0)
                view.showData(team.teamBadge.toString(), 1)
            }
        })

        val away = service.getTeam(bundle.getString("awayTeamName"))
        away.enqueue(object : Callback<TeamResponse> {
            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                Log.e("team", "error get nya")
            }

            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                val team = response!!.body()!!.teams.get(0)
                view.showData(team.teamBadge.toString(), 2)
            }
        })
    }
}