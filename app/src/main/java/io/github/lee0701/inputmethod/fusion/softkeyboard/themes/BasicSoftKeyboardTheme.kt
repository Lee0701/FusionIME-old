package io.github.lee0701.inputmethod.fusion.softkeyboard.themes

import android.graphics.Color
import io.github.lee0701.inputmethod.fusion.R
import io.github.lee0701.inputmethod.fusion.event.KeyCode
import io.github.lee0701.inputmethod.fusion.softkeyboard.KeyTheme
import io.github.lee0701.inputmethod.fusion.softkeyboard.KeyboardTheme
import io.github.lee0701.inputmethod.fusion.softkeyboard.RowTheme

object BasicSoftKeyboardTheme {

    private val WHITE_KEY_CHARACTER = KeyTheme(
            R.drawable.keybg_white,
            R.drawable.keybg_white_p,
            Color.parseColor("#dd000000")
    )

    private val WHITE_KEY_MOD = KeyTheme(
            R.drawable.keybg_white_mod,
            R.drawable.keybg_white_mod_p,
            Color.parseColor("#dd000000")
    )

    private val WHITE_KEY_STICKY = KeyTheme(
            R.drawable.keybg_white_sticky,
            R.drawable.keybg_white_sticky_p,
            Color.parseColor("#dd000000")
    )

    private val WHITE_KEY_STICKY_LOCKED = KeyTheme(
            R.drawable.keybg_white_locked,
            R.drawable.keybg_white_locked_p,
            Color.parseColor("#dd000000")
    )

    private val WHITE_KEY_SPACE = WHITE_KEY_CHARACTER.copy(foreground = R.drawable.keyfg_space_black)
    private val WHITE_KEY_SHIFT = WHITE_KEY_MOD.copy(foreground = R.drawable.keyfg_shift_black)
    private val WHITE_KEY_DEL = WHITE_KEY_MOD.copy(foreground = R.drawable.keyfg_del_black)
    private val WHITE_KEY_LANG = WHITE_KEY_MOD.copy(foreground = R.drawable.keyfg_lang_black)

    val WHITE = KeyboardTheme(
            R.drawable.keybg_white_bg,
            mapOf(
                    null to RowTheme()
            ),
            mapOf(
                    null to WHITE_KEY_CHARACTER,
                    KeyCode.SPACE to WHITE_KEY_SPACE,
                    KeyCode.ENTER to KeyTheme(
                            R.drawable.keybg_white_enter,
                            R.drawable.keybg_white_mod_p,
                            Color.parseColor("#dd000000"),
                            R.drawable.keyfg_enter_white
                    ),
                    KeyCode.SHIFT to WHITE_KEY_SHIFT,
                    KeyCode.DEL to WHITE_KEY_DEL,
                    KeyCode.SYM to WHITE_KEY_MOD,
                    KeyCode.LANG to WHITE_KEY_LANG
            ),
            WHITE_KEY_STICKY,
            WHITE_KEY_STICKY_LOCKED,
            R.drawable.keybg_white_preview
    )

    private val DARK_KEY_CHARACTER = KeyTheme(
            R.drawable.keybg_dark,
            R.drawable.keybg_dark_p,
            Color.WHITE
    )

    private val DARK_KEY_MOD = KeyTheme(
            R.drawable.keybg_dark_mod,
            R.drawable.keybg_dark_p,
            Color.WHITE
    )

    private val DARK_KEY_STICKY = KeyTheme(
            R.drawable.keybg_dark_sticky,
            R.drawable.keybg_dark_sticky_p,
            Color.WHITE
    )

    private val DARK_KEY_STICKY_LOCKED = KeyTheme(
            R.drawable.keybg_dark_locked,
            R.drawable.keybg_dark_locked_p,
            Color.WHITE
    )

    private val DARK_KEY_SPACE = DARK_KEY_CHARACTER.copy(foreground = R.drawable.keyfg_space_white)
    private val DARK_KEY_SHIFT = DARK_KEY_MOD.copy(foreground = R.drawable.keyfg_shift_white)
    private val DARK_KEY_DEL = DARK_KEY_MOD.copy(foreground = R.drawable.keyfg_del_white)
    private val DARK_KEY_LANG = DARK_KEY_MOD.copy(foreground = R.drawable.keyfg_lang_white)

    val DARK = KeyboardTheme(
            R.drawable.keybg_dark_bg,
            mapOf(
                    null to RowTheme()
            ),
            mapOf(
                    null to DARK_KEY_CHARACTER,
                    KeyCode.SPACE to DARK_KEY_SPACE,
                    KeyCode.ENTER to KeyTheme(
                            R.drawable.keybg_dark_enter,
                            R.drawable.keybg_dark_enter_p,
                            Color.WHITE,
                            R.drawable.keyfg_enter_white
                    ),
                KeyCode.SHIFT to DARK_KEY_SHIFT,
                    KeyCode.DEL to DARK_KEY_DEL,
                    KeyCode.SYM to DARK_KEY_MOD,
                    KeyCode.LANG to DARK_KEY_LANG
            ),
            DARK_KEY_STICKY,
            DARK_KEY_STICKY_LOCKED,
            R.drawable.keybg_dark_preview
    )

