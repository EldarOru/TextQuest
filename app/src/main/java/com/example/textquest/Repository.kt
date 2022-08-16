package com.example.textquest

import android.content.Context
import android.widget.LinearLayout
import androidx.annotation.RawRes
import com.google.gson.Gson

interface Repository {

    fun nextScreen(id: String): ScreenUi

    class Base(
        private val actionCallback: ActionCallback,
        readRawResource: ReadRawResource,
        gson: Gson,
    ) : Repository {

        private val screensData: ScreensData = gson.fromJson(
            readRawResource.read(R.raw.quest),
            ScreensData::class.java
        )

        override fun nextScreen(id: String): ScreenUi {
            screensData.screensList.find {
                it.id == id
            }!!.let { screenData ->
                val actions = screenData.actionsList.map { actionData ->
                    ActionUi(actionCallback, actionData.screenId, actionData.text, ActionButtonsSetter.Base())
                }
                return ScreenUi(screenData.text, actions)
            }
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