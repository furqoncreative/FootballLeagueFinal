package com.furqoncreative.kadesubsfinal.data.repository


import com.furqoncreative.kadesubsfinal.data.network.ApiRepository
import com.furqoncreative.kadesubsfinal.data.network.ApiServices
import com.furqoncreative.kadesubsfinal.model.LeagueResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailLeagueRepository {

    fun getDetailLeague(id: String, callback: RepositoryCallback<LeagueResponse?>) {
        ApiRepository.createService(ApiServices::class.java)
            .getDetailLeague(id)
            .enqueue(object : Callback<LeagueResponse?> {
                override fun onFailure(call: Call<LeagueResponse?>?, t: Throwable?) {
                    callback.onDataError()
                }

                override fun onResponse(
                    call: Call<LeagueResponse?>?,
                    response: Response<LeagueResponse?>?
                ) {
                    response?.let {
                        if (it.isSuccessful) {
                            callback.onDataLoaded(it.body())
                        } else {
                            callback.onDataError()
                        }
                    }
                }
            })
    }
}