    private val FLATWHITE_KEY_CHARACTER = KeyTheme(
            R.drawable.keybg_flatwhite,
            R.drawable.keybg_flatwhite_p,
            Color.parseColor("#ff000000")
    )

    private val FLATWHITE_KEY_STICKY = KeyTheme(
            R.drawable.keybg_flatwhite_sticky,
            R.drawable.keybg_flatwhite_sticky_p,
            Color.parseColor("#ff000000")
    )

    private val FLATWHITE_KEY_STICKY_LOCKED = KeyTheme(
            R.drawable.keybg_flatwhite_locked,
            R.drawable.keybg_flatwhite_locked_p,
            Color.parseColor("#ff000000")
    )

    private val FLATWHITE_KEY_SPACE = KeyTheme(
            R.drawable.keybg_flatwhite_space,
            R.drawable.keybg_flatwhite_space_p,
            Color.parseColor("#ff000000")
    )

    private val FLATWHITE_KEY_SHIFT = FLATWHITE_KEY_CHARACTER.copy(foreground = R.drawable.keyfg_shift_black)
    private val FLATWHITE_KEY_DEL = FLATWHITE_KEY_CHARACTER.copy(foreground = R.drawable.keyfg_del_black)
    private val FLATWHITE_KEY_LANG = FLATWHITE_KEY_CHARACTER.copy(foreground = R.drawable.keyfg_lang_black)

    val FLATWHITE = KeyboardTheme(
            R.drawable.keybg_flatwhite_bg,
            mapOf(
                    null to RowTheme()
            ),
            mapOf(
                    null to FLATWHITE_KEY_CHARACTER,
                    KeyCode.SPACE to FLATWHITE_KEY_SPACE,
                    KeyCode.ENTER to KeyTheme(
                            R.drawable.keybg_flatwhite_enter,
                            R.drawable.keybg_flatwhite_enter_p,
                            Color.parseColor("#ff000000"),
                            R.drawable.keyfg_enter_white
                    ),
                    KeyCode.SHIFT to FLATWHITE_KEY_SHIFT,
                    KeyCode.DEL to FLATWHITE_KEY_DEL,
                    KeyCode.LANG to FLATWHITE_KEY_LANG
            ),
            FLATWHITE_KEY_STICKY,
            FLATWHITE_KEY_STICKY_LOCKED,
            R.drawable.keybg_flatwhite_preview
    )

    private val FLATDARK_KEY_CHARACTER = KeyTheme(
            R.drawable.keybg_flatdark,
            R.drawable.keybg_flatdark_p,
            Color.parseColor("#ffffffff")
    )

    private val FLATDARK_KEY_STICKY = KeyTheme(
            R.drawable.keybg_flatdark_sticky,
            R.drawable.keybg_flatdark_sticky_p,
            Color.parseColor("#ffffffff")
    )

    private val FLATDARK_KEY_STICKY_LOCKED = KeyTheme(
            R.drawable.keybg_flatdark_locked,
            R.drawable.keybg_flatdark_locked_p,
            Color.parseColor("#ffffffff")
    )

    private val FLATDARK_KEY_SPACE = KeyTheme(
            R.drawable.keybg_flatdark_space,
            R.drawable.keybg_flatdark_space_p,
            Color.parseColor("#ffffffff")
    )

    private val FLATDARK_KEY_SHIFT = FLATDARK_KEY_CHARACTER.copy(foreground = R.drawable.keyfg_shift_white)
    private val FLATDARK_KEY_DEL = FLATDARK_KEY_CHARACTER.copy(foreground = R.drawable.keyfg_del_white)
    private val FLATDARK_KEY_LANG = FLATDARK_KEY_CHARACTER.copy(foreground = R.drawable.keyfg_lang_white)

    val FLATDARK = KeyboardTheme(
            R.drawable.keybg_flatdark_bg,
            mapOf(
                    null to RowTheme()
            ),
            mapOf(
                    null to FLATDARK_KEY_CHARACTER,
                    KeyCode.SPACE to FLATDARK_KEY_SPACE,
                    KeyCode.ENTER to KeyTheme(
                            R.drawable.keybg_flatdark_enter,
                            R.drawable.keybg_flatdark_enter_p,
                            Color.parseColor("#ffffffff"),
                            R.drawable.keyfg_enter_white
                    ),
                    KeyCode.SHIFT to FLATDARK_KEY_SHIFT,
                    KeyCode.DEL to FLATDARK_KEY_DEL,
                    KeyCode.LANG to FLATDARK_KEY_LANG
            ),
            FLATDARK_KEY_STICKY,
            FLATDARK_KEY_STICKY_LOCKED,
            R.drawable.keybg_flatdark_preview
    )

}
