package com.example.saparalieva_zhanna_hw_m4.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.saparalieva_zhanna_hw_m4.R
import com.example.saparalieva_zhanna_hw_m4.data.local.Pref
import com.example.saparalieva_zhanna_hw_m4.databinding.FragmentOnBoardingBinding
import com.example.saparalieva_zhanna_hw_m4.databinding.FragmentProfileBinding
import com.example.saparalieva_zhanna_hw_m4.ui.onboarding.adapter.OnBoardingAdapter
import me.relex.circleindicator.CircleIndicator
import me.relex.circleindicator.CircleIndicator3


class OnBoardingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardingBinding

    private val adapter = OnBoardingAdapter(this::onClick)

    private  val  pref by lazy {
        Pref(requireContext())
    }

    private fun onClick() {
        pref.onShowed()
        findNavController().navigateUp()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = adapter
        binding.indicator.setViewPager(binding.viewPager)
    }


}