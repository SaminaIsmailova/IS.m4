package com.example.saparalieva_zhanna_hw_m4.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.saparalieva_zhanna_hw_m4.App
import com.example.saparalieva_zhanna_hw_m4.R
import com.example.saparalieva_zhanna_hw_m4.databinding.FragmentTaskBinding
import com.example.saparalieva_zhanna_hw_m4.model.Task


class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private var task: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        val inputText = binding.outlinedTextField.editText?.text.toString()
        binding.outlinedTextField.editText?.doOnTextChanged { inputText, _, _, _ ->
        }
        val inputTextDesc = binding.title.editText?.text.toString()
        binding.outlinedTextField.editText?.doOnTextChanged { inputText, _, _, _ ->
        }

        task = arguments?.getSerializable(TASK_KEY) as Task?
        binding.etDesc.setText(task?.desc)
        binding.etTitle.setText(task?.title)
        if (task != null) {
            btnSave.text = getString(R.string.update)
            btnSave.setOnClickListener {
                upDate()
            }
        } else {
            binding.btnSave.setOnClickListener {
                save()
            }

        }

    }

    private fun upDate() = with(binding) {
        var updatedTask = task?.copy(
            title = etTitle.text.toString(),
            desc = etDesc.text.toString()
        )
        App.db.taskDao().upDate(updatedTask!!)
        findNavController().navigateUp()

    }

    private fun save() = with(binding) {
        val data = Task(
            title = etTitle.text.toString(),
            desc = etDesc.text.toString()
        )
        App.db.taskDao().insert(data)
        findNavController().navigateUp()
    }


    companion object {
        const val RESULT_KEY = "result.key"
        const val TASK_KEY = "task.key"
    }

}
