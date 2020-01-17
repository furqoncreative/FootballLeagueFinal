package com.furqoncreative.kadesubsfinal.model


import com.google.gson.annotations.SerializedName

data class MatchResponse(
    @SerializedName("events")
    val events: List<Match>
)