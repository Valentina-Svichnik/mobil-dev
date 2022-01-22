package com.example.lesson6.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lesson6.model.Node

@Dao
interface NodeDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNode(node: Node)

    @Query("SELECT * FROM node_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Node>>

    @Update
    suspend fun updateNode(node: Node)

//    @Query("SELECT * FROM node_table")
// //    fun getAllNodes(): MutableList<Node>
//    fun readAllData(): LiveData<List<Node>>
//
//    @Insert
//    fun insertNode(vararg nodes: Node)
//    suspend fun addNode(node: Node)
//
//    @Query("DELETE FROM node_table")
//    fun clearTable()
// //    abstract fun addNode(node: Node)
}
