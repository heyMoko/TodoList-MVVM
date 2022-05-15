package com.project.simpletodolist_mvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.project.simpletodolist_mvvm.repository.TodoRepository
import com.project.simpletodolist_mvvm.room.Todo

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = TodoRepository(application)
    private val items = repository.getAll()

    fun insert(todo: Todo) {
        repository.insert(todo)
    }

    fun delete(todo: Todo){
        repository.delete(todo)
    }

    fun getAll(): LiveData<List<Todo>> {
        return items
    }
}