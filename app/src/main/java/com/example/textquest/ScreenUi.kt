package com.example.textquest

import android.content.Context
import android.graphics.Color
import android.text.Layout
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class ScreenUi(
    fullText: String,
    actions: List<ActionUi>,
    context: Context,
    scrollView: LinearLayout
) {

    private val spannableString = SpannableString(fullText)

    init {
        for (action in actions) {
            action.setActionButtons(context = context, layout = scrollView)
        }
    }

    fun show(textView: TextView) = textView.run {
        text = spannableString
        movementMethod = LinkMovementMethod.getInstance()
        highlightColor = Color.TRANSPARENT
    }
}

class ActionUi(
    private val actionCallback: ActionCallback,
    private val actionId: String,
    private val actionText: String
) {

    fun setActionButtons(layout: LinearLayout, context: Context) {
        val actionButton = Button(context).apply {
            text = actionText
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            setOnClickListener {
                actionCallback.moveToScreen(actionId)
            }
        }
        layout.addView(actionButton)
    }
    /*
    fun setSpan(spannableString: SpannableString, fullText: String) {
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) = actionCallback.moveToScreen(id)

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = true
                ds.color = Color.parseColor("#FF0000")
            }
        }

        val indexOf = fullText.indexOf(text)
        spannableString.setSpan(
            clickableSpan,
            indexOf,
            indexOf + text.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }

     */
}

interface ActionCallback {

    fun moveToScreen(id: String)
}
