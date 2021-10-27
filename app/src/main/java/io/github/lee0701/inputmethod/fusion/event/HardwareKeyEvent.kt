package io.github.lee0701.inputmethod.fusion.event

data class HardwareKeyEvent(
    val type: Type,
    override val keyCode: Int?,
    override val char: Char?,
    override val text: String?,
    val modifierState: ModifierState,
    val repeatCount: Int = 0,
): KeyEvent {
    enum class Type {
        PRESS, RELEASE, REPEAT
    }
}