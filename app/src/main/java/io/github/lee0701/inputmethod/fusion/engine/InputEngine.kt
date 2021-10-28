package io.github.lee0701.inputmethod.fusion.engine

import android.view.inputmethod.InputConnection
import io.github.lee0701.inputmethod.fusion.event.KeyEvent

interface InputEngine {
    var inputConnection: InputConnection?

    fun reset()

    fun onKeyEvent(keyEvent: KeyEvent)
}