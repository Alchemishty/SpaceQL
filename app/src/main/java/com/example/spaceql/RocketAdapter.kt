package com.example.spaceql

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rocketreserver.RocketListQuery
import com.example.spaceql.databinding.RocketItemBinding

class RocketAdapter (val rockets: List<RocketListQuery.Launch>) :
    RecyclerView.Adapter<RocketAdapter.ViewHolder>() {

    class ViewHolder(val binding: RocketItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return rockets.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RocketItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        println("Launch item called")
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rocket = rockets.get(position)
        holder.binding.name.text = rocket.rocket?.name
        holder.binding.id.text = rocket.rocket?.id
        holder.binding.type.text = rocket.rocket?.type
    }
}