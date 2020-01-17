package com.furqoncreative.kadesubsfinal.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.furqoncreative.kadesubsfinal.R
import com.furqoncreative.kadesubsfinal.data.repository.SearchMatchRepository
import com.furqoncreative.kadesubsfinal.data.repository.TeamRepository
import com.furqoncreative.kadesubsfinal.model.Search
import com.furqoncreative.kadesubsfinal.model.SearchResponse
import com.furqoncreative.kadesubsfinal.presenter.SearchMatchPresenter
import com.furqoncreative.kadesubsfinal.ui.match.DetailMatchActivity
import com.furqoncreative.kadesubsfinal.view.SearchView
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class ResultMatchActivity : AppCompatActivity(), SearchView {
    companion object {
        const val QUERY = "query"
    }

    private var query: String = ""

    private var items: MutableList<Search> = mutableListOf()
    private lateinit var progressBar: ProgressBar
    private lateinit var matchPresenter: SearchMatchPresenter
    private lateinit var matchAdapter: SearchMatchAdapter
    private lateinit var listItem: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var emptyLayout: LinearLayout


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent != null) {
            query = intent.getStringExtra(QUERY)
        }
        val actionbar = supportActionBar
        actionbar!!.title = "Result for : $query"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(8)
            leftPadding = dip(8)
            rightPadding = dip(8)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
                )

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listItem = recyclerView {
                        id = R.id.list_match
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(this@ResultMatchActivity)
                    }

                    progressBar = progressBar {
                        id = R.id.progressbar

                    }.lparams {
                        centerHorizontally()
                    }

                    emptyLayout = linearLayout {
                        lparams(width = matchParent, height = matchParent)
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER
                        visibility = View.GONE
                        id = R.id.empty_layout


                        imageView {
                            imageResource = (R.drawable.ic_inbox_black_24dp)
                        }.lparams {
                            height = dip(100)
                            width = dip(100)
                        }


                        textView {
                            textSize = 14f
                            gravity = Gravity.CENTER
                            text = "Result not found"

                        }.lparams {
                            topMargin = dip(16)
                        }
                    }


                }
            }
        }

        matchAdapter = SearchMatchAdapter(
            items,
            TeamRepository()
        ) {
            startActivity<DetailMatchActivity>(
                DetailMatchActivity.ID to it.idEvent
            )
        }

        listItem.adapter = matchAdapter

        matchPresenter = SearchMatchPresenter(this, SearchMatchRepository())

        matchPresenter.getResultSearch(query)

        swipeRefresh.onRefresh {
            matchPresenter.getResultSearch(query)

        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
        listItem.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
        listItem.visibility = View.VISIBLE
    }

    override fun showEmptyData() {
        swipeRefresh.isRefreshing = false
        listItem.visibility = View.GONE
        emptyLayout.visibility = View.VISIBLE
    }

    override fun onDataLoaded(data: SearchResponse?) {
        val result: MutableList<Search> = mutableListOf()
        for (i in data!!.event.indices) {
            if (data.event[i].strSport == ("Soccer")) {
                result.add(data.event[i])
            }
        }
        if (result.size != 0) {
            emptyLayout.visibility = View.GONE
            swipeRefresh.isRefreshing = false
            items.clear()
            items.addAll(result)
            matchAdapter.notifyDataSetChanged()
        } else {
            showEmptyData()
        }

    }

    override fun onDataError() {
    }

}
