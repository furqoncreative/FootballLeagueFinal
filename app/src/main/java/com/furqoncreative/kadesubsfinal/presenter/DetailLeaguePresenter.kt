package com.furqoncreative.kadesubsfinal.presenter

import com.furqoncreative.kadesubsfinal.data.repository.DetailLeagueRepository
import com.furqoncreative.kadesubsfinal.data.repository.RepositoryCallback
import com.furqoncreative.kadesubsfinal.model.LeagueResponse
import com.furqoncreative.kadesubsfinal.view.DetailLeagueView

class DetailLeaguePresenter(
    private val view: DetailLeagueView,
    private val appRepository: DetailLeagueRepository
) {


    fun getDetailLeague(id: String) {
        view.showLoading()
        appRepository.getDetailLeague(id, object : RepositoryCallback<LeagueResponse?> {
            override fun onDataLoaded(data: LeagueResponse?) {
                if (data?.leagues != null) {
                    view.onDataLoaded(data)
                    view.hideLoading()
                }
            }

            override fun onDataError() {
                view.onDataError()
            }

        })
    }

}