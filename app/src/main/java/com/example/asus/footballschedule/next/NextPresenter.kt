package com.example.asus.footballschedule.next

import android.util.Log
import com.example.asus.footballschedule.model.ScheduleResponse
import com.example.asus.footballschedule.retrofit.GetDataService
import com.example.asus.footballschedule.retrofit.RetrofitClientInstance
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

class NextPresenter(private val view: NextView){
    private var result : Call<ScheduleResponse>
    //private var mIdlingRes
    //CountingIdlingResource mIdlingRes = new CountingIdlingResource("name");

    init {
        val retrofit = RetrofitClientInstance.getInstance()
        val service = retrofit.create(GetDataService::class.java)
        this.result = service.getAllNextSchedule()
        //mIdlingRes = CountingIdlingResource("unitTest")
    }

    fun getNextSchedule(){
        view.showLoading()
        result.enqueue(object : Callback<ScheduleResponse>{
            override fun onResponse(call: Call<ScheduleResponse>, response: Response<ScheduleResponse>) {
                val schedulese = response!!.body()!!.schedulese
                view.hideLoading()
                view.showSchedule(schedulese)
            }

            override fun onFailure(call: Call<ScheduleResponse>, t: Throwable) {
                Log.e("getData", "Error")
            }
        })


    }

    fun setScheduleResponse(scheduleResponse : Call<ScheduleResponse>){
        result = scheduleResponse
    }
}