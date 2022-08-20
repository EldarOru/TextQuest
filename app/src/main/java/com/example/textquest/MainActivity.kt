package com.example.textquest

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.textquest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var actionLayout: LinearLayout
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRecyclerView()
        actionLayout = findViewById(R.id.action_layout)
        viewModel = (application as ProvideViewModel).provideViewModel()

        viewModel.observe(this) {
            it.getStory().apply {
                mainAdapter.update(this)

                /*
                show(textView)
                showActionButtons(context = baseContext, linearLayout = actionLayout)

                 */
            }
            it.getStory()[it.getStory().size-1].showActionButtons(context = baseContext, linearLayout = actionLayout)
        }
    }


    private fun setRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.text_rv)
        val linearLayoutManager = LinearLayoutManager(this).apply {
            reverseLayout = false
            stackFromEnd = true
        }
        recyclerView.layoutManager = linearLayoutManager
        mainAdapter = MainAdapter()
        recyclerView.adapter = mainAdapter
    }

}