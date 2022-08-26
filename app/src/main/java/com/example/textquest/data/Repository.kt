package com.example.textquest.data

import android.content.Context
import android.util.Log
import androidx.annotation.RawRes
import com.example.textquest.R
import com.example.textquest.data.WriteInfo.Companion.FILE_NAME
import com.google.gson.Gson
import java.io.File
import java.io.FileWriter

interface Repository {

    fun nextScreen(id: String): ScreenData

    class Base(
        readRawResource: ReadRawResource,
        gson: Gson,
        writeInfo: WriteInfo
    ) : Repository {

        private val screensData: ScreensData = gson.fromJson(
            readRawResource.read(R.raw.quest),
            ScreensData::class.java
        )

        init {
            val player: Player = Player.Base(name = "ELDAR",
                progress = "1",
                items = arrayListOf(Item("1", "Hammer")))
            writeInfo.write(writeInfo.makeFile(FILE_NAME), gson.toJson(player))
        }

        override fun nextScreen(id: String): ScreenData {
            return screensData.screensList.find {
                it.id == id
            } ?: ScreenData("-1", "Teller", "error", listOf())
        }
    }
}

interface ReadRawResource {

    fun read(@RawRes id: Int): String

    class Base(private val context: Context) : ReadRawResource {
        override fun read(id: Int): String =
            context.resources.openRawResource(id).bufferedReader().readText()
    }
}

interface WriteInfo {

    fun makeFile(fileName: String) : File

    fun write(fileName: File, text: String)

    class Base(private val context: Context) : WriteInfo {

        override fun makeFile(fileName: String) = File(context.filesDir, fileName)

        override fun write(fileName: File, text: String) {
            if (!fileName.exists()) {
                fileName.createNewFile()
            }
            FileWriter(fileName).apply {
                append(text)
                flush()
                close()
            }
        }
    }

    companion object {
        const val FILE_NAME = "playerinfo.json"
    }
}