package com.example.asus.footballschedule.favorite

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.asus.footballschedule.R
import com.example.asus.footballschedule.db.Favorite
import org.jetbrains.anko.find


class FavoriteAdapter(private val favorite: List<Favorite>, private val listener :(Favorite)-> Unit)
    : RecyclerView.Adapter<FavoriteViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        Log.i("TEST VIEW HOLDE", "HMMM")
        return FavoriteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.schedule_list, parent, false))
    }

    override fun getItemCount() = favorite.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

}


class FavoriteViewHolder(view : View) : RecyclerView.ViewHolder(view){

    private val homeTeam : TextView = view.find(R.id.tv_strhome)
    private val homeScore : TextView = view.find(R.id.tv_homescore)
    private val awayTeam : TextView = view.find(R.id.tv_straway)
    private val awayScore : TextView = view.find(R.id.tv_awayscore)
    private val eventDate : TextView = view.find(R.id.tv_date_event)

    fun bindItem(favorite: Favorite, listener: (Favorite) -> Unit){
        homeTeam.text = favorite.homeName
        if(favorite.homeScore.equals("null") && favorite.awayScore.equals("null")){
            homeScore.text = " "
            awayScore.text = " "
        }
        else{
            homeScore.text = favorite.homeScore
            awayScore.text = favorite.awayScore
        }
        awayTeam.text = favorite.awayName
        eventDate.text = favorite.eventDate
        itemView.setOnClickListener {
            listener(favorite)
        }
    }
}