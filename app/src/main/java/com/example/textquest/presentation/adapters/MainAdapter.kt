package com.example.textquest.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.example.textquest.data.ScreenUi
import com.example.textquest.databinding.QuestTextViewBinding
import com.example.textquest.databinding.QuestTypeTextBinding
import com.example.textquest.presentation.customviews.TypeTextView
import java.lang.Exception

class MainAdapter : ListAdapter<ScreenUi, RecyclerView.ViewHolder>(AsyncDifferConfig.Builder(DiffCallback()).build()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            typeText -> TypeTextVH(
                QuestTypeTextBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            justText -> TextVH(
                QuestTextViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw Exception()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is TypeTextVH -> {
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
        return if (position == currentList.size-1) {
            typeText
        } else justText
    }

    override fun getItemCount() = currentList.size

    class TextVH(val questTextView: QuestTextViewBinding) :
        RecyclerView.ViewHolder(questTextView.root)

    class TypeTextVH(val questTypeTextView: QuestTypeTextBinding) :
        RecyclerView.ViewHolder(questTypeTextView.root)

    companion object {
        const val typeText = 0
        const val justText = 1
    }


    private class DiffCallback : DiffUtil.ItemCallback<ScreenUi>() {

        override fun areItemsTheSame(oldItem: ScreenUi, newItem: ScreenUi) =
            oldItem.sameId(newItem)

        override fun areContentsTheSame(oldItem: ScreenUi, newItem: ScreenUi) =
            oldItem.sameScreenUi(newItem)
    }
}