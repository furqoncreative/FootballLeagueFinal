package com.furqoncreative.kadesubsfinal.ui.klasemen


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.furqoncreative.kadesubsfinal.R
import com.furqoncreative.kadesubsfinal.data.repository.KlasemenRepository
import com.furqoncreative.kadesubsfinal.data.repository.TeamRepository
import com.furqoncreative.kadesubsfinal.model.Klasemen
import com.furqoncreative.kadesubsfinal.model.KlasemenResponse
import com.furqoncreative.kadesubsfinal.presenter.KlasemenPresenter
import com.furqoncreative.kadesubsfinal.view.KlasemenView
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout


class KlasemenFragment : Fragment(), AnkoComponent<Context>, KlasemenView {

    private var items: MutableList<Klasemen> = mutableListOf()
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: KlasemenPresenter
    private lateinit var adapter: KlasemenAdapter
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
        setHasOptionsMenu(true)
        adapter = KlasemenAdapter(items, TeamRepository())
        listItem.adapter = adapter

        presenter = KlasemenPresenter(this, KlasemenRepository())

        leagueId = activity!!.intent.extras!!.getString("id")!!
        presenter.getKlasemen(leagueId)

        swipeRefresh.onRefresh {
            presenter.getKlasemen(leagueId)

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


            cardView {
                lparams(width = matchParent, height = wrapContent) {
                    margin = dip(5)
                }

                verticalLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.HORIZONTAL
                    weightSum = 5f


                    textView {
                        id = R.id.team_name
                        textSize = 14f
                    }.lparams {
                        height = wrapContent
                        width = 0
                        weight = 0.5f
                    }

                    imageView {
                        id = R.id.team_logo
                    }.lparams {
                        height = wrapContent
                        width = 0
                        weight = 0.5f
                        marginEnd = 1
                    }

                    textView {
                        id = R.id.team_name
                        textSize = 14f
                        text = "Team"
                    }.lparams {
                        height = wrapContent
                        width = 0
                        weight = 1.5f
                    }

                    textView {
                        id = R.id.played
                        textSize = 14f
                        text = "P"
                    }.lparams {
                        height = wrapContent
                        width = 0
                        weight = 0.5f
                    }

                    textView {
                        id = R.id.win
                        textSize = 14f
                        text = "W"

                    }.lparams {
                        height = wrapContent
                        width = 0
                        weight = 0.5f
                    }

                    textView {
                        id = R.id.draw
                        textSize = 14f
                        text = "D"

                    }.lparams {
                        height = wrapContent
                        width = 0
                        weight = 0.5f
                    }

                    textView {
                        id = R.id.lose
                        textSize = 14f
                        text = "L"

                    }.lparams {
                        height = wrapContent
                        width = 0
                        weight = 0.5f
                    }

                    textView {
                        id = R.id.total
                        textSize = 14f
                        text = "Pts"

                    }.lparams {
                        height = wrapContent
                        width = 0
                        weight = 0.5f
                    }

                }

            }

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

    override fun onDataLoaded(data: KlasemenResponse?) {
        val math: List<Klasemen> = data!!.table
        emptyLayout.visibility = View.GONE
        swipeRefresh.isRefreshing = false
        items.clear()
        items.addAll(math)
        adapter.notifyDataSetChanged()
    }

    override fun onDataError() {
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.action_search)?.isVisible = false
    }

}