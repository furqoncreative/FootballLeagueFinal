package com.furqoncreative.kadesubsfinal.data.repository

interface RepositoryCallback<T> {
    fun onDataLoaded(data: T?)
    fun onDataError()
}