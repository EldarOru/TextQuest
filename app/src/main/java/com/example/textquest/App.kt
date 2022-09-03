package com.example.textquest

import android.app.Application
import com.example.textquest.core.Communication
import com.example.textquest.data.ReadInternalStorage
import com.example.textquest.data.ReadRawResource
import com.example.textquest.data.Repository
import com.example.textquest.data.WriteInternalStorage
import com.example.textquest.presentation.viewmodels.MainViewModel
import com.google.gson.Gson

class App : Application(), ProvideViewModel {

    private lateinit var viewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()

        viewModel = MainViewModel(
            Communication.Base(),
            Repository.Base(
                readRawResource = ReadRawResource.Base(this),
                writeInternalStorage = WriteInternalStorage(this),
                readInternalStorage = ReadInternalStorage(this),
                gson =  Gson(),
            )
        )
    }

    override fun provideViewModel(): MainViewModel {
        return viewModel
    }
}

interface ProvideViewModel {
    fun provideViewModel(): MainViewModel
}