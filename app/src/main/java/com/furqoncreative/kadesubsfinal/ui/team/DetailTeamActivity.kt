package com.furqoncreative.kadesubsfinal.ui.team

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.furqoncreative.kadesubsfinal.R
import com.furqoncreative.kadesubsfinal.data.local.TeamFavoriteDB
import com.furqoncreative.kadesubsfinal.data.local.teamdatabase
import com.furqoncreative.kadesubsfinal.data.repository.TeamRepository
import com.furqoncreative.kadesubsfinal.model.Team
import com.furqoncreative.kadesubsfinal.model.TeamResponse
import com.furqoncreative.kadesubsfinal.presenter.TeamPresenter
import com.furqoncreative.kadesubsfinal.view.TeamView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

class DetailTeamActivity : AppCompatActivity(), TeamView {
    companion object {
        const val ID = "id"
    }

    private lateinit var teamFavorite: Team
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private var id: String = ""
    private lateinit var presenter: TeamPresenter
    private lateinit var nameTextView: TextView
    private lateinit var descTextView: TextView
    private lateinit var logoImageView: ImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var scrollview: ScrollView
    private lateinit var cardview: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = intent.getStringExtra(ID)
        val actionbar = supportActionBar
        actionbar!!.title = "Detail Team"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
        scrollview = scrollView {
            lparams(width = matchParent, height = matchParent)
            verticalLayout {
                lparams(width = matchParent, height = matchParent)
                padding = dip(14)

                cardview = cardView {
                    lparams(width = matchParent, height = matchParent) {
                        radius = 12f
                    }
                    visibility = View.GONE

                    linearLayout {
                        lparams(width = matchParent, height = matchParent)
                        padding = dip(10)
                        orientation = LinearLayout.VERTICAL
                        weightSum = 3F

                        logoImageView = imageView().lparams(width = dip(80), height = dip(80)) {
                            gravity = Gravity.CENTER
                        }

                        nameTextView = textView {
                            textSize = 16f
                        }.lparams(width = wrapContent) {
                            gravity = Gravity.CENTER
                            bottomMargin = 16
                        }

                        descTextView = textView().lparams(width = wrapContent) {
                            gravity = Gravity.CENTER
                            bottomMargin = 16
                        }
                    }


                }

                progressBar = progressBar {
                }.lparams {
                    gravity = Gravity.CENTER
                }

            }


        }


        favoriteState()
        presenter = TeamPresenter(this, TeamRepository())
        presenter.getDetailTeam(id)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun showEmptyData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @SuppressLint("SetTextI18n")
    override fun onDataLoaded(data: TeamResponse?) {
        val team: Team? = data!!.teams[0]
        if (team != null) {

            teamFavorite = Team(
                team.teamId,
                team.teamName,
                team.teamBadge
            )
            Picasso.get().load(team.teamBadge).into(logoImageView)
            nameTextView.text = team.teamName
            descTextView.text = team.teamDescription
            cardview.visibility = View.VISIBLE

        }
    }

    override fun onDataError() {
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun favoriteState() {
        teamdatabase.use {
            val result = select(TeamFavoriteDB.TABLE_FAVORITE)
                .whereArgs(
                    "(TEAM_ID = {id})",
                    "id" to id
                )
            val favorite = result.parseList(classParser<TeamFavoriteDB>())
            if (favorite.isNotEmpty()) isFavorite = true
        }
    }


    private fun addToFavorite() {
        try {
            teamdatabase.use {
                insert(
                    TeamFavoriteDB.TABLE_FAVORITE,
                    TeamFavoriteDB.TEAM_ID to teamFavorite.teamId,
                    TeamFavoriteDB.TEAM_NAME to teamFavorite.teamName,
                    TeamFavoriteDB.TEAM_BADGE to teamFavorite.teamBadge
                )

            }
            scrollview.snackbar("Added to favorite").show()

        } catch (e: SQLiteConstraintException) {
            scrollview.snackbar("Added to favorite").show()
        }
    }

    private fun removeFromFavorite() {
        try {
            teamdatabase.use {
                delete(
                    TeamFavoriteDB.TABLE_FAVORITE, "(TEAM_ID = {id})",
                    "id" to id
                )
            }
            scrollview.snackbar("Removed from favorite").show()
        } catch (e: SQLiteConstraintException) {
            scrollview.snackbar(e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorited)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_unfavorite)
    }

}
