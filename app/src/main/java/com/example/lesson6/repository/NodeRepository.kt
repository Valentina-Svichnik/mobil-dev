package com.example.lesson6.repository

import androidx.lifecycle.LiveData
import com.example.lesson6.db.NodeDAO
import com.example.lesson6.model.Node

class NodeRepository(private val nodeDAO: NodeDAO) {

    val readAllData: LiveData<List<Node>> = nodeDAO.readAllData()

    suspend fun addNode(node: Node) {
        nodeDAO.addNode(node)
    }

    suspend fun updateNode(node: Node) {
        nodeDAO.updateNode(node)
    }

//    suspend fun updateNode(value: Int, nodes: MutableList<Node>){
//        nodeDAO.updateNode(value, nodes)
//    }
}
