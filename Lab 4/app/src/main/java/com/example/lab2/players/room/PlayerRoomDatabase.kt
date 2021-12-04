package com.example.lab2.players.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.lab2.players.domain.Player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Player::class], version = 1, exportSchema = false)
abstract class PlayerRoomDatabase : RoomDatabase() {

    abstract fun playerDao(): PlayerDao

    companion object {
        @Volatile
        private var INSTANCE: PlayerRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): PlayerRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlayerRoomDatabase::class.java,
                    "player_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(PlayerDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class PlayerDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let {
                    scope.launch(Dispatchers.IO) {
                    }
                }
            }
        }
    }

}