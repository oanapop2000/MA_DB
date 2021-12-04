package com.example.lab2.players.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.lab2.players.domain.Player
import com.example.lab2.players.repo.PlayersRepository
import com.example.lab2.players.room.PlayerRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlayersViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PlayersRepository

    val allPlayers: LiveData<List<Player>>

    init {
        val playersDao = PlayerRoomDatabase.getDatabase(application, viewModelScope).playerDao()
        repository = PlayersRepository(playersDao)
        allPlayers = repository.allPlayers
    }

    fun insert(username: String, nume: String, email: String, data: String, grad: String, nrMeciuri: String) = viewModelScope.launch(Dispatchers.IO){
        val nrMeciuriBun = Integer.parseInt(nrMeciuri)
        val player = Player(0,username, nume, email, data, grad, nrMeciuriBun)
        repository.addPlayer(player)
    }

    fun modify(id:Int, username: String, nume:String, email:String, data:String, grad:String, nrMeciuri: Int)  = viewModelScope.launch(Dispatchers.IO){
        val player = Player(id,username, nume, email, data, grad, nrMeciuri)
        repository.modifyPlayer(player)

    }

    fun delete(id: Int)  = viewModelScope.launch(Dispatchers.IO){
        repository.deletePlayer(id)
    }
}