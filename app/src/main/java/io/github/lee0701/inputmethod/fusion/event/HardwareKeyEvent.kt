package io.github.lee0701.inputmethod.fusion.event

data class HardwareKeyEvent(
    val originalEvent: android.view.KeyEvent,
    val type: Type = Type.valueOf(originalEvent.action),
    override val keyCode: Int? = originalEvent.keyCode,
    override val char: Char? = originalEvent.unicodeChar.toChar(),
    override val text: String?,
    val modifierState: ModifierState = ModifierState(originalEvent),
    val repeatCount: Int = 0,
): KeyEvent {
    enum class Type {
        NONE, PRESS, RELEASE, REPEAT;
        companion object {
            fun valueOf(value: Int): Type {
                return when(value) {
                    android.view.KeyEvent.ACTION_DOWN -> PRESS
                    android.view.KeyEvent.ACTION_UP -> RELEASE
                    else -> NONE
                }
            }
        }
    }
}