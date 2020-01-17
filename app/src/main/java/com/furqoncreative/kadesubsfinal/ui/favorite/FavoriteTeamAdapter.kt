package com.furqoncreative.kadesubsfinal.ui.favorite

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.furqoncreative.kadesubsfinal.R
import com.furqoncreative.kadesubsfinal.data.local.TeamFavoriteDB
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView


class FavoriteTeamAdapter(
    private val teamFavoriteDB: List<TeamFavoriteDB>,
    private val listener: (TeamFavoriteDB) -> Unit
) : RecyclerView.Adapter<FavoriteTeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTeamViewHolder {
        return FavoriteTeamViewHolder(
            TeamItemUI().createView(
                AnkoContext.create(
                    parent.context,
                    parent
                )
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteTeamViewHolder, position: Int) {
        holder.bindItem(teamFavoriteDB[position], listener)
    }

    override fun getItemCount(): Int = teamFavoriteDB.size

}

class TeamItemUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {

            cardView {
                lparams(width = matchParent, height = wrapContent) {
                    margin = dip(5)
                    radius = 8f
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

class FavoriteTeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val teamLogo: ImageView = view.find(R.id.team_logo)
    private val teamName: TextView = view.find(R.id.team_name)

    fun bindItem(
        matchFavoriteDB: TeamFavoriteDB,
        listener: (TeamFavoriteDB) -> Unit
    ) {

        teamName.text = matchFavoriteDB.teamName
        Glide.with(itemView.context).load(matchFavoriteDB.teamBadge).into(teamLogo)

        itemView.setOnClickListener {
            listener(matchFavoriteDB)
        }
    }


}

