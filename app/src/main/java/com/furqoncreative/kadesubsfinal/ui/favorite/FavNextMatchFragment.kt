package com.furqoncreative.kadesubsfinal.ui.favorite


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.furqoncreative.kadesubsfinal.R
import com.furqoncreative.kadesubsfinal.data.local.MatchFavoriteDB
import com.furqoncreative.kadesubsfinal.data.local.matchdatabase
import com.furqoncreative.kadesubsfinal.data.repository.TeamRepository
import com.furqoncreative.kadesubsfinal.ui.match.DetailMatchActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout


class FavNextMatchFragment : Fragment(), AnkoComponent<Context> {
    private var matchFavoriteDB: MutableList<MatchFavoriteDB> = mutableListOf()
    private lateinit var adapter: FavoriteMatchAdapter
    private lateinit var listTeam: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var emptyLayout: LinearLayout

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavoriteMatchAdapter(matchFavoriteDB, TeamRepository()) {
            context?.startActivity<DetailMatchActivity>("id" to "${it.macthId}")
        }

        listTeam.adapter = adapter
        swipeRefresh.onRefresh {
            showFavorite()
        }
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    private fun showFavorite() {
        matchFavoriteDB.clear()
        context?.matchdatabase?.use {
            swipeRefresh.isRefreshing = false
            val result = select(MatchFavoriteDB.TABLE_FAVORITE).whereArgs(
                "(CATEGORY = {category})",
                "category" to "NEXT"
            )
            val favorite = result.parseList(classParser<MatchFavoriteDB>())
            matchFavoriteDB.addAll(favorite)
            if (favorite.isEmpty()) {
                listTeam.visibility = View.GONE
                emptyLayout.visibility = View.VISIBLE

            } else {
                listTeam.visibility = View.VISIBLE
                emptyLayout.visibility = View.GONE
            }
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    @SuppressLint("SetTextI18n")
    override fun createView(ui: AnkoContext<Context>): View = with(ui) {

        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
                )

                linearLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listTeam = recyclerView {
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
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
                            text = "There is no Favorited"

                        }.lparams {
                            topMargin = dip(16)
                        }
                    }
                }
            }


        }
    }


}
