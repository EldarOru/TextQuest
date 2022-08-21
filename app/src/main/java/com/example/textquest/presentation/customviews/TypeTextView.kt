package com.example.textquest.presentation.customviews

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class TypeTextView(context: Context, attributeSet: AttributeSet) :
    AppCompatTextView(context, attributeSet) {

    private var mText: CharSequence? = null
    private var mIndex = 0
    private val mDelay: Long = 150

    private val mHandler = Handler(Looper.getMainLooper())

    private val characterAdder = Runnable {
        text = mText?.subSequence(0, mIndex++)
    }

    fun animateText(txt: CharSequence) {
        mText = txt
        mIndex = 0

        text = ""
        mHandler.removeCallbacks(characterAdder)
        mHandler.postDelayed(characterAdder, mDelay)
    }
}