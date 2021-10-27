package io.github.lee0701.inputmethod.fusion.softkeyboard

import android.app.Service
import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.os.Vibrator
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import io.github.lee0701.inputmethod.fusion.R
import io.github.lee0701.inputmethod.fusion.event.*
import io.github.lee0701.inputmethod.fusion.hangul.Hangul
import io.github.lee0701.inputmethod.fusion.softkeyboard.layouts.*
import io.github.lee0701.inputmethod.fusion.softkeyboard.themes.BasicSoftKeyboardTheme

class BasicSoftKeyboard(
    private val config: BasicSoftKeyboardConfig,
    private val layout: Layout,
    private val theme: KeyboardTheme,
    private val onKeyEvent: (KeyEvent) -> Unit,
): MoreKeysSupportedSoftKeyboard, BasicKeyboardView.OnKeyListener {

    var keyboardViewHolder: ViewGroup? = null

    var keyboardView: BasicKeyboardView? = null
    var oneHandedButtonsHolder: View? = null
    var flipButton: ImageButton? = null

    lateinit var leftDrawable: Drawable
    lateinit var rightDrawable: Drawable

    var labels: Map<Int, String> = mapOf()

    private val displayMetrics = DisplayMetrics()
    private val rect = Rect()

    override var shift: Int
        get() = keyboardView?.shift ?: 0
        set(value) {keyboardView?.shift = value}

    override var alt: Int
        get() = keyboardView?.alt ?: 0
        set(value) {keyboardView?.alt = value}

    private var pressTime: Long = 0

    private var vibrator: Vibrator? = null
    private var soundPool: SoundPool? = null
    private var downSound: Int? = null
    private var upSound: Int? = null

    override fun initView(context: Context): View? {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) context.display?.getMetrics(displayMetrics)
        else (context.getSystemService(Service.WINDOW_SERVICE) as WindowManager).defaultDisplay.getMetrics(displayMetrics)

        if(config.vibrate) vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if(config.sound) soundPool = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> SoundPool.Builder().setAudioAttributes(AudioAttributes.Builder().setLegacyStreamType(AudioManager.STREAM_SYSTEM).build()).build()
            else -> SoundPool(1, AudioManager.STREAM_SYSTEM, 0)
        }.apply {
            config.soundType?.down?.let { downSound = load(context, it, 1) }
            config.soundType?.up?.let { upSound = load(context, it, 1) }
        }

        val marginBottom = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, config.marginBottom.toFloat(), context.resources.displayMetrics)
        val marginLeft = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, config.marginLeft.toFloat(), context.resources.displayMetrics)
        val marginRight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, config.marginRight.toFloat(), context.resources.displayMetrics)
        val keyboardHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, config.keyHeight, context.resources.displayMetrics) * layout.rows.size
        val keyboardWidth = displayMetrics.widthPixels - if(config.oneHandedMode != OneHandedMode.OFF) config.oneHandedMargin else 0

        keyboardView = BasicKeyboardView(context, layout, theme, this,
                keyboardWidth, keyboardHeight.toInt(), config.showLabels, config.repeatRate, config.longClickDelay,
                marginLeft.toInt(), marginRight.toInt(), marginBottom.toInt())

        val keyboardViewHolder = LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
        }
        this.keyboardViewHolder = keyboardViewHolder

        leftDrawable = ContextCompat.getDrawable(context, R.drawable.ic_chevron_left_black_24dp)!!
        rightDrawable = ContextCompat.getDrawable(context, R.drawable.ic_chevron_right_black_24dp)!!

        val buttonsHolder = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = ViewGroup.LayoutParams(config.oneHandedMargin, ViewGroup.LayoutParams.MATCH_PARENT)
            background = ContextCompat.getDrawable(context, theme.background)

            addView(ImageButton(context).apply {
                layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT).apply {
                    weight = 1f
                }
                setBackgroundColor(Color.TRANSPARENT)
                setOnClickListener {
                    //TODO: Invert one-handed mode direction
                }
                flipButton = this
            })
            addView(ImageButton(context).apply {
                layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                setBackgroundColor(Color.TRANSPARENT)
                setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_zoom_out_map_black_24dp))
                setOnClickListener {
                    //TODO: Disable one-handed mode
                }
            })
        }
        this.oneHandedButtonsHolder = buttonsHolder


        keyboardView?.let {
            it.keyboardWidth = displayMetrics.widthPixels - if(config.oneHandedMode != OneHandedMode.OFF) config.oneHandedMargin else 0
            it.invalidateAllKeys()
            it.invalidate()
        }

        keyboardViewHolder.let { holder ->
            holder.removeAllViews()
            if(config.oneHandedMode == OneHandedMode.RIGHT) holder.addView(oneHandedButtonsHolder)
            holder.addView(keyboardView)
            if(config.oneHandedMode == OneHandedMode.LEFT) holder.addView(oneHandedButtonsHolder)
        }

        flipButton?.setImageDrawable(
            if(config.oneHandedMode == OneHandedMode.LEFT) rightDrawable else leftDrawable)

        return this.keyboardViewHolder
    }

    override fun getView(): View? {
        return keyboardViewHolder
    }

    override fun reset() {
        keyboardView?.reset()
    }

    override fun updateLabels(labels: Map<Int, String>) {
        this.labels = labels
        layout.rows.forEach { row ->
            row.keys.forEach { key ->
                key.label = labels[key.keyCode] ?: key.label
                if(config.compatibleLabels) key.label = convertToCompatible(key.label)
            }
        }
//        if(keyboardView?.maxLabelLength == 0) keyboardView?.invalidateAllKeys()
        keyboardView?.invalidate()
        keyboardView?.updatePopups()
    }

    private fun convertToCompatible(label: String): String {
        return label.map { c ->
            if(c == ' ') c
            else if(Hangul.isCho(c.code)) Hangul.COMPAT_CHO[Hangul.CONVERT_CHO.indexOf(c)]
            else if(Hangul.isJung(c.code)) Hangul.COMPAT_JUNG[Hangul.STD_JUNG.indexOf(c)]
            else if(Hangul.isJong(c.code)) Hangul.COMPAT_CHO[Hangul.CONVERT_JONG.indexOf(c)]
            else c
        }.joinToString("")
    }

    private fun validatePopupShown(keyCode: Int): Boolean {
        return keyCode in 7 .. 19 || keyCode in 29 .. 56 || keyCode in 68 .. 78
    }

    override fun onKeyDown(keyCode: Int, x: Int, y: Int) {
        val keyboardView = keyboardView
        if(config.showPopups && validatePopupShown(keyCode) && keyboardView != null) {
            keyboardView.getGlobalVisibleRect(rect)
            val key = layout.rows.flatMap { row -> row.keys }.filter { key -> key.keyCode == keyCode }.firstOrNull() ?: return
            val popup = BasicKeyPreviewPopup(
                keyboardView.context,
                // Apply margin if one-handed mode is right
                if(config.oneHandedMode == OneHandedMode.RIGHT) config.oneHandedMargin else 0,
                rect.top, key,
                theme.previewBackground,
                theme.keyTheme[null]?.textColor ?: Color.BLACK
            )
            keyboardView.showPopup(popup)
        }

        vibrator?.vibrate(config.vibrateDuration.toLong())
        val volume = config.soundVolume

        val sound = if(keyCode == KeyCode.SPACE && upSound != null) upSound else downSound
        sound?.let { soundPool?.play(it, volume, volume, 1, 0, 1f) }
        pressTime = System.currentTimeMillis()

        onKeyEvent(SoftwareKeyEvent(
            SoftwareKeyEvent.Type.PRESS,
            keyCode,
            null,
            null,
            ModifierState(shift = shift > 0, alt = alt > 0)
        ))
    }

    override fun onKeyUp(keyCode: Int, x: Int, y: Int) {
        val timeDiff = System.currentTimeMillis() - pressTime - config.longClickDelay/5
        val timeRatio = (timeDiff.toFloat() / config.longClickDelay).let { if(it > 1) 1f else if(it < 0) 0f else it }
        val duration = (timeRatio * config.vibrateDuration).toLong()
        if(duration > 0) vibrator?.vibrate(duration)

        val volume = timeRatio * config.soundVolume
        upSound?.let { soundPool?.play(it, volume, volume, 1, 0, 1f) }

        onKeyEvent(SoftwareKeyEvent(
            SoftwareKeyEvent.Type.RELEASE,
            keyCode,
            null,
            null,
            ModifierState(shift = shift > 0, alt = alt > 0)
        ))
    }

    override fun onKeyLongClick(keyCode: Int) {
        vibrator?.vibrate(config.vibrateDuration.toLong() / 2)
        onKeyEvent(SoftwareKeyEvent(
            SoftwareKeyEvent.Type.LONG,
            keyCode,
            null,
            null,
            ModifierState(shift = shift > 0, alt = alt > 0)
        ))
    }

    override fun onKeyRepeat(keyCode: Int) {
        onKeyEvent(SoftwareKeyEvent(
            SoftwareKeyEvent.Type.REPEAT,
            keyCode,
            null,
            null,
            ModifierState(shift = shift > 0, alt = alt > 0)
        ))
    }

    override fun onMoreKeySelect(originalKeyCode: Int, keyCode: Int) {
        onKeyEvent(SoftwareKeyEvent(
            SoftwareKeyEvent.Type.MORE_KEY_SELECT,
            keyCode,
            null,
            null,
            ModifierState(shift = shift > 0, alt = alt > 0)
        ))
    }

    override fun onKeyFlickLeft(keyCode: Int) {
        onKeyEvent(KeyFlickEvent(
            FlickDirection4.LEFT,
            keyCode,
            null,
            null,
        ))
    }

    override fun onKeyFlickRight(keyCode: Int) {
        onKeyEvent(KeyFlickEvent(
            FlickDirection4.RIGHT,
            keyCode,
            null,
            null,
        ))
    }

    override fun onKeyFlickUp(keyCode: Int) {
        onKeyEvent(KeyFlickEvent(
            FlickDirection4.UP,
            keyCode,
            null,
            null,
        ))
    }

    override fun onKeyFlickDown(keyCode: Int) {
        onKeyEvent(KeyFlickEvent(
            FlickDirection4.DOWN,
            keyCode,
            null,
            null,
        ))
    }

    override fun showMoreKeysKeyboard(keyCode: Int, moreKeys: List<Int>) {
        val key = layout.rows.flatMap { row -> row.keys }.filter { key -> key.keyCode == keyCode }.firstOrNull() ?: return
        val keyboardView = keyboardView ?: return
        keyboardView.getGlobalVisibleRect(rect)
        val list = moreKeys.map { it to (labels[it] ?: it.toString()) }
        if(list.isEmpty()) return
        val popup = BasicMoreKeyPopup(
            keyboardView.context,
            // Apply margin if one-handed mode is right
            if(config.oneHandedMode == OneHandedMode.RIGHT) config.oneHandedMargin else 0,
            rect.top, key, list, theme
        )
        keyboardView.showPopup(popup)
    }

    override fun closeMoreKeysKeyboard() {

    }

    companion object {
        val THEMES = mapOf(
                "white" to BasicSoftKeyboardTheme.WHITE,
                "dark" to BasicSoftKeyboardTheme.DARK,
                "flatwhite" to BasicSoftKeyboardTheme.FLATWHITE,
                "flatdark" to BasicSoftKeyboardTheme.FLATDARK
        )
        val REVERSE_THEMES = THEMES.map { it.value to it.key }.toMap()

        val DARK_THEMES = mapOf(
                BasicSoftKeyboardTheme.WHITE to BasicSoftKeyboardTheme.DARK,
                BasicSoftKeyboardTheme.FLATWHITE to BasicSoftKeyboardTheme.FLATDARK
        )

        val LIGHT_THEMES = DARK_THEMES.map { it.value to it.key }.toMap()

    }

}
