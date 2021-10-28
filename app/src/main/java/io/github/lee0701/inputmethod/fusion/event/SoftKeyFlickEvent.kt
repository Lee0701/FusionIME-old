package io.github.lee0701.inputmethod.fusion.event

data class SoftKeyFlickEvent(
    val direction: FlickDirection,
    override val keyCode: Int?,
    override val char: Char?,
    override val text: String?,
    override val modifierState: ModifierState,
): SoftKeyEvent()