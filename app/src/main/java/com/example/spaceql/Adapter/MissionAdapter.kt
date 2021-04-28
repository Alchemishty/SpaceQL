package com.example.spaceql

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.rocketreserver.MissionListQuery
import com.example.spaceql.databinding.MissionItemBinding

class MissionAdapter(val missions: List<MissionListQuery.Launch>) :
    RecyclerView.Adapter<MissionAdapter.ViewHolder>() {

    class ViewHolder(val binding: MissionItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return missions.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MissionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mission = missions.get(position)
        holder.binding.mission.text = mission.mission?.name
        holder.binding.profilePic.load(mission.mission?.missionPatch) {
            placeholder(R.drawable.ic_placeholder)
        }
    }
}