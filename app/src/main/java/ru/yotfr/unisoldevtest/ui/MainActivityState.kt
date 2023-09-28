package ru.yotfr.unisoldevtest.ui

import ru.yotfr.model.ThemeModel

data class MainActivityState(
    val theme: ru.yotfr.model.ThemeModel = ru.yotfr.model.ThemeModel.SYSTEM_DEFAULT
)
