package com.example.textquest.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.textquest.data.ScreenUi
import com.example.textquest.databinding.QuestTextViewBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.TextVH>() {

    private val list = mutableListOf<ScreenUi>()

    fun update(newList: List<ScreenUi>) {
        list.clear()
        list.addAll(newList)

        notifyDataSetChanged() //todo diffutilcallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextVH {
        return TextVH(QuestTextViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: TextVH, position: Int) {
        holder.bind(list[position].getFullText())
    }

    override fun getItemCount() = list.size


    class TextVH(val questTextView: QuestTextViewBinding) :
        RecyclerView.ViewHolder(questTextView.root) {
            fun bind(string: String) {
                questTextView.mainTv.text = string
            }
        }

}