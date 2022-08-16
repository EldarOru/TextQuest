package com.example.textquest

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class MainViewModel(
    private val communication: Communication.Mutable,
    private val repository: Repository
) : ViewModel(), Communication.Observe, ActionCallback {

    private val mapper = ScreenDataToUi(this)

    init {
        moveToScreen("1")
    }

    override fun moveToScreen(id: String) {
        val screenData = repository.nextScreen(id)
        val screenUi = mapper.map(screenData)
        communication.map(screenUi)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<ScreenUi>) =
        communication.observe(owner, observer)

    /*
    val liveData = MutableLiveData<ScreenUi>()

    fun nextScreen(id: String) {
        val screenUi = repository.nextScreen(id)
        liveData.value = screenUi
    }

     */
}

class ScreenDataToUi(private val actionCallback: ActionCallback) : Mapper<ScreenData, ScreenUi> {
    override fun map(data: ScreenData): ScreenUi {
        val actions = data.actionsList.map { actionData ->
            ActionUi(actionCallback, actionData.screenId, actionData.text, ActionButtonsSetter.Base())
        }
        return ScreenUi(data.text, actions)
    }
}