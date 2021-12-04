package com.example.lab2.players.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player_table")
data class Player(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "nume") var nume: String,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "dataNasterii") var dataNasterii: String,
    @ColumnInfo(name = "grad") var grad: String,
    @ColumnInfo(name = "nrMeciuriCastigate") var nrMeciuriCastigate: Int
)