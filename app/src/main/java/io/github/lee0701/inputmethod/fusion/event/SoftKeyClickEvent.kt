package io.github.lee0701.inputmethod.fusion.event

data class SoftKeyClickEvent(
    val type: Type,
    override val keyCode: Int?,
    override val char: Char?,
    override val text: String?,
    override val modifierState: ModifierState,
): SoftKeyEvent() {
    enum class Type {
        NONE, PRESS, RELEASE, LONG, REPEAT, MORE_KEY_SELECT
    }
}