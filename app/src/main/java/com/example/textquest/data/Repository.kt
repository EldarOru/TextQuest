package com.example.textquest.data

import android.content.Context
import androidx.annotation.RawRes
import com.example.textquest.R
import com.example.textquest.data.WriteInternalStorage.Companion.FILE_NAME
import com.google.gson.Gson
import java.io.File
import java.io.FileReader
import java.io.FileWriter

interface Repository {

    fun nextScreen(id: String): ScreenData

    //TODO CHANGE
    fun getPlayerJsonFromFile(fileName: String): FileCondition

    fun createPlayerFromJson(playerJson: String): Player

    fun createJsonFromPlayer(player: Player): String

    fun savePlayerJsonToFile(playerJson: String)

    class Base(
        readRawResource: ReadRawResource,
        private val gson: Gson,
        private val writeInternalStorage: WriteInternalStorage,
        private val readInternalStorage: ReadInternalStorage
    ) : Repository {

        private val screensData: ScreensData = gson.fromJson(
            readRawResource.read(R.raw.quest),
            ScreensData::class.java
        )

        override fun getPlayerJsonFromFile(fileName: String): FileCondition {
            return readInternalStorage.read(fileName)
        }

        override fun createPlayerFromJson(playerJson: String): Player {
            return gson.fromJson(playerJson, Player.Base::class.java)
        }

        override fun createJsonFromPlayer(player: Player): String {
            return gson.toJson(player)
        }

        override fun savePlayerJsonToFile(playerJson: String) {
            writeInternalStorage.write(FILE_NAME, playerJson)
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

interface ReadInternalStorage {

    fun read(fileName: String): FileCondition

    class Base(private val context: Context) : ReadInternalStorage {
        override fun read(fileName: String): FileCondition {
            val file = File(context.filesDir, fileName)
            if (!file.exists()) {
                return FileCondition.Fail
            }
            val fileReader = FileReader(file)
            val info = fileReader.readText()
            fileReader.close()
            return FileCondition.Success(info)
        }
    }
}

interface WriteInternalStorage {

    fun write(fileName: String, text: String)

    class Base(private val context: Context) : WriteInternalStorage {

        override fun write(fileName: String, text: String) {
            val file = File(context.filesDir, fileName)
            if (!file.exists()) {
                file.createNewFile()
            }
            FileWriter(file).apply {
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