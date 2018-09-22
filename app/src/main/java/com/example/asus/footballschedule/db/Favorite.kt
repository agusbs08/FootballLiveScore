package com.example.asus.footballschedule.db

data class Favorite(val id: Long?, val eventId: String?, val homeName: String?, val awayName: String?
                    , val homeScore : String?, val awayScore : String?, val eventDate : String?) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val HOME_TEAM_NAME: String = "HOME_TEAM_NAME"
        const val AWAY_TEAM_NAME: String = "AWAY_TEAM_NAME"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val EVENT_DATE: String = "TEAM_DATE"
    }
}