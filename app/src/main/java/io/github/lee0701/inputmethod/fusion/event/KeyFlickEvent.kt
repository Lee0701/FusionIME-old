package io.github.lee0701.inputmethod.fusion.event

data class KeyFlickEvent(
    val direction: FlickDirection,
    override val keyCode: Int?,
    override val char: Char?,
    override val text: String?,
): KeyEvent