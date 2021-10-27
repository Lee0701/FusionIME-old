package io.github.lee0701.inputmethod.fusion.event

sealed interface FlickDirection

enum class FlickDirection4: FlickDirection {
    LEFT,
    RIGHT,
    UP,
    DOWN,
}
enum class FlickDirection8: FlickDirection {
    LEFT,
    RIGHT,
    UP,
    DOWN,
    UP_LEFT,
    UP_RIGHT,
    DOWN_LEFT,
    DOWN_RIGHT,
}