package io.github.lee0701.inputmethod.fusion.softkeyboard.layouts

import io.github.lee0701.inputmethod.fusion.event.KeyCode
import io.github.lee0701.inputmethod.fusion.softkeyboard.Key
import io.github.lee0701.inputmethod.fusion.softkeyboard.Layout
import io.github.lee0701.inputmethod.fusion.softkeyboard.Row

object SoftLayout {

    val LAYOUT_10COLS_MOBILE = Layout(listOf(Row(listOf(
            Key('q'.code, "q"),
            Key('w'.code, "w"),
            Key('e'.code, "e"),
            Key('r'.code, "r"),
            Key('t'.code, "t"),
            Key('y'.code, "y"),
            Key('u'.code, "u"),
            Key('i'.code, "i"),
            Key('o'.code, "o"),
            Key('p'.code, "p")
    ), Row.Type.ODD), Row(listOf(
            Key('a'.code, "a"),
            Key('s'.code, "s"),
            Key('d'.code, "d"),
            Key('f'.code, "f"),
            Key('g'.code, "g"),
            Key('h'.code, "h"),
            Key('j'.code, "j"),
            Key('k'.code, "k"),
            Key('l'.code, "l")
    ), Row.Type.EVEN, marginLeft = 0.05f, marginRight = 0.05f), Row(listOf(
            Key(KeyCode.SHIFT, "SFT", keyWidth = 0.15f),
            Key('z'.code, "z"),
            Key('x'.code, "x"),
            Key('c'.code, "c"),
            Key('v'.code, "v"),
            Key('b'.code, "b"),
            Key('n'.code, "n"),
            Key('m'.code, "m"),
            Key(KeyCode.DEL, "DEL", repeatable = true, keyWidth = 0.15f)
    ), Row.Type.ODD), Row(listOf(
            Key(keyCode = KeyCode.SYM, label = "?12", keyWidth = 0.125f),
            Key(keyCode = ','.code, label = ",", keyWidth = 0.1f),
            Key(keyCode = KeyCode.LANG, label = "ABC", keyWidth = 0.125f),
            Key(keyCode = KeyCode.SPACE, keyWidth = 4/10f),
            Key(keyCode = '.'.code, label = ".", keyWidth = 1/10f),
            Key(keyCode = '\n'.code, label = "RETURN", keyWidth = 0.15f)
    ), Row.Type.BOTTOM)))

    val LAYOUT_10COLS_MOBILE_WITH_NUM = Layout(listOf(Row(listOf(
            Key('1'.code, "1"),
            Key('2'.code, "2"),
            Key('3'.code, "3"),
            Key('4'.code, "4"),
            Key('5'.code, "5"),
            Key('6'.code, "6"),
            Key('7'.code, "7"),
            Key('8'.code, "8"),
            Key('9'.code, "9"),
            Key('0'.code, "0")
    ), Row.Type.NUMBER), Row(listOf(
            Key('q'.code, "q"),
            Key('w'.code, "w"),
            Key('e'.code, "e"),
            Key('r'.code, "r"),
            Key('t'.code, "t"),
            Key('y'.code, "y"),
            Key('u'.code, "u"),
            Key('i'.code, "i"),
            Key('o'.code, "o"),
            Key('p'.code, "p")
    ), Row.Type.ODD), Row(listOf(
            Key('a'.code, "a"),
            Key('s'.code, "s"),
            Key('d'.code, "d"),
            Key('f'.code, "f"),
            Key('g'.code, "g"),
            Key('h'.code, "h"),
            Key('j'.code, "j"),
            Key('k'.code, "k"),
            Key('l'.code, "l")
    ), Row.Type.EVEN, marginLeft = 0.05f, marginRight = 0.05f), Row(listOf(
            Key(KeyCode.SHIFT, "SFT", keyWidth = 0.15f),
            Key('z'.code, "z"),
            Key('x'.code, "x"),
            Key('c'.code, "c"),
            Key('v'.code, "v"),
            Key('b'.code, "b"),
            Key('n'.code, "n"),
            Key('m'.code, "m"),
            Key(KeyCode.DEL, "DEL", repeatable = true, keyWidth = 0.15f)
    ), Row.Type.ODD), Row(listOf(
            Key(keyCode = KeyCode.SYM, label = "?12", keyWidth = 0.125f),
            Key(keyCode = ','.code, label = ",", keyWidth = 0.1f),
            Key(keyCode = KeyCode.LANG, label = "ABC", keyWidth = 0.125f),
            Key(keyCode = KeyCode.SPACE, keyWidth = 4/10f),
            Key(keyCode = '.'.code, label = ".", keyWidth = 1/10f),
            Key(keyCode = '\n'.code, label = "RETURN", keyWidth = 0.15f)
    ), Row.Type.BOTTOM)))

}
