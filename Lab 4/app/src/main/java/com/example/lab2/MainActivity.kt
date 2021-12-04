package com.example.lab2

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab2.players.adapter.PlayersListAdapter
import com.example.lab2.players.viewmodel.PlayersViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private lateinit var playersViewModel: PlayersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = PlayersListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        playersViewModel = ViewModelProvider(this).get(PlayersViewModel::class.java)

        playersViewModel.allPlayers.observe(this, { players ->
            players?.let { adapter.setPlayers(it) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewPlayerActivity::class.java)
            newPlayerActivityLauncher.launch(intent)
        }

        adapter.onItemClick = {
            val intent = Intent(this@MainActivity, PlayerDetailsActivity::class.java)
            intent.putExtra("id", it.id.toString())
            intent.putExtra("username", it.username)
            intent.putExtra("nume", it.nume)
            intent.putExtra("email", it.email)
            intent.putExtra("dataNasterii", it.dataNasterii)
            intent.putExtra("grad", it.grad)
            intent.putExtra("nrMeciuri", it.nrMeciuriCastigate.toString())
            playerDetailsActivityLauncher.launch(intent)
        }
    }

    private val newPlayerActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val data = result.data
            if (data != null) {
                playersViewModel.insert(data.getStringExtra("username")!!,data.getStringExtra("nume")!!,data.getStringExtra("email")!!,data.getStringExtra("dataNasterii")!!,data.getStringExtra("grad")!!,data.getStringExtra("nrMeciuri")!!)
            }

        }

    private val playerDetailsActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val data = result.data
            if(data!!.getStringExtra("modificare/stergere").equals("modificare")){
                playersViewModel.modify(Integer.parseInt(data.getStringExtra("id")!!), data.getStringExtra("username")!!,data.getStringExtra("nume")!!,data.getStringExtra("email")!!,data.getStringExtra("dataNasterii")!!,data.getStringExtra("grad")!!,Integer.parseInt(data.getStringExtra("nrMeciuri")!!))
            }else if(data.getStringExtra("modificare/stergere").equals("stergere")){
                playersViewModel.delete(Integer.parseInt(data.getStringExtra("id")!!))
            }


        }
}