package com.example.textquest

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var actionLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
        actionLayout = findViewById(R.id.action_layout)
        viewModel = (application as ProvideViewModel).provideViewModel()

        viewModel.observe(this) {
            it.apply {
                show(textView)
                showActionButtons(context = baseContext, linearLayout = actionLayout)
            }
        }
    }
}