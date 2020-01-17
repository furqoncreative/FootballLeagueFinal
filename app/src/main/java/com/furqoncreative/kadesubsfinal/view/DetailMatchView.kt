package com.furqoncreative.kadesubsfinal.view

import com.furqoncreative.kadesubsfinal.data.repository.RepositoryCallback
import com.furqoncreative.kadesubsfinal.model.MatchResponse
import com.furqoncreative.kadesubsfinal.model.Team

interface DetailMatchView : RepositoryCallback<MatchResponse> {
    fun showLoading()
    fun hideLoading()
    fun setHomeBadge(data: List<Team>)
    fun setAwayBadge(data: List<Team>)
}