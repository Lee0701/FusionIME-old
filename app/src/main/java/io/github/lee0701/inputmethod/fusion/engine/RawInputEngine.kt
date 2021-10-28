package io.github.lee0701.inputmethod.fusion.engine

import android.view.inputmethod.InputConnection
import io.github.lee0701.inputmethod.fusion.event.KeyCode
import io.github.lee0701.inputmethod.fusion.event.KeyEvent
import io.github.lee0701.inputmethod.fusion.event.SoftwareKeyEvent

class RawInputEngine: InputEngine {

    override var inputConnection: InputConnection? = null
    private var shift: Boolean = false

    override fun reset() {

    }

    override fun onKeyEvent(keyEvent: KeyEvent) {
        val text = keyEvent.text
            ?: keyEvent.char?.toString()
            ?: keyEvent.keyCode?.let { if(it <= 0) null else Char(it).toString() }
        if(keyEvent is SoftwareKeyEvent) {
            when(keyEvent.keyCode) {
                KeyCode.SHIFT, KeyCode.SHIFT_LEFT, KeyCode.SHIFT_RIGHT -> {
                    shift = keyEvent.type == SoftwareKeyEvent.Type.PRESS
                }
                KeyCode.DEL -> {
                    if(keyEvent.type == SoftwareKeyEvent.Type.PRESS
                        || keyEvent.type == SoftwareKeyEvent.Type.REPEAT) {
                        inputConnection?.deleteSurroundingText(1, 0)
                    }
                }
                KeyCode.SPACE -> {
                    if(keyEvent.type == SoftwareKeyEvent.Type.PRESS
                        || keyEvent.type == SoftwareKeyEvent.Type.REPEAT) {
                        inputConnection?.commitText(" ", 1)
                    }
                }
                KeyCode.ENTER -> {
                    if(keyEvent.type == SoftwareKeyEvent.Type.PRESS) {
                        inputConnection?.commitText("\n", 1)
                    }
                }
                else -> {
                    if(keyEvent.type == SoftwareKeyEvent.Type.PRESS) {
                        if(text != null) inputConnection?.commitText(if(shift) text.uppercase() else text, 1)
                    }
                }
            }
        }
    }
}