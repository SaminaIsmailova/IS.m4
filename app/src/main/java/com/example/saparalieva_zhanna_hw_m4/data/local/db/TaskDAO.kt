package com.example.saparalieva_zhanna_hw_m4.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.saparalieva_zhanna_hw_m4.model.Task

@Dao
interface TaskDAO {

    @Query("SELECT * FROM task ORDER BY id DESC")
    fun  getAll(): List<Task>

    @Insert
    fun insert(task: Task)

    @Delete
    fun delete(task: Task)

    @Update
    fun upDate(task: Task)



}