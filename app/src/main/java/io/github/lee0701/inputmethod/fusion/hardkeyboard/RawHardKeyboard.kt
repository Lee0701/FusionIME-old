package io.github.lee0701.inputmethod.fusion.hardkeyboard

import io.github.lee0701.inputmethod.fusion.event.KeyEvent
import android.view.KeyEvent.*
import io.github.lee0701.inputmethod.fusion.event.KeyCode
import io.github.lee0701.inputmethod.fusion.event.ModifierState
import io.github.lee0701.inputmethod.fusion.event.SoftwareKeyEvent

class RawHardKeyboard: HardKeyboard {

    override fun reset() {

    }

    override fun convertKeyEvent(keyEvent: android.view.KeyEvent): KeyEvent {
        val type = when(keyEvent.action) {
            ACTION_DOWN -> {
                if(keyEvent.repeatCount > 0) SoftwareKeyEvent.Type.REPEAT
                else SoftwareKeyEvent.Type.PRESS
            }
            ACTION_UP -> SoftwareKeyEvent.Type.RELEASE
            else -> SoftwareKeyEvent.Type.NONE
        }
        val keyCode = convertKeyCode(keyEvent.keyCode)
        val char = if(keyEvent.isPrintingKey) keyEvent.unicodeChar.toChar() else null
        val modifierState = ModifierState(keyEvent)
        return SoftwareKeyEvent(type, keyCode, char, null, modifierState)
    }

    private fun convertKeyCode(keyCode: Int): Int {
        return when(keyCode) {
            in KEYCODE_0 .. KEYCODE_9 -> keyCode - KEYCODE_0 + '0'.code
            in KEYCODE_A .. KEYCODE_Z -> keyCode - KEYCODE_A + 'a'.code
            KEYCODE_SPACE -> KeyCode.SPACE
            KEYCODE_ENTER -> KeyCode.ENTER
            KEYCODE_DEL -> KeyCode.DEL
            KEYCODE_SHIFT_LEFT -> KeyCode.SHIFT_LEFT
            KEYCODE_SHIFT_RIGHT -> KeyCode.SHIFT_RIGHT
            KEYCODE_ALT_LEFT -> KeyCode.ALT_LEFT
            KEYCODE_ALT_RIGHT -> KeyCode.ALT_RIGHT
            else -> 0
        }
    }

}