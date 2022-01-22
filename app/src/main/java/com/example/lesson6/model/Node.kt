package com.example.lesson6.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "node_table")
data class Node(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "value") val value: String,
//    @ColumnInfo(name = "nodes") var nodes: String
):Parcelable

//data class Node(
//    @PrimaryKey(autoGenerate = true)
//    val id: Int,
//    val value: String
//)
