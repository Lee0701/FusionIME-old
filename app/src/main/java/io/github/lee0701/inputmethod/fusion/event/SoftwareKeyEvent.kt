package io.github.lee0701.inputmethod.fusion.event

data class SoftwareKeyEvent(
    val type: Type,
    override val keyCode: Int?,
    override val char: Char?,
    override val text: String?,
    val modifierState: ModifierState,
): KeyEvent {
    enum class Type {
        PRESS, RELEASE, LONG, REPEAT, MORE_KEY_SELECT
    }
}