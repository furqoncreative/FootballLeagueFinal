package com.furqoncreative.kadesubsfinal.ui.league

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.furqoncreative.kadesubsfinal.R
import com.furqoncreative.kadesubsfinal.data.repository.DetailLeagueRepository
import com.furqoncreative.kadesubsfinal.model.League
import com.furqoncreative.kadesubsfinal.model.LeagueResponse
import com.furqoncreative.kadesubsfinal.presenter.DetailLeaguePresenter
import com.furqoncreative.kadesubsfinal.ui.klasemen.KlasemenFragment
import com.furqoncreative.kadesubsfinal.ui.match.NextMatchFragment
import com.furqoncreative.kadesubsfinal.ui.match.PrevMatchFragment
import com.furqoncreative.kadesubsfinal.ui.search.ResultMatchActivity
import com.furqoncreative.kadesubsfinal.ui.team.ResultTeamActivity
import com.furqoncreative.kadesubsfinal.ui.team.TeamFragment
import com.furqoncreative.kadesubsfinal.view.DetailLeagueView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.support.v4.viewPager


class DetailLeagueActivity : AppCompatActivity(), DetailLeagueView, SearchView.OnQueryTextListener {
    companion object {
        const val ID = "id"
    }

    private var id: String = ""
    private var index: Int = 0
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var presenter: DetailLeaguePresenter
    private lateinit var nameTextView: TextView
    private lateinit var country: TextView
    private lateinit var countryTextView: TextView
    private lateinit var web: TextView
    private lateinit var webTextView: TextView
    private lateinit var year: TextView
    private lateinit var yearTextView: TextView
    private lateinit var date: TextView
    private lateinit var dateTextView: TextView
    private lateinit var gender: TextView
    private lateinit var genderTextView: TextView
    private lateinit var logoImageView: ImageView
    private lateinit var trophyImageView: ImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var fragmentlayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionbar = supportActionBar
        actionbar!!.title = "Detail League"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
        coordinatorLayout {
            lparams(matchParent, matchParent)

            appBarLayout {
                lparams(matchParent, wrapContent)

                linearLayout {
                    lparams(width = matchParent, height = 500)
                    padding = dip(14)
                    orientation = LinearLayout.VERTICAL

                    logoImageView = imageView().lparams(width = dip(70), height =  dip(70)) {
                        gravity = Gravity.CENTER
                    }

                    nameTextView = textView().lparams(width = wrapContent) {
                        gravity = Gravity.CENTER
                        bottomMargin = 16
                    }

                    linearLayout {
                        lparams(width = matchParent, height = wrapContent)
                        orientation = LinearLayout.HORIZONTAL
                        weightSum = 3f

                        trophyImageView =
                            imageView().lparams(width = 0, height = wrapContent, weight = 1f) {
                                gravity = Gravity.CENTER
                            }


                        linearLayout {
                            lparams(width = 0, height = wrapContent, weight = 2f)
                            orientation = LinearLayout.VERTICAL
                            weightSum = 2f
                            gravity = Gravity.CENTER_HORIZONTAL

                            linearLayout {
                                lparams(width = matchParent, height = wrapContent)
                                orientation = LinearLayout.HORIZONTAL
                                weightSum = 2f

                                country = textView {
                                    textSize = 12f
                                }.lparams {
                                    height = wrapContent
                                    width = 0
                                    weight = 1f
                                }

                                countryTextView = textView {
                                    textSize = 12f

                                }.lparams {
                                    height = wrapContent
                                    width = 0
                                    weight = 1f

                                }


                            }

                            linearLayout {
                                lparams(width = matchParent, height = wrapContent)
                                orientation = LinearLayout.HORIZONTAL
                                weightSum = 2f

                                web = textView {
                                    textSize = 12f

                                }.lparams {
                                    height = wrapContent
                                    width = 0
                                    weight = 1f
                                }


                                webTextView = textView {
                                    textSize = 12f

                                }.lparams {
                                    height = wrapContent
                                    width = 0
                                    weight = 1f

                                }


                            }

                            linearLayout {
                                lparams(width = matchParent, height = wrapContent)
                                orientation = LinearLayout.HORIZONTAL
                                weightSum = 2f

                                gender = textView {
                                    textSize = 12f

                                }.lparams {
                                    height = wrapContent
                                    width = 0
                                    weight = 1f

                                }

                                genderTextView = textView {
                                    textSize = 12f

                                }.lparams {
                                    height = wrapContent
                                    width = 0
                                    weight = 1f

                                }
                            }

                            linearLayout {
                                lparams(width = matchParent, height = wrapContent)
                                orientation = LinearLayout.HORIZONTAL
                                weightSum = 2f

                                year = textView {
                                    textSize = 12f

                                }.lparams {
                                    height = wrapContent
                                    width = 0
                                    weight = 1f


                                }

                                yearTextView = textView {
                                    textSize = 12f

                                }.lparams {
                                    height = wrapContent
                                    width = 0
                                    weight = 1f

                                }

                            }
                            linearLayout {
                                lparams(width = matchParent, height = wrapContent)
                                orientation = LinearLayout.HORIZONTAL
                                weightSum = 2f

                                date = textView {
                                    textSize = 12f

                                }.lparams {
                                    height = wrapContent
                                    width = 0
                                    weight = 1f

                                }

                                dateTextView = textView {
                                    textSize = 12f

                                }.lparams {
                                    height = wrapContent
                                    width = 0
                                    weight = 1f

                                }


                            }
                        }


                    }


                    progressBar = progressBar {
                    }.lparams {
                        gravity = Gravity.CENTER
                    }

                }

            }

            fragmentlayout = linearLayout {
                orientation = LinearLayout.VERTICAL
                lparams(width = matchParent, height = matchParent)

                tabLayout = themedTabLayout(R.style.ThemeOverlay_AppCompat_ActionBar) {
                    lparams(matchParent, wrapContent)
                    {
                        tabGravity = TabLayout.GRAVITY_FILL
                        tabMode = TabLayout.MODE_FIXED

                    }
                }
                viewPager = viewPager {
                    id = R.id.viewpager
                }.lparams(matchParent, matchParent)
            }

            (fragmentlayout.layoutParams as CoordinatorLayout.LayoutParams).behavior =
                AppBarLayout.ScrollingViewBehavior()


        }



