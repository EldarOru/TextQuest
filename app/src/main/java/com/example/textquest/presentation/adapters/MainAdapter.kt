package com.example.textquest.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.textquest.data.ScreenUi
import com.example.textquest.databinding.QuestTextBinding
import com.example.textquest.databinding.QuestTypeTextBinding
import com.example.textquest.presentation.customviews.TypeTextView

class MainAdapter : ListAdapter<ScreenUi, RecyclerView.ViewHolder>(
    AsyncDifferConfig.Builder(DiffCallback()).build()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_TEXT_VIEW -> TypeTextVH(
                QuestTypeTextBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            JUST_TEXT_VIEW -> TextVH(
                QuestTextBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw Exception()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TypeTextVH -> {
                //holder.questTypeTextView.tellerTv.text = currentList[position].getTeller()
                holder.questTypeTextView.typeMainTv.apply {
                    animateStart(currentList[position].getFullText())
                    setOnClickListener { (it as TypeTextView).animateStop() }
                }

            }
            is TextVH -> {
                holder.questTextView.mainTv.text = currentList[position].getFullText()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == currentList.size - 1) {
            TYPE_TEXT_VIEW
        } else JUST_TEXT_VIEW
    }

    override fun getItemCount() = currentList.size

    class TextVH(val questTextView: QuestTextBinding) :
        RecyclerView.ViewHolder(questTextView.root)

    class TypeTextVH(val questTypeTextView: QuestTypeTextBinding) :
        RecyclerView.ViewHolder(questTypeTextView.root)

    companion object {
        const val TYPE_TEXT_VIEW = 0
        const val JUST_TEXT_VIEW = 1
    }

    private class DiffCallback : DiffUtil.ItemCallback<ScreenUi>() {

        override fun areItemsTheSame(oldItem: ScreenUi, newItem: ScreenUi) =
            oldItem.sameId(newItem)

        override fun areContentsTheSame(oldItem: ScreenUi, newItem: ScreenUi) =
            oldItem.sameScreenUi(newItem)
    }
}