package com.example.lesson6.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Node::class], version = 1, exportSchema = false)
abstract class NodeDataBase : RoomDatabase() {
    abstract fun nodeDao(): NodeDAO

    companion object {
        @Volatile
        private var instance: NodeDataBase? = null

        fun getDatabase(context: Context): NodeDataBase {
            val tempInstance = instance
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                var instance = Room.databaseBuilder(
                    context.applicationContext,
                    NodeDataBase::class.java,
                    "node_database"
                ).build()
                instance = instance
                return instance
            }
        }
    }
}
