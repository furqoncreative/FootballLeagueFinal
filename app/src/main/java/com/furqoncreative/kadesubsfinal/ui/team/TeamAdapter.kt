package com.furqoncreative.kadesubsfinal.ui.team

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.furqoncreative.kadesubsfinal.R
import com.furqoncreative.kadesubsfinal.model.Team
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView


class TeamAdapter(
    private val items: List<Team>,
    private val listener: (Team) -> Unit

) : RecyclerView.Adapter<TeamAdapter.MyViewHolder>() {


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
        holder.bindItem(items[position], listener)

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

                        imageView {
                            id = R.id.team_logo
                        }.lparams {
                            height = dip(50)
                            width = dip(50)
                        }

                        textView {
                            id = R.id.team_name
                            textSize = 14f
                        }.lparams {
                            margin = dip(16)
                        }


                    }

                }


            }
        }
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val name: TextView = view.find(R.id.team_name)
        private val logoTeam: ImageView = view.find(R.id.team_logo)

        fun bindItem(
            items: Team,
            listener: (Team) -> Unit
        ) {

            name.text = items.teamName
            Picasso.get().load(items.teamBadge).into(logoTeam)

            itemView.setOnClickListener {
                listener(items)
            }

        }

    }

}

