package com.example.lesson6.db

import androidx.lifecycle.LiveData

class NodeRepository(private val nodeDAO: NodeDAO) {

    val readAllData: LiveData<List<Node>> = nodeDAO.readAllData()

    suspend fun addNode(node: Node) {
        nodeDAO.addNode(node)
    }
}
