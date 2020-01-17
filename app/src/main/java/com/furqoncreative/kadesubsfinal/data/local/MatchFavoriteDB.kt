package com.furqoncreative.kadesubsfinal.data.local


data class MatchFavoriteDB(
    val id: Long?,
    val macthId: String?,
    val macthDate: String?,
    val macthTime: String?,
    val leagueName: String?,
    val homeName: String?,
    val awayName: String?,
    val homeBadge: String?,
    val awayBadge: String?,
    val homeScore: String?,
    val awayScore: String?,
    val category: String?
) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_MATCH_FAVORITE"
        const val ID: String = "ID_"
        const val MATCH_ID: String = "MATCH_ID"
        const val MATCH_DATE: String = "MATCH_DATE"
        const val MATCH_TIME: String = "MATCH_TIME"
        const val LEAGUE_NAME: String = "LEAGUE_NAME"
        const val HOME_TEAM_NAME: String = "HOME_TEAM_NAME"
        const val AWAY_TEAM_NAME: String = "AWAY_TEAM_NAME"
        const val HOME_TEAM_BADGE: String = "HOME_TEAM_BADGE"
        const val AWAY_TEAM_BADGE: String = "AWAY_TEAM_BADGE"
        const val HOME_TEAM_SCORE: String = "HOME_TEAM_SCORE"
        const val AWAY_TEAM_SCORE: String = "AWAY_TEAM_SCORE"
        const val CATEGORY: String = "CATEGORY"
    }
}