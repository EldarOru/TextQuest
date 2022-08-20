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
    private lateinit var recyclerView: RecyclerView

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
            it.getStory()[it.getStoryCount()].showActionButtons(context = baseContext, linearLayout = actionLayout)
            recyclerView.scrollToPosition(it.getStoryCount())
        }
    }

    private fun setRecyclerView() {
        recyclerView = findViewById(R.id.text_rv)
        recyclerView.layoutManager = LinearLayoutManager(this).apply {
            reverseLayout = false
            stackFromEnd = true
        }
        mainAdapter = MainAdapter()
        recyclerView.adapter = mainAdapter
    }

}