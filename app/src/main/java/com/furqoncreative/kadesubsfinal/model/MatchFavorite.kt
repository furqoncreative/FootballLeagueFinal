package com.furqoncreative.kadesubsfinal.model


import com.google.gson.annotations.SerializedName

data class MatchFavorite(

    @SerializedName("idEvent")
    val idEvent: String,
    @SerializedName("strDate")
    val strDate: String,
    @SerializedName("strTime")
    val strTime: String,
    @SerializedName("strLeague")
    val strLeague: String,
    @SerializedName("strHomeTeam")
    val strHomeTeam: String,
    @SerializedName("strAwayTeam")
    val strAwayTeam: String,
    @SerializedName("idHomeTeam")
    val idHomeTeam: String,
    @SerializedName("idAwayTeam")
    val idAwayTeam: String,
    @SerializedName("intHomeScore")
    val intHomeScore: String,
    @SerializedName("intAwayScore")
    val intAwayScore: String
)
