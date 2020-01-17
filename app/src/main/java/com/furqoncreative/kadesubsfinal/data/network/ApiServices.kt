package com.furqoncreative.kadesubsfinal.data.network

import com.furqoncreative.kadesubsfinal.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("lookupleague.php")
    fun getDetailLeague(@Query("id") id: String?): Call<LeagueResponse>

    @GET("lookupevent.php")
    fun getDetailMatch(@Query("id") id: String?): Call<MatchResponse>


    @GET("eventsnextleague.php")
    fun getNextMatch(@Query("id") id: String?): Call<MatchResponse>

    @GET("eventspastleague.php")
    fun getPrevMatch(@Query("id") id: String?): Call<MatchResponse>


    @GET("lookup_all_teams.php")
    fun getListTeam(@Query("id") query: String?): Call<TeamResponse>

    @GET("lookupteam.php")
    fun getDetailTeam(@Query("id") id: String?): Call<TeamResponse>

    @GET("lookuptable.php")
    fun getKlasemen(@Query("l") query: String?): Call<KlasemenResponse>

    @GET("searchevents.php")
    fun getResultMatch(@Query("e") query: String?): Call<SearchResponse>

    @GET("searchteams.php")
    fun getResultTeam(@Query("t") query: String?): Call<TeamResponse>

}