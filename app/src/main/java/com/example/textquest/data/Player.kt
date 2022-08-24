package com.example.textquest.data

interface Player {

    fun getItem(item: Item)

    class Base(
        private val name: String,
        private val items: ArrayList<Item>,
        private val progress: String,
        private val char: HashMap<Characteristic, Int>
    ): Player {

        override fun getItem(item: Item) {
            items.add(item)
        }

    }
}

class Item(
    private val key: String,
    private val name: String)

sealed class Characteristic {

    object Brave: Characteristic()

    object Chaotic: Characteristic()

}