        id = intent.getStringExtra(ID)
        presenter = DetailLeaguePresenter(this, DetailLeagueRepository())
        presenter.getDetailLeague(id)

        val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
        viewPager.adapter = fragmentAdapter
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.setOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab) {
                index = tab.position
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                index = tab.position
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                val searchView =
                    MenuItemCompat.getActionView(item) as SearchView
                searchView.setOnQueryTextListener(this)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (index == 0 || index == 1) {
            startActivity<ResultMatchActivity>(
                ResultMatchActivity.QUERY to query
            )
        } else {
            startActivity<ResultTeamActivity>(
                ResultTeamActivity.QUERY to query
            )
        }

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    @SuppressLint("SetTextI18n")
    override fun onDataLoaded(data: LeagueResponse?) {
        val league: League? = data!!.leagues[0]
        if (league != null) {
            Picasso.get().load(league.strBadge).into(logoImageView)
            Picasso.get().load(league.strTrophy).into(trophyImageView)
            nameTextView.text = league.strLeague
            webTextView.text = ": " + league.strWebsite
            web.text = "Website"
            countryTextView.text = ": " + league.strCountry
            country.text = "Country"
            genderTextView.text = ": " + league.strGender
            gender.text = "Gender"
            yearTextView.text = ": " + league.intFormedYear
            year.text = "Formed Year"
            dateTextView.text = ": " + league.dateFirstEvent
            date.text = "Date First Event"
        }
    }

    override fun onDataError() {
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}

class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                PrevMatchFragment()
            }
            1 -> {
                NextMatchFragment()
            }
            2 -> {
                KlasemenFragment()
            }
            else -> {
                return TeamFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Previous Match"
            1 -> "Next Match"
            2 -> "Klasemen"
            else -> {
                return "Team"
            }
        }
    }
}