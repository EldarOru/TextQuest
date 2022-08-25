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
    private val mDelay: Long = 20
    private var isAnimate = false

    private val mHandler = Handler(Looper.getMainLooper())

    private val characterAdder = object : Runnable {
        override fun run() {
            text = mText?.subSequence(0, mIndex++)
            if (mIndex < (mText?.length ?: 0) && isAnimate) {
                mHandler.postDelayed(this, mDelay)
            } else isAnimate = false
        }
    }

    fun animateStop() {
        mHandler.removeCallbacks(characterAdder)
        isAnimate = false
        text = mText
    }

    fun animateStart(txt: CharSequence) {
        mText = txt
        mIndex = 0

        text = " "
        isAnimate = true
        mHandler.postDelayed(characterAdder, mDelay)
    }
}