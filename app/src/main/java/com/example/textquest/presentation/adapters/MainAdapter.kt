package com.example.textquest.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.textquest.data.ScreenUi
import com.example.textquest.databinding.QuestTextViewBinding
import com.example.textquest.databinding.QuestTypeTextBinding
import org.w3c.dom.Text

class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list = mutableListOf<ScreenUi>()

    fun update(newList: List<ScreenUi>) {
        list.clear()
        list.addAll(newList)

        notifyDataSetChanged() //todo diffutilcallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == typeText) {
            TypeTextVH(QuestTypeTextBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false))
        } else return TextVH(QuestTextViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is TypeTextVH -> {
                holder.questTypeTextView.typeMainTv.animateText(list[position].getFullText())
            }
            is TextVH -> {
                holder.questTextView.mainTv.text = list[position].getFullText()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == list.size-1) {
            typeText
        } else justText
    }

    override fun getItemCount() = list.size

    class TextVH(val questTextView: QuestTextViewBinding) :
        RecyclerView.ViewHolder(questTextView.root)

    class TypeTextVH(val questTypeTextView: QuestTypeTextBinding) :
        RecyclerView.ViewHolder(questTypeTextView.root)

    companion object {
        const val typeText = 0
        const val justText = 1
    }
}