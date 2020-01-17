package com.furqoncreative.kadesubsfinal.presenter

import com.furqoncreative.kadesubsfinal.data.repository.RepositoryCallback
import com.furqoncreative.kadesubsfinal.data.repository.SearchMatchRepository
import com.furqoncreative.kadesubsfinal.model.SearchResponse
import com.furqoncreative.kadesubsfinal.view.SearchView

class SearchMatchPresenter(
    private val view: SearchView,
    private val apiMatchRepository: SearchMatchRepository
) {


    fun getResultSearch(query: String) {
        view.showLoading()
        apiMatchRepository.getSearchResult(query, object : RepositoryCallback<SearchResponse?> {
            override fun onDataLoaded(data: SearchResponse?) {
                if (data?.event != null) {
                    view.onDataLoaded(data)
                    view.hideLoading()
                } else {
                    view.showEmptyData()
                    view.hideLoading()
                }
            }

            override fun onDataError() {
                view.onDataError()
            }
        })
    }

}