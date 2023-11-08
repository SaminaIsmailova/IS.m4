package com.example.saparalieva_zhanna_hw_m4.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.saparalieva_zhanna_hw_m4.databinding.ItemTaskBinding
import com.example.saparalieva_zhanna_hw_m4.model.Cinema

class CinemaAdapter : Adapter<CinemaAdapter.CinemaViewHolder>() {

    private val list = arrayListOf<Cinema>()
    fun addlist(newList: List<Cinema>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CinemaViewHolder {
        return CinemaViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CinemaViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class CinemaViewHolder(private val binding: ItemTaskBinding) : ViewHolder(binding.root) {
        fun bind(cinema: Cinema) {
            binding.tvTitle.text = cinema.name
            binding.tvDesc.text = cinema.author
        }
    }

}