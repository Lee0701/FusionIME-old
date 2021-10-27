package io.github.lee0701.inputmethod.fusion.softkeyboard

import android.content.Context
import android.view.View

interface SoftKeyboard {

    var shift: Int
    var alt: Int

    fun initView(context: Context): View?
    fun getView(): View?

    fun reset()

    fun updateLabels(labels: Map<Int, String>)

}
