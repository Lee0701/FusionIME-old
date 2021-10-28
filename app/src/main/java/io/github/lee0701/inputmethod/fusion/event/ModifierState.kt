package io.github.lee0701.inputmethod.fusion.event

import android.view.KeyEvent

data class ModifierState(
    val shift: Boolean = false,
    val alt: Boolean = false,
    val caps: Boolean = false,
    val control: Boolean = false,
    val meta: Boolean = false,
) {
    constructor(keyEvent: KeyEvent): this(keyEvent.isShiftPressed, keyEvent.isAltPressed, keyEvent.isCapsLockOn)
}