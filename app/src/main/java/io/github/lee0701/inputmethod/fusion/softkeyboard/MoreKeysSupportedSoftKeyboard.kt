package io.github.lee0701.inputmethod.fusion.softkeyboard

interface MoreKeysSupportedSoftKeyboard: SoftKeyboard {

    fun showMoreKeysKeyboard(keyCode: Int, moreKeys: List<Int>)
    fun closeMoreKeysKeyboard()

}
