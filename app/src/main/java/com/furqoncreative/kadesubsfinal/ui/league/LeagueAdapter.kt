package com.furqoncreative.kadesubsfinal.ui.league

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.furqoncreative.kadesubsfinal.R
import com.furqoncreative.kadesubsfinal.model.LeagueItem
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView


class LeagueAdapter(
    private val items: List<LeagueItem>,
    private val listener: (LeagueItem) -> Unit
) : RecyclerView.Adapter<LeagueAdapter.LeagueViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        return LeagueViewHolder(
            LeagueItemUI().createView(
                AnkoContext.create(
                    parent.context,
                    parent
                )
            )
        )
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bindItem(items[position], listener)

    }

    override fun getItemCount(): Int = items.size

    class LeagueItemUI : AnkoComponent<ViewGroup> {
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

                        imageView {
                            id = R.id.league_logo
                        }.lparams {
                            height = dip(50)
                            width = dip(50)
                        }

                        textView {
                            id = R.id.league_name
                            textSize = 14f
                        }.lparams {
                            margin = dip(16)
                        }


                    }

                }


            }
        }
    }

    class LeagueViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val leagueLogo: ImageView = view.find(R.id.league_logo)
        private val leagueName: TextView = view.find(R.id.league_name)

        fun bindItem(items: LeagueItem, listener: (LeagueItem) -> Unit) {
            items.logo?.let { Picasso.get().load(it).into(leagueLogo) }

            leagueName.text = items.name

            itemView.setOnClickListener {
                listener(items)
            }
        }
    }

}

