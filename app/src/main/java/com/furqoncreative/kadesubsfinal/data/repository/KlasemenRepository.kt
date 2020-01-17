package com.furqoncreative.kadesubsfinal.data.repository


import com.furqoncreative.kadesubsfinal.data.network.ApiRepository
import com.furqoncreative.kadesubsfinal.data.network.ApiServices
import com.furqoncreative.kadesubsfinal.model.KlasemenResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KlasemenRepository {

    fun getKlasemen(id: String, callback: RepositoryCallback<KlasemenResponse?>) {
        ApiRepository.createService(ApiServices::class.java)
            .getKlasemen(id)
            .enqueue(object : Callback<KlasemenResponse?> {
                override fun onFailure(call: Call<KlasemenResponse?>?, t: Throwable?) {
                    callback.onDataError()
                }

                override fun onResponse(
                    call: Call<KlasemenResponse?>?,
                    response: Response<KlasemenResponse?>?
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