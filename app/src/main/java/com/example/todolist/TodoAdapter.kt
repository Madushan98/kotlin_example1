package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.itemtodo.view.*

class TodoAdapter(

    private  val todos: MutableList<Todo>
):   RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){



    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return  TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.itemtodo,
                parent,
                false
            )
        )


    }

    override fun getItemCount(): Int {
            return todos.size
    }

    private  fun  toggleStrikeThrough(todo: TextView, isChecked: Boolean ){
        if(isChecked){
            todo.paintFlags = todo.paintFlags or STRIKE_THRU_TEXT_FLAG

        }else {
            todo.paintFlags = todo.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    fun addTodo(todo:Todo){
        todos.add(todo)
        notifyItemInserted(todos.size-1)
    }

    fun deleteDonetodos() {
        todos.removeAll {
            todo -> todo.isChecked
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]

        holder.itemView.apply {
            todo.text = curTodo.title
            cdDone.isChecked = curTodo.isChecked
            toggleStrikeThrough(todo,curTodo.isChecked)
            cdDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(todo, isChecked)
                curTodo.isChecked = !curTodo.isChecked

            }
        }

    }
}