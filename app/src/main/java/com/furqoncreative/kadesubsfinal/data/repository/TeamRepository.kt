package com.furqoncreative.kadesubsfinal.data.repository


import com.furqoncreative.kadesubsfinal.data.network.ApiRepository
import com.furqoncreative.kadesubsfinal.data.network.ApiServices
import com.furqoncreative.kadesubsfinal.model.TeamResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamRepository {

    fun getDetailTeam(id: String, callback: RepositoryCallback<TeamResponse?>) {
        ApiRepository.createService(ApiServices::class.java)
            .getDetailTeam(id)
            .enqueue(object : Callback<TeamResponse?> {
                override fun onFailure(call: Call<TeamResponse?>?, t: Throwable?) {
                    callback.onDataError()
                }

                override fun onResponse(
                    call: Call<TeamResponse?>?,
                    response: Response<TeamResponse?>?
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

    fun getListTeam(id: String, callback: RepositoryCallback<TeamResponse?>) {
        ApiRepository.createService(ApiServices::class.java)
            .getListTeam(id)
            .enqueue(object : Callback<TeamResponse?> {
                override fun onFailure(call: Call<TeamResponse?>?, t: Throwable?) {
                    callback.onDataError()
                }

                override fun onResponse(
                    call: Call<TeamResponse?>?,
                    response: Response<TeamResponse?>?
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

    fun getResultTeam(id: String, callback: RepositoryCallback<TeamResponse?>) {
        ApiRepository.createService(ApiServices::class.java)
            .getResultTeam(id)
            .enqueue(object : Callback<TeamResponse?> {
                override fun onFailure(call: Call<TeamResponse?>?, t: Throwable?) {
                    callback.onDataError()
                }

                override fun onResponse(
                    call: Call<TeamResponse?>?,
                    response: Response<TeamResponse?>?
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