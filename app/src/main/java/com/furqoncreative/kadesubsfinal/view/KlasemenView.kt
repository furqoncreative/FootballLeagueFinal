package com.furqoncreative.kadesubsfinal.view

import com.furqoncreative.kadesubsfinal.data.repository.RepositoryCallback
import com.furqoncreative.kadesubsfinal.model.KlasemenResponse

interface KlasemenView : RepositoryCallback<KlasemenResponse> {
    fun showLoading()
    fun hideLoading()
    fun showEmptyData()
}