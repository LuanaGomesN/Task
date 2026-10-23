package com.luana.task.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.luana.task.R
import com.luana.task.databinding.FragmentRegisterBinding
import com.luana.task.databinding.FragmentSplashBinding
import com.luana.task.util.initToolbar
import com.luana.task.util.showBottomSheet


class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding?= null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

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
                binding.progressbar.isVisible=true
                regiserUser(email,senha)
            } else {
                Toast.makeText(requireContext(), "Preencha a senha!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "Preencha a senha", Toast.LENGTH_SHORT).show()
        }
    }

    private fun regiserUser(email:String,password:String){
        try{
        //instanciando o objeto de autentcação com o firebase
        val auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                task ->
                if(task.isSuccessful){
                    findNavController().navigate(R.id.action_global_homeFragment)
            }else{
                binding.progressbar.isInvisible=false
                Toast.makeText(requireContext(),task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }

        }catch (e: Exception){
        Toast.makeText(requireContext(),e.message.toString(), Toast.LENGTH_SHORT).show()

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}