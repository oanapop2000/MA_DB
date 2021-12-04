package com.example.lab2.players.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab2.R
import com.example.lab2.players.domain.Player

class PlayersListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<PlayersListAdapter.PlayerViewHolder>() {

    var onItemClick: ((Player) -> Unit)? = null
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var players = emptyList<Player>() // Cached copy of words

    inner class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerItemView: TextView = itemView.findViewById(R.id.textView)
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(players[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return PlayerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val current = players[position]
        holder.playerItemView.text = current.username
    }

    internal fun setPlayers(words: List<Player>) {
        this.players = words
        notifyDataSetChanged()
    }

    override fun getItemCount() = players.size
}