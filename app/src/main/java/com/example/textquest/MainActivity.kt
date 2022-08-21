package com.example.textquest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.textquest.presentation.fragments.ActionButtonsListFragment
import com.example.textquest.presentation.fragments.TextListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.main_fragment, TextListFragment())
            .add(R.id.bottom_fragment, ActionButtonsListFragment())
            .commit()
    }
}