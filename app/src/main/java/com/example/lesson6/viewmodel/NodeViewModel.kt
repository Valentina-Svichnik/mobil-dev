package com.example.lesson6.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.lesson6.db.NodeDataBase
import com.example.lesson6.repository.NodeRepository
import com.example.lesson6.model.Node
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NodeViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<Node>>
    private val repository: NodeRepository

    init {
        val nodeDAO = NodeDataBase.getDatabase(application).nodeDao()
        repository = NodeRepository(nodeDAO)
        readAllData = repository.readAllData
    }

    fun addNode(node: Node){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNode(node)
        }
    }

    fun updateNode(node: Node) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNode(node)
        }
    }

//    fun updateNode(value: Int, nodes: MutableList<Node>){
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.updateNode(value, nodes)
//        }
//    }
}


