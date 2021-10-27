package io.github.lee0701.inputmethod.fusion

import android.inputmethodservice.InputMethodService
import android.view.View
import android.view.inputmethod.EditorInfo
import io.github.lee0701.inputmethod.fusion.engine.InputEngine
import io.github.lee0701.inputmethod.fusion.engine.RawInputEngine
import io.github.lee0701.inputmethod.fusion.event.KeyEvent
import io.github.lee0701.inputmethod.fusion.event.SoftwareKeyEvent
import io.github.lee0701.inputmethod.fusion.softkeyboard.BasicSoftKeyboard
import io.github.lee0701.inputmethod.fusion.softkeyboard.BasicSoftKeyboardConfig
import io.github.lee0701.inputmethod.fusion.softkeyboard.SoftKeyboard
import io.github.lee0701.inputmethod.fusion.softkeyboard.layouts.SoftLayout
import io.github.lee0701.inputmethod.fusion.softkeyboard.themes.BasicSoftKeyboardTheme

class FusionIME: InputMethodService() {

    lateinit var softKeyboard: SoftKeyboard
    lateinit var inputEngine: InputEngine

    override fun onCreate() {
        super.onCreate()
        val config = BasicSoftKeyboardConfig(
            keyHeight = 50f,
        )
        softKeyboard = BasicSoftKeyboard(
            config,
            SoftLayout.LAYOUT_10COLS_MOBILE,
            BasicSoftKeyboardTheme.WHITE
        ) { onSoftKeyEvent(it) }
        inputEngine = RawInputEngine()
    }

    override fun onStartInput(attribute: EditorInfo?, restarting: Boolean) {
        super.onStartInput(attribute, restarting)
        inputEngine.inputConnection = currentInputConnection
    }

    override fun onCreateInputView(): View? {
        return softKeyboard.initView(this)
    }

    private fun onSoftKeyEvent(event: KeyEvent) {
        inputEngine.onKeyEvent(event)
    }
}