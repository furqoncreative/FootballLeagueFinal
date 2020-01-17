package com.furqoncreative.kadesubsfinal.ui.team


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
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


class TeamFragment : Fragment(), AnkoComponent<Context>, TeamView {

    private var items: MutableList<Team> = mutableListOf()
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: TeamPresenter
    private lateinit var adapter: TeamAdapter
    private lateinit var listItem: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private var leagueId: String = ""
    private lateinit var emptyLayout: LinearLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return createView(AnkoContext.Companion.create(requireContext()))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TeamAdapter(items) {
            context?.startActivity<DetailTeamActivity>(DetailTeamActivity.ID to it.teamId)
        }
        listItem.adapter = adapter

        presenter = TeamPresenter(this, TeamRepository())

        leagueId = activity!!.intent.extras!!.getString("id")!!
        presenter.getKListTeam(leagueId)

        swipeRefresh.onRefresh {
            presenter.getKListTeam(leagueId)

        }
    }

    @SuppressLint("SetTextI18n")
    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
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
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                    }.lparams {
                        centerHorizontally()
                    }

                    emptyLayout = linearLayout {
                        lparams(width = matchParent, height = matchParent)
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER
                        visibility = View.GONE


                        imageView {
                            imageResource = (R.drawable.ic_inbox_black_24dp)
                        }.lparams {
                            height = dip(100)
                            width = dip(100)
                        }


                        textView {
                            textSize = 14f
                            gravity = Gravity.CENTER
                            text = "Match not found"

                        }.lparams {
                            topMargin = dip(16)
                        }
                    }
                }
            }
        }
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
        listItem.visibility = View.INVISIBLE

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
        val team: List<Team> = data!!.teams
        emptyLayout.visibility = View.GONE
        swipeRefresh.isRefreshing = false
        items.clear()
        items.addAll(team)
        adapter.notifyDataSetChanged()
    }

    override fun onDataError() {
    }


}