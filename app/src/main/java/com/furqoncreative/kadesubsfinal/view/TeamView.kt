package com.furqoncreative.kadesubsfinal.view

import com.furqoncreative.kadesubsfinal.data.repository.RepositoryCallback
import com.furqoncreative.kadesubsfinal.model.TeamResponse

interface TeamView : RepositoryCallback<TeamResponse> {
    fun showLoading()
    fun hideLoading()
    fun showEmptyData()
}