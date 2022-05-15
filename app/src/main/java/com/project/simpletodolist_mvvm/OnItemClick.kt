package com.project.simpletodolist_mvvm

import com.project.simpletodolist_mvvm.room.Todo

interface OnItemClick {
    fun deleteTodo(todo: Todo)
}