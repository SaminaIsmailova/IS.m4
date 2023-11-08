package com.example.saparalieva_zhanna_hw_m4.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.saparalieva_zhanna_hw_m4.databinding.FragmentDashboardBinding
import com.example.saparalieva_zhanna_hw_m4.model.Cinema
import com.example.saparalieva_zhanna_hw_m4.ui.dashboard.adapter.CinemaAdapter
import com.example.saparalieva_zhanna_hw_m4.utils.showToast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    private val binding get() = _binding!!

    private var adapter = CinemaAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        Firebase.firestore.collection(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .get()
            .addOnSuccessListener {
                val list = it.toObjects(Cinema::class.java)
                adapter.addlist(list)
            }
            .addOnFailureListener {
                context?.showToast(it.message.toString())
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}