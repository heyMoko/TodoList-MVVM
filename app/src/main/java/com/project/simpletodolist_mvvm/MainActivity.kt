package com.project.simpletodolist_mvvm

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.simpletodolist_mvvm.databinding.ActivityMainBinding
import com.project.simpletodolist_mvvm.room.Todo
import com.project.simpletodolist_mvvm.viewmodel.TodoViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), OnItemClick {

    private lateinit var binding: ActivityMainBinding
    private val model: TodoViewModel by viewModels()
    private lateinit var adapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        model.getAll().observe(this, Observer{
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })

        binding.btnAdd.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO){
                model.insert(Todo(binding.editText.text.toString()))
            }
        }
    }

    private fun initRecyclerView(){
        binding.recyclerViewTodo.layoutManager = LinearLayoutManager(this)
        adapter = TodoAdapter(this)
        binding.recyclerViewTodo.adapter = adapter
    }

    override fun deleteTodo(todo: Todo) {
        lifecycleScope.launch(Dispatchers.IO){
            model.delete(todo)
        }
    }
}