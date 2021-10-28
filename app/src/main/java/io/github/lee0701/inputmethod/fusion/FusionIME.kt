package io.github.lee0701.inputmethod.fusion

import android.inputmethodservice.InputMethodService
import android.view.View
import android.view.inputmethod.EditorInfo
import io.github.lee0701.inputmethod.fusion.engine.InputEngine
import io.github.lee0701.inputmethod.fusion.engine.RawInputEngine
import io.github.lee0701.inputmethod.fusion.event.KeyEvent
import io.github.lee0701.inputmethod.fusion.hardkeyboard.HardKeyboard
import io.github.lee0701.inputmethod.fusion.hardkeyboard.RawHardKeyboard
import io.github.lee0701.inputmethod.fusion.softkeyboard.BasicSoftKeyboard
import io.github.lee0701.inputmethod.fusion.softkeyboard.BasicSoftKeyboardConfig
import io.github.lee0701.inputmethod.fusion.softkeyboard.SoftKeyboard
import io.github.lee0701.inputmethod.fusion.softkeyboard.layouts.SoftLayout
import io.github.lee0701.inputmethod.fusion.softkeyboard.themes.BasicSoftKeyboardTheme

class FusionIME: InputMethodService() {

    lateinit var softKeyboard: SoftKeyboard
    lateinit var hardKeyboard: HardKeyboard
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
        ) { onKeyEvent(it) }
        hardKeyboard = RawHardKeyboard()
        inputEngine = RawInputEngine()
    }

    override fun onStartInput(attribute: EditorInfo?, restarting: Boolean) {
        super.onStartInput(attribute, restarting)
        inputEngine.inputConnection = currentInputConnection
    }

    override fun onCreateInputView(): View? {
        return softKeyboard.initView(this)
    }

    override fun onKeyDown(keyCode: Int, event: android.view.KeyEvent): Boolean {
        val convertedEvent = hardKeyboard.convertKeyEvent(event)
        onKeyEvent(convertedEvent)
        return true
    }

    override fun onKeyUp(keyCode: Int, event: android.view.KeyEvent): Boolean {
        val convertedEvent = hardKeyboard.convertKeyEvent(event)
        onKeyEvent(convertedEvent)
        return true
    }

    private fun onKeyEvent(event: KeyEvent) {
        inputEngine.onKeyEvent(event)
    }
}