package com.furqoncreative.kadesubsfinal.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*


class TeamDatabaseHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {
    companion object {
        private var instance: TeamDatabaseHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): TeamDatabaseHelper {
            if (instance == null) {
                instance = TeamDatabaseHelper(ctx.applicationContext)
            }
            return instance as TeamDatabaseHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            TeamFavoriteDB.TABLE_FAVORITE, true,
            TeamFavoriteDB.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            TeamFavoriteDB.TEAM_ID to TEXT + UNIQUE,
            TeamFavoriteDB.TEAM_NAME to TEXT,
            TeamFavoriteDB.TEAM_BADGE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(TeamFavoriteDB.TABLE_FAVORITE, true)
    }
}

val Context.teamdatabase: TeamDatabaseHelper
    get() = TeamDatabaseHelper.getInstance(applicationContext)