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
import com.luana.task.databinding.FragmentDoingBinding
import com.luana.task.databinding.FragmentTodoBinding
import com.luana.task.ui.adapter.TaskAdapter

class DoingFragment : Fragment() {
    private var _binding: FragmentDoingBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViewTask()
        getTask()
    }


    private fun initRecyclerViewTask(){


        taskAdapter = TaskAdapter(requireContext() ) {task, option -> optionSelected(task, option)}

        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = taskAdapter
        }
    }

    private fun optionSelected(task:Task, option:Int){

    }

    private fun getTask() = listOf(
        Task("0", "Criar nova tela do app",Status.DOING),
        Task("1", "Validar informações na tela de login",Status.DOING),
        Task("2", "Adicionar nova funcionalidade no app",Status.DOING),
        Task("3", "Salvar token localmente",Status.DOING),
        Task("4", "Criar funcionalidade de logout no app",Status.DOING),
    )

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}