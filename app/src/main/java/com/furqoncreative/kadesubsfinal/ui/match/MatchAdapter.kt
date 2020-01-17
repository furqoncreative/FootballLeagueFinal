package com.furqoncreative.kadesubsfinal.ui.match

import android.annotation.SuppressLint
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.furqoncreative.kadesubsfinal.R
import com.furqoncreative.kadesubsfinal.data.repository.RepositoryCallback
import com.furqoncreative.kadesubsfinal.data.repository.TeamRepository
import com.furqoncreative.kadesubsfinal.model.Match
import com.furqoncreative.kadesubsfinal.model.Team
import com.furqoncreative.kadesubsfinal.model.TeamResponse
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView


class MatchAdapter(
    private val items: List<Match>,
    private var apiRepository: TeamRepository,
    private val listener: (Match) -> Unit
) : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(
            EventItemUI().createView(
                AnkoContext.create(
                    parent.context,
                    parent
                )
            )
        )
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(items[position], listener, apiRepository)

    }

    override fun getItemCount(): Int = items.size

    class EventItemUI : AnkoComponent<ViewGroup> {
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


    class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

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
            items: Match,
            listener: (Match) -> Unit,
            apiRepository: TeamRepository
        ) {

            eventHome.text = items.strHomeTeam
            eventAway.text = items.strAwayTeam
            eventTime.text = items.strTime
            eventDate.text = items.strDate
            eventLocation.text = items.strLeague
            if (items.intHomeScore != null) {
                scoreHome.text = items.intHomeScore
                scoreAway.text = items.intAwayScore
            } else {
                scoreHome.text = "-"
                scoreAway.text = "-"
            }
            getLogoHome(apiRepository, items.idHomeTeam)
            getLogoAway(apiRepository, items.idAwayTeam)

            itemView.setOnClickListener {
                listener(items)
            }
        }

        private fun getLogoHome(apiRepository: TeamRepository, id: String) {
            apiRepository.getDetailTeam(id, object : RepositoryCallback<TeamResponse?> {
                override fun onDataLoaded(data: TeamResponse?) {
                    if (data?.teams != null) {
                        val team: Team? = data.teams[0]
                        Picasso.get().load(team?.teamBadge).into(logoHome)
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
                        Picasso.get().load(team?.teamBadge).into(logoAway)
                    }
                }

                override fun onDataError() {
                }

            })
        }


    }

}

