package com.example.textquest.data

import com.google.gson.annotations.SerializedName

interface Player {

    fun getItem(item: Item)

    class Base(
        @SerializedName("playerName")
        private val name: String,
        @SerializedName("items")
        private val items: ArrayList<Item>,
        @SerializedName("progress")
        private val progress: String,
        //@SerializedName("characteristic")
        //private val char: HashMap<Characteristic, Int>
    ): Player {

        override fun getItem(item: Item) {
            items.add(item)
        }
    }
}

class Item(
    private val key: String,
    private val name: String)

/*
sealed class Characteristic(name: String) {

    object Brave: Characteristic("Brave")

    object Chaotic: Characteristic("Chaotic")
}
 */
