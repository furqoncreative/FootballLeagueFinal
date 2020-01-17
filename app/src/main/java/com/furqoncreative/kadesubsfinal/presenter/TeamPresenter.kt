package com.furqoncreative.kadesubsfinal.presenter


import com.furqoncreative.kadesubsfinal.data.repository.RepositoryCallback
import com.furqoncreative.kadesubsfinal.data.repository.TeamRepository
import com.furqoncreative.kadesubsfinal.model.TeamResponse
import com.furqoncreative.kadesubsfinal.view.TeamView

class TeamPresenter(private val view: TeamView, private val apiRepository: TeamRepository) {

    fun getKListTeam(id: String) {
        view.showLoading()
        apiRepository.getListTeam(id, object : RepositoryCallback<TeamResponse?> {
            override fun onDataLoaded(data: TeamResponse?) {
                if (data?.teams != null) {
                    view.onDataLoaded(data)
                    view.hideLoading()
                } else {
                    view.hideLoading()
                    view.showEmptyData()
                }
            }

            override fun onDataError() {
                view.onDataError()
                view.hideLoading()
            }
        })
    }


    fun getDetailTeam(id: String) {
        view.showLoading()
        apiRepository.getDetailTeam(id, object : RepositoryCallback<TeamResponse?> {
            override fun onDataLoaded(data: TeamResponse?) {
                if (data?.teams != null) {
                    view.onDataLoaded(data)
                    view.hideLoading()
                } else {
                    view.hideLoading()
                    view.showEmptyData()
                }
            }

            override fun onDataError() {
                view.onDataError()
                view.hideLoading()
            }
        })
    }


    fun getResullTeam(id: String) {
        view.showLoading()
        apiRepository.getResultTeam(id, object : RepositoryCallback<TeamResponse?> {
            override fun onDataLoaded(data: TeamResponse?) {
                if (data?.teams != null) {
                    view.onDataLoaded(data)
                    view.hideLoading()
                } else {
                    view.hideLoading()
                    view.showEmptyData()
                }
            }

            override fun onDataError() {
                view.onDataError()
                view.hideLoading()
            }
        })
    }


}