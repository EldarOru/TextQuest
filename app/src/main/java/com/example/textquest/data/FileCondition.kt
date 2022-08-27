package com.example.textquest.data

sealed class FileCondition {

    class Success(val string: String) : FileCondition()

    object Fail : FileCondition()
}