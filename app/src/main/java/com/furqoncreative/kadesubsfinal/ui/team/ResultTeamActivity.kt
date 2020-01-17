package com.furqoncreative.kadesubsfinal.ui.team

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
import com.furqoncreative.kadesubsfinal.data.repository.TeamRepository
import com.furqoncreative.kadesubsfinal.model.Team
import com.furqoncreative.kadesubsfinal.model.TeamResponse
import com.furqoncreative.kadesubsfinal.presenter.TeamPresenter
import com.furqoncreative.kadesubsfinal.view.TeamView
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class ResultTeamActivity : AppCompatActivity(), TeamView {
    companion object {
        const val QUERY = "query"
    }

    private var query: String = ""

    private var items: MutableList<Team> = mutableListOf()
    private lateinit var progressBar: ProgressBar
    private lateinit var matchPresenter: TeamPresenter
    private lateinit var adapter: TeamAdapter
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
                        layoutManager = LinearLayoutManager(this@ResultTeamActivity)
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

        adapter =
            TeamAdapter(items) {
                startActivity<DetailTeamActivity>(
                    DetailTeamActivity.ID to it.teamId
                )
            }

        listItem.adapter = adapter

        matchPresenter = TeamPresenter(this, TeamRepository())

        matchPresenter.getResullTeam(query)

        swipeRefresh.onRefresh {
            matchPresenter.getResullTeam(query)

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

    override fun onDataLoaded(data: TeamResponse?) {
        val result: MutableList<Team> = mutableListOf()
        for (i in data!!.teams.indices) {
            if (data.teams[i].teamSport == ("Soccer")) {
                result.add(data.teams[i])
            }
        }
        if (result.size != 0) {
            emptyLayout.visibility = View.GONE
            swipeRefresh.isRefreshing = false
            items.clear()
            items.addAll(result)
            adapter.notifyDataSetChanged()
        } else {
            showEmptyData()
        }

    }

    override fun onDataError() {
    }

}
