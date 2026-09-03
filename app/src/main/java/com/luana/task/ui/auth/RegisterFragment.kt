package com.luana.task.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.luana.task.R
import com.luana.task.databinding.FragmentRegisterBinding
import com.luana.task.databinding.FragmentSplashBinding
import com.luana.task.util.initToolbar
import com.luana.task.util.showBottomSheet


class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(binding.toolbar)
        initListener()
    }

    private fun initListener(){
        binding.buttonRegister.setOnClickListener{
            validateData()
        }
    }

    private fun validateData() {
        val email = binding.edittextEmail.text.toString().trim()
        val senha = binding.edittextSenha.text.toString().trim()

        if (email.isNotBlank()) {
            if (senha.isNotBlank()) {
                Toast.makeText(requireContext(), "Tudo ok!", Toast.LENGTH_SHORT).show()
            } else {
                showBottomSheet(message = getString(R.string.password_empty_register_fragment))
            }
        } else {
            showBottomSheet(message = getString(R.string.email_empty_register_fragment))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}