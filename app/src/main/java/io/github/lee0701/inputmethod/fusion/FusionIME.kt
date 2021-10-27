package io.github.lee0701.inputmethod.fusion

import android.inputmethodservice.InputMethodService
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import io.github.lee0701.inputmethod.fusion.softkeyboard.BasicSoftKeyboard
import io.github.lee0701.inputmethod.fusion.softkeyboard.BasicSoftKeyboardConfig
import io.github.lee0701.inputmethod.fusion.softkeyboard.SoftKeyboard
import io.github.lee0701.inputmethod.fusion.softkeyboard.layouts.SoftLayout
import io.github.lee0701.inputmethod.fusion.softkeyboard.themes.BasicSoftKeyboardTheme

class FusionIME: InputMethodService() {

    lateinit var softKeyboard: SoftKeyboard

    override fun onCreate() {
        super.onCreate()
        val config = BasicSoftKeyboardConfig(
            keyHeight = 50f,
        )
        softKeyboard = BasicSoftKeyboard(
            config,
            SoftLayout.LAYOUT_10COLS_MOBILE,
            BasicSoftKeyboardTheme.WHITE
        ) { event ->
            println(event)
        }
    }

    override fun onCreateInputView(): View? {
        return softKeyboard.initView(this)
    }
}