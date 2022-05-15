package com.project.simpletodolist_mvvm.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo (
    var content: String
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}