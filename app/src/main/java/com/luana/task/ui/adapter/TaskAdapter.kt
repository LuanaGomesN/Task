package com.luana.task.ui.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.luana.task.R
import com.luana.task.data.model.Status
import com.luana.task.data.model.Task
import com.luana.task.databinding.ItemTaskBinding

class TaskAdapter(
    private val context: Context,
    private val taskSeleted: (Task, Int) -> Unit
) : ListAdapter<Task, TaskAdapter.MyViewHolder>(DIFF_CALBACK) {

    companion object {
        val SELECT_BACK: Int = 1
        val SELECT_REMOVER: Int = 2
        val SELECT_EDIT: Int = 3
        val SELECT_DETAILS: Int = 4
        val SELECT_NEXT: Int = 5

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Task>() {
            override fun areItemsTheSame(
                oldItem: Task,
                newItem: Task
            ): Boolean {
                return oldItem.id == newItem.id && oldItem.description == newItem.description
            }

            override fun areContentsTheSame(
                oldItem: Task,
                newItem: Task
            ): Boolean {
                return oldItem == newItem && oldItem.description == newItem.description
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val task = getItem(position)
        holder.binding.textDescription.text = task.description

        setIndicators(task, holder)
    }

    private fun setIndicators(task: Task, holder: MyViewHolder){
        when (task.status){
            Status.TODO -> {
                holder.binding.buttonBack.isInvisible = false
                holder.binding.buttonForward.setOnClickListener { taskSeleted(task, SELECT_NEXT)}
            }
            Status.DOING -> {
                holder.binding.buttonBack.setColorFilter(ContextCompat.getColor(context, R.color.color_status_todo))
                holder.binding.buttonForward.setColorFilter(ContextCompat.getColor(context, R.color.color_status_done))
                holder.binding.buttonForward.setOnClickListener { taskSeleted(task, SELECT_NEXT)}
                holder.binding.buttonBack.setOnClickListener { taskSeleted(task, SELECT_BACK)}

            }
            Status.DONE -> {
                holder.binding.buttonForward.isInvisible = false
                holder.binding.buttonBack.setOnClickListener { taskSeleted(task, SELECT_BACK)}

            }
        }

        holder.binding.buttonDelete.setOnClickListener { taskSeleted(task, SELECT_REMOVER)}
        holder.binding.buttonEditar.setOnClickListener { taskSeleted(task, SELECT_EDIT)}
        holder.binding.buttonDetails.setOnClickListener { taskSeleted(task, SELECT_DETAILS)}
    }
    inner class MyViewHolder(
        val binding: ItemTaskBinding
    ) : RecyclerView.ViewHolder(binding.root) {

    }
}