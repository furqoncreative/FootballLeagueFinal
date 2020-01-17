package com.furqoncreative.kadesubsfinal.model


import com.google.gson.annotations.SerializedName

data class KlasemenResponse(
    @SerializedName("table")
    val table: List<Klasemen>
)