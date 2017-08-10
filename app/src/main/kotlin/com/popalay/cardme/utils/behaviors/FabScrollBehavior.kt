package com.popalay.cardme.utils.behaviors

import android.content.Context
import android.os.Handler
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageButton

class FabScrollBehavior(context: Context, attrs: AttributeSet) : SnackbarFabBehavior(context, attrs) {

    private val handler: Handler by lazy { Handler() }

    override fun onStopNestedScroll(coordinatorLayout: CoordinatorLayout,
                                    child: ImageButton,
                                    target: View,
                                    type: Int) {
        super.onStopNestedScroll(coordinatorLayout, child, target, type)
        handler.postDelayed({ show(child) }, 500L)
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout,
                                child: ImageButton,
                                target: View,
                                dxConsumed: Int,
                                dyConsumed: Int,
                                dxUnconsumed: Int,
                                dyUnconsumed: Int,
                                type: Int) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type)
        if (dyConsumed == 0) return
        hide(child)
    }

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout,
                                     child: ImageButton,
                                     directTargetChild: View,
                                     target: View,
                                     axes: Int,
                                     type: Int): Boolean {
        return axes and ViewCompat.SCROLL_AXIS_VERTICAL != 0

    }

    private fun show(child: ImageButton) {
        child.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .setDuration(200L)
                .start()
    }

    private fun hide(child: ImageButton) {
        child.animate()
                .alpha(0f)
                .scaleX(0f)
                .scaleY(0f)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .setDuration(200L)
                .start()
    }
}