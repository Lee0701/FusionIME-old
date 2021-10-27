package io.github.lee0701.inputmethod.fusion.softkeyboard

/**
 * Config variables to create BasicSoftKeyboard
 */
data class BasicSoftKeyboardConfig(
    val keyHeight: Float = 0f,
    val showLabels: Boolean = true,
    val compatibleLabels: Boolean = true,
    val showPopups: Boolean = false,

    val marginLeft: Int = 0,
    val marginRight: Int = marginLeft,
    val marginBottom: Int = 0,

    val oneHandedMode: OneHandedMode = OneHandedMode.OFF,
    val oneHandedMargin: Int = 0,

    val repeatRate: Int = 50,
    val longClickDelay: Int = 1000,

    val vibrate: Boolean = false,
    val vibrateDuration: Int = 0,

    val sound: Boolean = false,
    val soundType: BasicKeyboardSound? = null,
    val soundVolume: Float = 0f,
)