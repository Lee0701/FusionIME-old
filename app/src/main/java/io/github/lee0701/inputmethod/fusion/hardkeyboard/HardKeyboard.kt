package io.github.lee0701.inputmethod.fusion.hardkeyboard

import io.github.lee0701.inputmethod.fusion.event.KeyEvent

interface HardKeyboard {

    fun convertKeyEvent(keyEvent: android.view.KeyEvent): KeyEvent

    fun reset()

}