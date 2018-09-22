package com.example.asus.footballschedule.detail


interface DetailView {
    @Synchronized
    fun showData(urlImage : String, idData : Int) {
    }
}