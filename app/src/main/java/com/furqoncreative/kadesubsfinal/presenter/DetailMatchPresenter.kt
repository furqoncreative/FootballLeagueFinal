package com.furqoncreative.kadesubsfinal.presenter

import com.furqoncreative.kadesubsfinal.data.repository.MatchRepository
import com.furqoncreative.kadesubsfinal.data.repository.RepositoryCallback
import com.furqoncreative.kadesubsfinal.data.repository.TeamRepository
import com.furqoncreative.kadesubsfinal.model.MatchResponse
import com.furqoncreative.kadesubsfinal.model.TeamResponse
import com.furqoncreative.kadesubsfinal.view.DetailMatchView

class DetailMatchPresenter(
    private val view: DetailMatchView,
    private val appRepository: TeamRepository,
    private val matchRepository: MatchRepository

) {

    fun getDetailMatch(id: String) {
        view.showLoading()
        matchRepository.getDetailMatch(id, object : RepositoryCallback<MatchResponse?> {
            override fun onDataLoaded(data: MatchResponse?) {
                if (data?.events != null) {
                    view.onDataLoaded(data)
                    view.hideLoading()
                }
            }

            override fun onDataError() {
                view.onDataError()
            }

        })
    }

    fun getHomeBadge(id: String) {
        appRepository.getDetailTeam(id, object : RepositoryCallback<TeamResponse?> {
            override fun onDataLoaded(data: TeamResponse?) {
                if (data?.teams != null) {
                    view.setHomeBadge(data.teams)
                }
            }

            override fun onDataError() {
                view.onDataError()
            }

        })
    }

    fun getAwayBadge(id: String) {
        appRepository.getDetailTeam(id, object : RepositoryCallback<TeamResponse?> {
            override fun onDataLoaded(data: TeamResponse?) {
                if (data?.teams != null) {
                    view.setAwayBadge(data.teams)
                }
            }

            override fun onDataError() {
                view.onDataError()
            }

        })
    }
}