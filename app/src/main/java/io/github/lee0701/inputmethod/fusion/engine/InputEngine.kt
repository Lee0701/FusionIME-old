package io.github.lee0701.inputmethod.fusion.engine

import io.github.lee0701.inputmethod.fusion.event.KeyEvent

interface InputEngine {
    fun init()
    fun destroy()
    fun onKeyEvent(keyEvent: KeyEvent)
}