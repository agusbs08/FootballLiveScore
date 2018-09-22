package com.example.asus.footballschedule.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.asus.footballschedule.R
import com.example.asus.footballschedule.model.Schedule
import org.jetbrains.anko.*

class RecyclerViewAdapter (private val schedulese: List<Schedule>, private val listener: (Schedule) -> Unit)
    : RecyclerView.Adapter<ScheduleViewHolder>() {

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        return holder.bindItem(schedulese[position], listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        return ScheduleViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.schedule_list, parent, false))
    }

    override fun getItemCount(): Int = schedulese.size
}

class ScheduleViewHolder(view: View) : RecyclerView.ViewHolder(view){
    private val homeTeam : TextView = view.find(R.id.tv_strhome)
    private val homeScore : TextView = view.find(R.id.tv_homescore)
    private val awayTeam : TextView = view.find(R.id.tv_straway)
    private val awayScore : TextView = view.find(R.id.tv_awayscore)
    private val eventDate : TextView = view.find(R.id.tv_date_event)

    fun bindItem(schedule : Schedule, listener : (Schedule) -> Unit){
        homeTeam.text = schedule.homeTeamName
        homeScore.text = schedule.homeScore
        awayTeam.text = schedule.awayTeamName
        awayScore.text = schedule.awayScore
        eventDate.text = schedule.eventDate
        itemView.setOnClickListener {
            listener(schedule)
        }
    }

}