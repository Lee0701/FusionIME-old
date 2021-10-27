package io.github.lee0701.inputmethod.fusion.softkeyboard

import android.content.Context
import android.view.View
import android.widget.LinearLayout

class EmptySoftKeyboard: SoftKeyboard {

    override var shift = 0
    override var alt = 0

    var inputView: View? = null

    override fun initView(context: Context): View? {
        inputView = LinearLayout(context)
        return inputView
    }

    override fun getView(): View? {
        return inputView
    }

    override fun reset() {

    }

    override fun updateLabels(labels: Map<Int, String>) {

    }
}
