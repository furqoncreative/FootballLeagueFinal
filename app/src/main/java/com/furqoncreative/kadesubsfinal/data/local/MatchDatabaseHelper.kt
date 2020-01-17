package com.furqoncreative.kadesubsfinal.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*


class MatchDatabaseHelper(ctx: Context) :
    ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 1) {
    companion object {
        private var instance: MatchDatabaseHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MatchDatabaseHelper {
            if (instance == null) {
                instance = MatchDatabaseHelper(ctx.applicationContext)
            }
            return instance as MatchDatabaseHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            MatchFavoriteDB.TABLE_FAVORITE, true,
            MatchFavoriteDB.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            MatchFavoriteDB.MATCH_ID to TEXT + UNIQUE,
            MatchFavoriteDB.MATCH_DATE to TEXT,
            MatchFavoriteDB.MATCH_TIME to TEXT,
            MatchFavoriteDB.LEAGUE_NAME to TEXT,
            MatchFavoriteDB.HOME_TEAM_NAME to TEXT,
            MatchFavoriteDB.AWAY_TEAM_NAME to TEXT,
            MatchFavoriteDB.HOME_TEAM_BADGE to TEXT,
            MatchFavoriteDB.AWAY_TEAM_BADGE to TEXT,
            MatchFavoriteDB.HOME_TEAM_SCORE to TEXT,
            MatchFavoriteDB.AWAY_TEAM_SCORE to TEXT,
            MatchFavoriteDB.CATEGORY to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(MatchFavoriteDB.TABLE_FAVORITE, true)
    }
}

val Context.matchdatabase: MatchDatabaseHelper
    get() = MatchDatabaseHelper.getInstance(applicationContext)