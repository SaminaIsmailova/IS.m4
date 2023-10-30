package com.example.saparalieva_zhanna_hw_m4.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.saparalieva_zhanna_hw_m4.App
import com.example.saparalieva_zhanna_hw_m4.R
import com.example.saparalieva_zhanna_hw_m4.databinding.FragmentHomeBinding
import com.example.saparalieva_zhanna_hw_m4.databinding.FragmentTaskBinding
import com.example.saparalieva_zhanna_hw_m4.model.Task
import com.example.saparalieva_zhanna_hw_m4.ui.home.adapter.TaskAdapter
import com.example.saparalieva_zhanna_hw_m4.ui.task.TaskFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        setData()
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }

    private val adapter = TaskAdapter(this::onLongClick, this::onClick)

    private fun onClick(task: Task) {
        val bundle = bundleOf(TaskFragment.TASK_KEY to task)
        findNavController().navigate(R.id.taskFragment, bundle)
    }

    private fun onLongClick(task: Task) {
        val builder = AlertDialog.Builder(requireContext())
        builder
            .setTitle(getString(R.string.delete_title))
            .setMessage(getString(R.string.delete_massege))
            .setNegativeButton(getString(R.string.no)) { dialog, i ->
                dialog.dismiss()
            }
            .setPositiveButton(getString(R.string.yes)) { dialog, i ->
                App.db.taskDao().delete(task)
                setData()
            }
            .show()

    }

    private fun setData() {
        val data = App.db.taskDao().getAll()
        adapter.addTasks(data)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}