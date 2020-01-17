package com.furqoncreative.kadesubsfinal.view

import com.furqoncreative.kadesubsfinal.data.repository.RepositoryCallback
import com.furqoncreative.kadesubsfinal.model.LeagueResponse

interface DetailLeagueView : RepositoryCallback<LeagueResponse> {
    fun showLoading()
    fun hideLoading()
}