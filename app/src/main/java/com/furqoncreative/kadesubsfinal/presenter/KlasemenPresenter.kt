package com.furqoncreative.kadesubsfinal.presenter


import com.furqoncreative.kadesubsfinal.data.repository.KlasemenRepository
import com.furqoncreative.kadesubsfinal.data.repository.RepositoryCallback
import com.furqoncreative.kadesubsfinal.model.KlasemenResponse
import com.furqoncreative.kadesubsfinal.view.KlasemenView

class KlasemenPresenter(
    private val view: KlasemenView,
    private val apiRepository: KlasemenRepository
) {

    fun getKlasemen(id: String) {
        view.showLoading()
        apiRepository.getKlasemen(id, object : RepositoryCallback<KlasemenResponse?> {
            override fun onDataLoaded(data: KlasemenResponse?) {
                if (data?.table != null) {
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