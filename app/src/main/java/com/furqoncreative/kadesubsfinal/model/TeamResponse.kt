package com.furqoncreative.kadesubsfinal.model


import com.google.gson.annotations.SerializedName

data class TeamResponse(
    @SerializedName("teams")
    val teams: List<Team>
)