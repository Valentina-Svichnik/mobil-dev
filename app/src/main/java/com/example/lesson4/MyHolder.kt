package com.example.lesson4

import java.util.*

object MyHolder {

    private val messages = mutableListOf<Message>()

    fun createList(mes: String): MutableList<Message> {

        val message = Message(
            mes
        )
        messages.add(message)
        return messages
    }
}

data class Message(
    val text: String
)
