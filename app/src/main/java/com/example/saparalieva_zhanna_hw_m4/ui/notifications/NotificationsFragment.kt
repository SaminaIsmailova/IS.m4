package com.example.saparalieva_zhanna_hw_m4.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.example.saparalieva_zhanna_hw_m4.R
import com.example.saparalieva_zhanna_hw_m4.databinding.FragmentNotificationsBinding
import com.example.saparalieva_zhanna_hw_m4.model.Cinema
import com.example.saparalieva_zhanna_hw_m4.utils.showToast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            val cinema = Cinema(
                name = binding.etName.text.toString(),
                author = binding.etAuthor.text.toString()
            )
            Firebase.firestore.collection(FirebaseAuth.getInstance().currentUser?.uid.toString())
                .add(cinema)
                .addOnSuccessListener {
                    context?.showToast(getString(R.string.upload_success))
                    binding.etAuthor.text?.clear()
                    binding.etName.text?.clear()
                }
                .addOnFailureListener{
                    context?.showToast(it.message.toString())
                }
        }

        val name = binding.name.editText?.text.toString()
        binding.author.editText?.doOnTextChanged { inputText, _, _, _ ->
        }
        val author = binding.author.editText?.text.toString()
        binding.author.editText?.doOnTextChanged { inputText, _, _, _ ->
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}