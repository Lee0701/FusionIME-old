package io.github.lee0701.inputmethod.fusion.event

data class HardKeyEvent(
    val type: Type,
    override val keyCode: Int?,
    override val char: Char?,
    override val text: String?,
    override val modifierState: ModifierState,
): KeyEvent {
    enum class Type {
        NONE, PRESS, RELEASE, REPEAT
    }
}