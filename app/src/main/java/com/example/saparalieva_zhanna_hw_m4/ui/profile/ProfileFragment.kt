package com.example.saparalieva_zhanna_hw_m4.ui.profile

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.saparalieva_zhanna_hw_m4.App
import com.example.saparalieva_zhanna_hw_m4.R
import com.example.saparalieva_zhanna_hw_m4.data.local.Pref
import com.example.saparalieva_zhanna_hw_m4.databinding.FragmentProfileBinding
import com.example.saparalieva_zhanna_hw_m4.utils.loadImage
import com.google.firebase.auth.FirebaseAuth


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val pref by lazy {
        Pref(requireContext())
    }

    private val openGallery =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val selectedImageUri = it.data?.data
                pref.saveImage(selectedImageUri.toString())
                binding.profileImage.loadImage(selectedImageUri.toString())
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.profileImage.loadImage(pref.getImage())
        binding.etName.setText(pref.getName())

        binding.profileImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.setType("image/*")
            openGallery.launch(intent)
        }
        binding.btnSave.setOnClickListener {
            pref.saveName(binding.etName.text.toString())
        }

        binding.imgExit.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder
                .setTitle(getString(R.string.exit))
                .setMessage(getString(R.string.are_you_sure_exit))
                .setNegativeButton(getString(R.string.no)) { dialog, i ->
                    dialog.dismiss()
                }
                .setPositiveButton(getString(R.string.yes)) { dialog, i ->
                    FirebaseAuth.getInstance().signOut()
                    findNavController().navigate(R.id.phoneFragment)
                }
                .show()
        }

        val inputText = binding.outlinedTextField.editText?.text.toString()
        binding.outlinedTextField.editText?.doOnTextChanged { inputText, _, _, _ ->
        }
    }

}