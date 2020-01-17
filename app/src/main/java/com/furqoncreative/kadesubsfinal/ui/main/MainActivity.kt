package com.furqoncreative.kadesubsfinal.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.furqoncreative.kadesubsfinal.R
import com.furqoncreative.kadesubsfinal.ui.favorite.FavoriteFragment
import com.furqoncreative.kadesubsfinal.ui.league.LeagueFragment
import com.pandora.bottomnavigator.BottomNavigator

class MainActivity : AppCompatActivity() {
    private lateinit var navigator: BottomNavigator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigator = BottomNavigator.onCreate(
            fragmentContainer = R.id.fragment_container,
            bottomNavigationView = findViewById(R.id.bottomnav_view),
            rootFragmentsFactory = mapOf(
                R.id.tab1 to { LeagueFragment() },
                R.id.tab2 to { FavoriteFragment() }
            ),
            defaultTab = R.id.tab1,
            activity = this
        )
    }

    override fun onBackPressed() {
        if (!navigator.pop()) {
            super.onBackPressed()
        }
    }


}
