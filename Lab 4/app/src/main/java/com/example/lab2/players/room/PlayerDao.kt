package com.example.lab2.players.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lab2.players.domain.Player

@Dao
interface PlayerDao {

    @Query("SELECT * from player_table")
    fun getPlayers(): LiveData<List<Player>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(player: Player)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun modify(player: Player)

    @Query("DELETE FROM player_table WHERE id = :id")
    fun delete(id: Int)
}