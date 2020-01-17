package com.furqoncreative.kadesubsfinal.ui.klasemen

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.furqoncreative.kadesubsfinal.R
import com.furqoncreative.kadesubsfinal.data.repository.RepositoryCallback
import com.furqoncreative.kadesubsfinal.data.repository.TeamRepository
import com.furqoncreative.kadesubsfinal.model.Klasemen
import com.furqoncreative.kadesubsfinal.model.Team
import com.furqoncreative.kadesubsfinal.model.TeamResponse
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView


class KlasemenAdapter(
    private val items: List<Klasemen>,
    private var apiRepository: TeamRepository
) : RecyclerView.Adapter<KlasemenAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemUI().createView(
                AnkoContext.create(
                    parent.context,
                    parent
                )
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItem(items[position], apiRepository, position)

    }

    override fun getItemCount(): Int = items.size

    class ItemUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {

                cardView {
                    lparams(width = matchParent, height = wrapContent) {
                        margin = dip(5)
                    }

                    verticalLayout {
                        lparams(width = matchParent, height = wrapContent)
                        padding = dip(16)
                        orientation = LinearLayout.HORIZONTAL
                        weightSum = 5f
                        gravity = Gravity.CENTER

                        textView {
                            id = R.id.position
                            textSize = 14f
                            gravity = Gravity.CENTER
                        }.lparams {
                            height = wrapContent
                            width = 0
                            weight = 0.5f
                        }

                        imageView {
                            id = R.id.team_logo
                        }.lparams {
                            height = dip(30)
                            width = 0
                            weight = 0.8f
                            marginEnd = 1
                            gravity = Gravity.CENTER

                        }

                        textView {
                            id = R.id.team_name
                            textSize = 14f
                            gravity = Gravity.CENTER

                        }.lparams {
                            height = wrapContent
                            width = 0
                            weight = 1.2f
                            marginEnd = 1
                        }

                        textView {
                            id = R.id.played
                            textSize = 14f
                            gravity = Gravity.CENTER

                        }.lparams {
                            height = wrapContent
                            width = 0
                            weight = 0.5f
                        }

                        textView {
                            id = R.id.win
                            textSize = 14f
                            gravity = Gravity.CENTER

                        }.lparams {
                            height = wrapContent
                            width = 0
                            weight = 0.5f
                        }

                        textView {
                            id = R.id.draw
                            textSize = 14f
                            gravity = Gravity.CENTER

                        }.lparams {
                            height = wrapContent
                            width = 0
                            weight = 0.5f
                        }

                        textView {
                            id = R.id.lose
                            textSize = 14f
                            gravity = Gravity.CENTER

                        }.lparams {
                            height = wrapContent
                            width = 0
                            weight = 0.5f
                        }

                        textView {
                            id = R.id.total
                            textSize = 14f
                            gravity = Gravity.CENTER

                        }.lparams {
                            height = wrapContent
                            width = 0
                            weight = 0.5f
                        }

                    }

                }


            }
        }
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val position: TextView = view.find(R.id.position)
        private val total: TextView = view.find(R.id.total)
        private val lose: TextView = view.find(R.id.lose)
        private val draw: TextView = view.find(R.id.draw)
        private val win: TextView = view.find(R.id.win)
        private val played: TextView = view.find(R.id.played)
        private val name: TextView = view.find(R.id.team_name)
        private val logoTeam: ImageView = view.find(R.id.team_logo)

        fun bindItem(
            items: Klasemen,
            apiRepository: TeamRepository,
            positions: Int
        ) {
            position.text = (positions + 1).toString()
            name.text = items.name
            played.text = items.played.toString()
            win.text = items.win.toString()
            draw.text = items.draw.toString()
            lose.text = items.loss.toString()
            total.text = items.total.toString()

            getLogoTeam( apiRepository, items.teamid)

        }

        private fun getLogoTeam(apiRepository: TeamRepository, id: String) {
            apiRepository.getDetailTeam(id, object : RepositoryCallback<TeamResponse?> {
                override fun onDataLoaded(data: TeamResponse?) {
                    if (data?.teams != null) {
                        val team: Team? = data.teams[0]
                        Picasso.get().load(team?.teamBadge).into(logoTeam)
                    }
                }

                override fun onDataError() {
                }

            })
        }


    }

}

