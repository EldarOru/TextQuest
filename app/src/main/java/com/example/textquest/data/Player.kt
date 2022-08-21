package com.example.textquest.data

interface Player {

    fun getItem(item: Item)

    class Base(
        private val name: String,
        private val items: ArrayList<String>,
        private val progress: String
    ): Player {

        override fun getItem(item: Item) {
            TODO("Not yet implemented")
        }
    }
}

class Item(
    private val key: String,
    private val name: String)