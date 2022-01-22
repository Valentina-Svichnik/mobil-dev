package com.example.lesson6.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lesson6.model.Node

@Database(entities = [Node::class], version = 1, exportSchema = false)
//@TypeConverters(Converters::class)
abstract class NodeDataBase : RoomDatabase() {
    abstract fun nodeDao(): NodeDAO

    companion object {
        @Volatile
        private var INSTANCE: NodeDataBase? = null

        fun getDatabase(context: Context): NodeDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                var instance = Room.databaseBuilder(
                    context.applicationContext,
                    NodeDataBase::class.java,
                    "node_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
