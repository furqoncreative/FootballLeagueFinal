package com.furqoncreative.kadesubsfinal.view

import com.furqoncreative.kadesubsfinal.data.repository.RepositoryCallback
import com.furqoncreative.kadesubsfinal.model.MatchResponse

interface MatchView : RepositoryCallback<MatchResponse> {
    fun showLoading()
    fun hideLoading()
    fun showEmptyData()
}