package com.example.asus.footballschedule.model

import com.google.gson.annotations.SerializedName

data class Schedule (
        @SerializedName("idEvent")
        var eventId: String? = null,

        @SerializedName("strEvent")
        var eventName: String? = null,

        @SerializedName("dateEvent")
        var eventDate: String? = null,

        @SerializedName("strHomeTeam")
        var homeTeamName: String? = null,

        @SerializedName("strAwayTeam")
        var awayTeamName: String? = null,

        @SerializedName("intHomeScore")
        var homeScore: String? = null,

        @SerializedName("intAwayScore")
        var awayScore: String? = null,

        @SerializedName("strHomeGoalDetails")
        var homeGoalDetails: String? = null,

        @SerializedName("strAwayGoalDetails")
        var awayGoalDetails: String? = null,

        @SerializedName("strHomeLineupGoalkeeper")
        var homeGoalKeeper: String? = null,

        @SerializedName("strAwayLineupGoalkeeper")
        var AwayGoalKeeper: String? = null,

        @SerializedName("strHomeLineupDefense")
        var homeDefense: String? = null,

        @SerializedName("strAwayLineupDefense")
        var awayDefense: String? = null,

        @SerializedName("strHomeLineupMidfield")
        var homeMidfield: String? = null,

        @SerializedName("strAwayLineupMidfield")
        var awayMidfield: String? = null,

        @SerializedName("strHomeLineupForward")
        var homeForward: String? = null,

        @SerializedName("strAwayLineupForward")
        var awayForward: String? = null
)