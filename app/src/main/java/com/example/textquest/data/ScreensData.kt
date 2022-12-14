package com.example.textquest.data

import com.google.gson.annotations.SerializedName

class ScreensData(
    @SerializedName("screens")
    val screensList: List<ScreenData>
)

class ScreenData(
    @SerializedName("id")
    val id: String,
    @SerializedName("teller")
    val teller: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("actions")
    val actionsList: List<ActionData>
)

class ActionData(
    @SerializedName("key")
    val text: String,
    @SerializedName("action")
    val screenId: String,

)