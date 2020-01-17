package com.furqoncreative.kadesubsfinal.data.repository


import com.furqoncreative.kadesubsfinal.data.network.ApiRepository
import com.furqoncreative.kadesubsfinal.data.network.ApiServices
import com.furqoncreative.kadesubsfinal.model.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchMatchRepository {

    fun getSearchResult(id: String, callback: RepositoryCallback<SearchResponse?>) {
        ApiRepository.createService(ApiServices::class.java)
            .getResultMatch(id)
            .enqueue(object : Callback<SearchResponse?> {
                override fun onFailure(call: Call<SearchResponse?>?, t: Throwable?) {
                    callback.onDataError()
                }

                override fun onResponse(
                    call: Call<SearchResponse?>?,
                    response: Response<SearchResponse?>?
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