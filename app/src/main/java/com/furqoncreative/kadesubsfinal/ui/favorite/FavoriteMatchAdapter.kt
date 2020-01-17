package com.furqoncreative.kadesubsfinal.ui.favorite

import android.annotation.SuppressLint
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.furqoncreative.kadesubsfinal.R
import com.furqoncreative.kadesubsfinal.data.local.MatchFavoriteDB
import com.furqoncreative.kadesubsfinal.data.repository.RepositoryCallback
import com.furqoncreative.kadesubsfinal.data.repository.TeamRepository
import com.furqoncreative.kadesubsfinal.model.Team
import com.furqoncreative.kadesubsfinal.model.TeamResponse
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView


class FavoriteMatchAdapter(
    private val matchFavoriteDB: List<MatchFavoriteDB>,
    private val apiRepository: TeamRepository,
    private val listener: (MatchFavoriteDB) -> Unit
) : RecyclerView.Adapter<FavoriteMatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMatchViewHolder {
        return FavoriteMatchViewHolder(
            MatchItemUI().createView(
                AnkoContext.create(
                    parent.context,
                    parent
                )
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteMatchViewHolder, position: Int) {
        holder.bindItem(matchFavoriteDB[position], listener, apiRepository)
    }

    override fun getItemCount(): Int = matchFavoriteDB.size

}

class MatchItemUI : AnkoComponent<ViewGroup> {
    @SuppressLint("SetTextI18n")
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {

            cardView {
                lparams(width = matchParent, height = wrapContent) {
                    margin = dip(5)
                    radius = 8f
                }

                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(10)
                    orientation = LinearLayout.HORIZONTAL
                    weightSum = 3F

                    linearLayout {
                        lparams(width = 0, height = wrapContent, weight = 1F)
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER


                        textView {
                            id = R.id.event_home
                            textSize = 14f
                            gravity = Gravity.CENTER

                        }.lparams {
                            topMargin = dip(16)
                        }

                        imageView {
                            id = R.id.logo_home
                        }.lparams {
                            height = dip(50)
                            width = dip(50)
                        }

                        textView {
                            id = R.id.event_home
                            textSize = 14f
                            gravity = Gravity.CENTER

                        }.lparams {
                            topMargin = dip(16)
                        }
                    }

                    linearLayout {
                        lparams(width = 0, height = wrapContent, weight = 1F)
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER

                        textView {
                            id = R.id.event_date
                            textSize = 12f
                            gravity = Gravity.CENTER

                        }.lparams {
                            marginEnd = dip(4)
                            marginStart = dip(4)
                            topMargin = dip(16)
                        }

                        textView {
                            id = R.id.event_time
                            textSize = 12f
                            gravity = Gravity.CENTER

                        }.lparams {
                            marginEnd = dip(4)
                            marginStart = dip(4)
                            bottomMargin = dip(16)
                        }

                        linearLayout {
                            lparams(width = matchParent, height = wrapContent)
                            orientation = LinearLayout.HORIZONTAL
                            gravity = Gravity.CENTER

                            textView {
                                id = R.id.score_home
                                textSize = 20f
                            }

                            textView {
                                textSize = 18f
                                text = " vs "
                            }

                            textView {
                                id = R.id.score_away
                                textSize = 20f
                            }


                        }

                        textView {
                            id = R.id.event_location
                            textSize = 12f
                            gravity = Gravity.CENTER
                        }.lparams {
                            topMargin = dip(16)
                            bottomMargin = dip(16)
                        }

                    }


                    linearLayout {
                        lparams(width = 0, height = wrapContent, weight = 1F)
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER

                        textView {
                            id = R.id.event_away
                            textSize = 14f
                            gravity = Gravity.CENTER
                        }.lparams {
                            topMargin = dip(16)
                        }

                        imageView {
                            id = R.id.logo_away
                        }.lparams {
                            height = dip(50)
                            width = dip(50)
                        }


                    }


                }

            }


        }
    }
}

class FavoriteMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val eventLocation: TextView = view.find(R.id.event_location)
    private val eventTime: TextView = view.find(R.id.event_time)
    private val eventDate: TextView = view.find(R.id.event_date)
    private val eventHome: TextView = view.find(R.id.event_home)
    private val eventAway: TextView = view.find(R.id.event_away)
    private val scoreHome: TextView = view.find(R.id.score_home)
    private val scoreAway: TextView = view.find(R.id.score_away)
    private val logoAway: ImageView = view.find(R.id.logo_away)
    private val logoHome: ImageView = view.find(R.id.logo_home)

    fun bindItem(
        matchFavoriteDB: MatchFavoriteDB,
        listener: (MatchFavoriteDB) -> Unit,
        apiRepository: TeamRepository
    ) {

        eventHome.text = matchFavoriteDB.homeName
        eventAway.text = matchFavoriteDB.awayName
        eventTime.text = matchFavoriteDB.macthTime
        eventDate.text = matchFavoriteDB.macthDate
        eventLocation.text = matchFavoriteDB.leagueName
        if (matchFavoriteDB.homeScore != null) {
            scoreHome.text = matchFavoriteDB.homeScore
            scoreAway.text = matchFavoriteDB.awayScore
        } else {
            scoreHome.text = "-"
            scoreAway.text = "-"
        }
        matchFavoriteDB.homeBadge?.let { getLogoHome(apiRepository, it) }
        matchFavoriteDB.awayBadge?.let { getLogoAway(apiRepository, it) }


        itemView.setOnClickListener {
            listener(matchFavoriteDB)
        }
    }

    private fun getLogoHome(apiRepository: TeamRepository, id: String) {
        apiRepository.getDetailTeam(id, object : RepositoryCallback<TeamResponse?> {
            override fun onDataLoaded(data: TeamResponse?) {
                if (data?.teams != null) {
                    val team: Team? = data.teams[0]
                    Glide.with(itemView.context).load(team?.teamBadge).into(logoHome)
                }
            }

            override fun onDataError() {
            }

        })
    }

    private fun getLogoAway(apiRepository: TeamRepository, id: String) {
        apiRepository.getDetailTeam(id, object : RepositoryCallback<TeamResponse?> {
            override fun onDataLoaded(data: TeamResponse?) {
                if (data?.teams != null) {
                    val team: Team? = data.teams[0]
                    Glide.with(itemView.context).load(team?.teamBadge).into(logoAway)
                }
            }

            override fun onDataError() {
            }

        })

    }
}

