package com.example.textquest

import com.google.gson.annotations.SerializedName

class ScreensData(
    @SerializedName("screens")
    val screensList: List<ScreenData>
)

class ScreenData(
    @SerializedName("id")
    val id: String,
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