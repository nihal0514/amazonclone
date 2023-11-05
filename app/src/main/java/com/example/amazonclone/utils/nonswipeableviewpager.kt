package com.example.amazonclone.utils

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class NonSwipeableViewPager(context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {

    override fun onInterceptTouchEvent(event: MotionEvent?): Boolean {
        // Disable swiping by always returning false
        return false
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        // Disable swiping by always returning false
        return false
    }


}
