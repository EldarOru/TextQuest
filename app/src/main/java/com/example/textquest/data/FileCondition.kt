package com.example.textquest.data

import java.io.File

sealed class FileCondition {

    class Success(val string: String) : FileCondition()

    object Fail : FileCondition()
}