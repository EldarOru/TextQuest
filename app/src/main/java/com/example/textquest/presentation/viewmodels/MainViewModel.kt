package com.example.textquest.presentation.viewmodels

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.textquest.core.Communication
import com.example.textquest.core.Mapper
import com.example.textquest.data.*
import com.example.textquest.data.WriteInternalStorage.Companion.FILE_NAME

class MainViewModel(
    private val communication: Communication.Mutable<MainGameInformation>,
    private val repository: Repository
) : ViewModel(), Communication.Observe<MainGameInformation>, ActionCallback, DialogInteract {

    private val mapper = ScreenDataToUi(this)
    private val mainGameInformation = MainGameInformation()

    override fun moveToScreen(id: String) {
        val screenData = repository.nextScreen(id)
        val screenUi = mapper.map(screenData)
        mainGameInformation.addScreenUi(screenUi)
        communication.map(mainGameInformation)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<MainGameInformation>) =
        communication.observe(owner, observer)

    fun getPlayer() = repository.getPlayerJsonFromFile(FILE_NAME)

    fun setPlayer(info: String) {
        mainGameInformation.setPlayer(repository.createPlayerFromJson(info))
        moveToScreen(mainGameInformation.getPlayer()?.returnProgress() ?: "1")
    }

    override fun onDialogInteract(string: String) {
        repository.savePlayerJsonToFile(
            repository.createJsonFromPlayer(
                Player.Base(name = string, null, "1")))
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

interface DialogInteract {

    fun onDialogInteract(string: String)
}