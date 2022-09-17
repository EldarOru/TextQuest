package com.example.textquest.data

import android.content.Context
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.content.res.AppCompatResources
import com.example.textquest.R

interface ActionButtonsSetter {

    fun setActionButtons(context: Context, linearLayout: LinearLayout, action: ActionUi)

    class Base : ActionButtonsSetter {

        //TODO RecyclerView instead of linearlayout
        override fun setActionButtons(
            context: Context,
            linearLayout: LinearLayout,
            action: ActionUi
        ) {
            val actionButton = Button(context, null, 0, R.style.ActionButton).apply {
                text = action.actionText

                setOnClickListener {
                    action.actionCallback.moveToScreen(action.actionId)
                }
            }
            linearLayout.addView(actionButton)
        }
    }
}