package io.github.lee0701.inputmethod.fusion.hangul

object Hangul {
    fun isCho(char: Int) = (char and 0xffffff).let { it in 0x1100 .. 0x115f || it in 0xa960 .. 0xa97c }
    fun isJung(char: Int) = (char and 0xffffff).let { it in 0x1160 .. 0x11a7 || it in 0xd7b0 .. 0xd7c6 }
    fun isJong(char: Int) = (char and 0xffffff).let { it in 0x11a8 .. 0x11ff || it in 0xd7cb .. 0xd7fb }

    const val COMPAT_CHO = "ㄱㄲㄳㄴㄵㄶㄷㄸㄹㄺㄻㄼㄽㄾㄿㅀㅁㅂㅃㅄㅅㅆㅇㅈㅉㅊㅋㅌㅍㅎ"
    const val COMPAT_JUNG = "ㅏㅐㅑㅒㅓㅔㅕㅖㅗㅘㅙㅚㅛㅜㅝㅞㅟㅠㅡㅢㅣㆇㆈㆉㆊㆋㆌㆍㆎ"

    const val CONVERT_CHO = "ᄀᄁ ᄂ  ᄃᄄᄅ       ᄆᄇᄈ ᄉᄊᄋᄌᄍᄎᄏᄐᄑᄒ"
    const val CONVERT_JONG = "ᆨᆩᆪᆫᆬᆭᆮ ᆯᆰᆱᆲᆳᆴᆵᆶᆷᆸ ᆹᆺᆻᆼᆽ ᆾᆿᇀᇁᇂ"
    const val STD_JUNG = "ᅡᅢᅣᅤᅥᅦᅧᅨᅩᅪᅫᅬᅭᅮᅯᅰᅱᅲᅳᅴᅵᆄᆅᆈᆑᆒᆔᆞᆡ"

}