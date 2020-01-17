package com.furqoncreative.kadesubsfinal.model


import com.google.gson.annotations.SerializedName

data class LeagueResponse(
    @SerializedName("leagues")
    val leagues: List<League>
)