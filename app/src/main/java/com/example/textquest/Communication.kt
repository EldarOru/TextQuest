package com.example.textquest

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface Communication {

    interface Observe {
        fun observe(owner: LifecycleOwner, observer: Observer<ScreenUi>)
    }

    interface SetValue : Mapper<ScreenUi, Unit>

    interface Mutable : Observe, SetValue

    class Base : Mutable {
        private val liveData = MutableLiveData<ScreenUi>()

        override fun map(data: ScreenUi) {
            liveData.value = data
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<ScreenUi>) =
            liveData.observe(owner, observer)
    }
}

interface Mapper<S, R> {
    fun map(data: S): R
}