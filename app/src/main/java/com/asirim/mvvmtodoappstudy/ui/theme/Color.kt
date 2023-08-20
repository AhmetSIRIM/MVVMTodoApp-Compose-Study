package com.asirim.mvvmtodoappstudy.ui.theme

import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

enum class TodoColorEnum(val value: Color) {
    PASTEL_PINK(Color(0xFFFFD3E0)),
    GOLDEN_YELLOW(Color(0xFFFFD700)),
    LIGHT_CORAL(Color(0xFFFF9AA2)),
    MISTY_ROSE(Color(0xFFFFC0CB)),
    PALE_GREEN(Color(0xFF98FB98)),
    SKY_BLUE(Color(0xFF87CEEB)),
    KHAKI(Color(0xFFF0E68C)),
    LIGHT_PINK(Color(0xFFFFB6C1)),
    LIGHT_BLUE(Color(0xFFADD8E6)),
    LAVENDER(Color(0xFFE6E6FA)),
    HONEYDEW(Color(0xFFF0FFF0))
} // TODO (Ahmet) ---> I will use this with 'TodoColorEnum.values().random()' and assign random color to todos.
