package com.example.bluetoothchat


data class Message(

    val id: Long = System.currentTimeMillis(),

    val text: String,

    val isMine: Boolean,

    val time: Long = System.currentTimeMillis()

)
