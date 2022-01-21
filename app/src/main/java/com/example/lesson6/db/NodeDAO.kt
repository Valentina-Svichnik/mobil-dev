package com.example.lesson6.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NodeDAO {

    @Query("SELECT * FROM node_table")
//    fun getAllNodes(): MutableList<Node>
    fun readAllData(): LiveData<List<Node>>

    @Insert
    fun insertNode(vararg nodes: Node)
    suspend fun addNode(node: Node)

    @Query("DELETE FROM node_table")
    fun clearTable()
//    abstract fun addNode(node: Node)
}
