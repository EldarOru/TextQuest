package com.example.textquest.core

interface Read<A, B> {

    fun read(obj: A): B
}