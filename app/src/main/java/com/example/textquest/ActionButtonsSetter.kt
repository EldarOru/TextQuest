package com.example.textquest

import android.content.Context
import android.widget.Button
import android.widget.LinearLayout

interface ActionButtonsSetter {

    fun setActionButtons(context: Context, linearLayout: LinearLayout, action: ActionUi)

    class Base: ActionButtonsSetter {

        override fun setActionButtons(context: Context, linearLayout: LinearLayout, action: ActionUi) {
                val actionButton = Button(context).apply {
                    text = action.actionText
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    setOnClickListener {
                        action.actionCallback.moveToScreen(action.actionId)
                    }
                }
                linearLayout.addView(actionButton)
        }
    }
}