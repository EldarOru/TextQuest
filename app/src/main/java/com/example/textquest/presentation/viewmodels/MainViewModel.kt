package com.example.textquest.presentation.viewmodels

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.textquest.core.Communication
import com.example.textquest.core.Mapper
import com.example.textquest.data.*
import com.example.textquest.data.WriteInternalStorage.Companion.FILE_NAME

class MainViewModel(
    private val communication: Communication.Mutable<ScreenStory>,
    private val repository: Repository
) : ViewModel(), Communication.Observe<ScreenStory>, ActionCallback {

    private val mapper = ScreenDataToUi(this)
    private val screenStory = ScreenStory()
    private var player: Player? = null

    init {
        //getPlayer()
        moveToScreen("1")
    }

    override fun moveToScreen(id: String) {
        val screenData = repository.nextScreen(id)
        val screenUi = mapper.map(screenData)
        screenStory.addScreenUi(screenUi)
        communication.map(screenStory)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<ScreenStory>) =
        communication.observe(owner, observer)

    private fun getPlayer() {
        when(val res = repository.getUserJson(FILE_NAME)) {
            is FileCondition.Success -> player = repository.createPlayer(res.string)
            is FileCondition.Fail -> createPlayer()
        }
    }

    fun createPlayer() {

    }

}

//actionCallback is a ViewModel with implementation of ActionCallback
class ScreenDataToUi(private val actionCallback: ActionCallback) : Mapper<ScreenData, ScreenUi> {
    override fun map(data: ScreenData): ScreenUi {
        val actions = data.actionsList.map { actionData ->
            ActionUi(
                actionCallback,
                actionData.screenId,
                actionData.text,
                ActionButtonsSetter.Base()
            )
        }
        return ScreenUi(id = data.id, fullText = data.text, teller = data.teller, actions = actions)
    }
}