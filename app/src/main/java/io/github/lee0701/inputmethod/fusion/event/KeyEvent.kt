package io.github.lee0701.inputmethod.fusion.event

interface KeyEvent: Event {
    val keyCode: Int?
    val char: Char?
    val text: String?
}