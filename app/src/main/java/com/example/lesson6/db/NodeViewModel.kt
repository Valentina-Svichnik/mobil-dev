package com.example.lesson6.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.lesson6.db.NodeDataBase.Companion.getDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NodeViewModel(application: Application) : AndroidViewModel(application) {
    private val readAllData: LiveData<List<Node>>
    private val repository: NodeRepository

    init {
        val nodeDAO = NodeDataBase.getDatabase(application).nodeDao()
//        val nodeDAO = NodeDatabase.getDatabase(application).nodeDAO()
        repository = NodeRepository(nodeDAO)
        readAllData = repository.readAllData
    }

    fun addNode(node: Node){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNode(node)
        }
    }
}


