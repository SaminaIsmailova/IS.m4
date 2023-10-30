package com.example.saparalieva_zhanna_hw_m4.ui.home.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.saparalieva_zhanna_hw_m4.App
import com.example.saparalieva_zhanna_hw_m4.databinding.ItemTaskBinding
import com.example.saparalieva_zhanna_hw_m4.model.Task
import com.example.saparalieva_zhanna_hw_m4.ui.home.HomeFragment
import java.text.ParsePosition

class TaskAdapter(
    val onLongClick: (Task) -> Unit,
    private val onClick: (Task) -> Unit
) : Adapter<TaskAdapter.TaskViewHolder>() {

    private val list = arrayListOf<Task>()

    fun addTasks(tasks: List<Task>) {
        list.clear()
        list.addAll(tasks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : ViewHolder(binding.root) {
        fun bind(task: Task) {
            itemView.setOnLongClickListener(View.OnLongClickListener {
                onLongClick(task)
                true
            })
            itemView.setOnClickListener {
                onClick(task)
            }

            binding.tvTitle.text = task.title
            binding.tvDesc.text = task.desc
        }
    }

}