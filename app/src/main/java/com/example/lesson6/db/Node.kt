package com.example.lesson6.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "node_table")
data class Node(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "value") val value: String,
//    @ColumnInfo(name = "nodes") var nodes: String
)

//data class Node(
//    @PrimaryKey(autoGenerate = true)
//    val id: Int,
//    val value: String
//)
