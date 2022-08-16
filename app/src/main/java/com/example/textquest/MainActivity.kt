package com.example.textquest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import com.google.gson.Gson

class MainActivity : AppCompatActivity(), ActionCallback {

    private lateinit var viewModel: MainViewModel
    private lateinit var actionLayout: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
        actionLayout = findViewById(R.id.action_layout)
        viewModel = MainViewModel(Repository.Base(
            this,
            ReadRawResource.Base(context = applicationContext),
            Gson(),

        ))

        viewModel.liveData.observe(this) {
            it.apply {
                show(textView)
                showActionButtons(context = baseContext, linearLayout = actionLayout)
            }
        }

        viewModel.nextScreen("1")

    }

    override fun moveToScreen(id: String) {
        actionLayout.removeAllViews()
        viewModel.nextScreen(id)
    }
}