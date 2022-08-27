package com.example.textquest.data

import android.content.Context
import android.widget.LinearLayout

class ScreenStory {

    private val story = ArrayList<ScreenUi>()
    fun addScreenUi(screenUi: ScreenUi) {
        story.add(screenUi)
    }

    fun getStory(): ArrayList<ScreenUi> = story.clone() as ArrayList<ScreenUi>

    fun getLastStory() = story[story.size - 1]
}

data class ScreenUi(
    private val id: String,
    private val teller: String,
    private val fullText: String,
    private val actions: List<ActionUi>
) {
    fun sameId(other: ScreenUi) = this.id == other.id

    fun sameScreenUi(other: ScreenUi): Boolean = this.id == other.id
            && this.actions == other.actions
            && this.fullText == other.fullText

    fun getFullText() = fullText

    fun getTeller() = teller

    fun showActionButtons(context: Context, linearLayout: LinearLayout) {
        linearLayout.removeAllViews()
        for (action in actions) {
            action.setActionButtons(context, linearLayout)
        }
    }

    /*
    fun show(textView: TextView) = textView.run {
        text = fullText
        movementMethod = LinkMovementMethod.getInstance()
        highlightColor = Color.TRANSPARENT
    }
     */
}

class ActionUi(
    val actionCallback: ActionCallback,
    val actionId: String,
    val actionText: String,
    private val actionButtonsSetter: ActionButtonsSetter
) {

    fun setActionButtons(context: Context, linearLayout: LinearLayout) {
        actionButtonsSetter.setActionButtons(context, linearLayout, this)
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
