package com.furqoncreative.kadesubsfinal.model


import com.google.gson.annotations.SerializedName

data class League(
    @SerializedName("dateFirstEvent")
    val dateFirstEvent: String,
    @SerializedName("idLeague")
    val idLeague: String,
    @SerializedName("intFormedYear")
    val intFormedYear: String,
    @SerializedName("strBadge")
    val strBadge: String,
    @SerializedName("strCountry")
    val strCountry: String,
    @SerializedName("strGender")
    val strGender: String,
    @SerializedName("strLeague")
    val strLeague: String,
    @SerializedName("strTrophy")
    val strTrophy: String,
    @SerializedName("strWebsite")
    val strWebsite: String
)