package com.furqoncreative.kadesubsfinal.view

import com.furqoncreative.kadesubsfinal.data.repository.RepositoryCallback
import com.furqoncreative.kadesubsfinal.model.SearchResponse

interface SearchView : RepositoryCallback<SearchResponse> {
    fun showLoading()
    fun hideLoading()
    fun showEmptyData()
}