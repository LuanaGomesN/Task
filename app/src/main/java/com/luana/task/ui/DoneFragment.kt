package com.luana.task.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.luana.task.R
import com.luana.task.data.model.Status
import com.luana.task.data.model.Task
import com.luana.task.databinding.FragmentDoneBinding
import com.luana.task.databinding.FragmentTodoBinding
import com.luana.task.ui.adapter.TaskAdapter

class DoneFragment : Fragment() {
    private var _binding: FragmentDoneBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initListeners()

        initRecyclerViewTask(getTask())
    }

    //private fun initListeners(){
    //    binding.floatingActionButton2.setOnClickListener {
    //        findNavController().navigate((R.id.action_homeFragment_to_formTaskFragment))
    //    }
    //}

    private fun initRecyclerViewTask(taskList: List<Task>){
        taskAdapter = TaskAdapter(taskList)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)

        binding.recyclerView.adapter = taskAdapter
    }

    private fun getTask() = listOf(
        Task("0", "Criar nova tela do app",Status.DONE),
        Task("1", "Validar informações na tela de login",Status.DONE),
        Task("2", "Adicionar nova funcionalidade no app",Status.DONE),
        Task("3", "Salvar token localmente",Status.DONE,
        Task("4", "Criar funcionalidade de logout no app",Status.DONE),
    )

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}