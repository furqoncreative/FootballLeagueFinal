package com.furqoncreative.kadesubsfinal.data.repository


import com.furqoncreative.kadesubsfinal.data.network.ApiRepository
import com.furqoncreative.kadesubsfinal.data.network.ApiServices
import com.furqoncreative.kadesubsfinal.model.MatchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchRepository {

    fun getNextMatch(id: String, callback: RepositoryCallback<MatchResponse?>) {
        ApiRepository.createService(ApiServices::class.java)
            .getNextMatch(id)
            .enqueue(object : Callback<MatchResponse?> {
                override fun onFailure(call: Call<MatchResponse?>?, t: Throwable?) {
                    callback.onDataError()
                }

                override fun onResponse(
                    call: Call<MatchResponse?>?,
                    response: Response<MatchResponse?>?
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

    fun getPrevMatch(id: String, callback: RepositoryCallback<MatchResponse?>) {
        ApiRepository.createService(ApiServices::class.java)
            .getPrevMatch(id)
            .enqueue(object : Callback<MatchResponse?> {
                override fun onFailure(call: Call<MatchResponse?>?, t: Throwable?) {
                    callback.onDataError()
                }

                override fun onResponse(
                    call: Call<MatchResponse?>?,
                    response: Response<MatchResponse?>?
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

    fun getDetailMatch(id: String, callback: RepositoryCallback<MatchResponse?>) {
        ApiRepository.createService(ApiServices::class.java)
            .getDetailMatch(id)
            .enqueue(object : Callback<MatchResponse?> {
                override fun onFailure(call: Call<MatchResponse?>?, t: Throwable?) {
                    callback.onDataError()
                }

                override fun onResponse(
                    call: Call<MatchResponse?>?,
                    response: Response<MatchResponse?>?
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