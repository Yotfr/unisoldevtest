package ru.yotfr.unisoldevtest.ui

import ru.yotfr.unisoldevtest.domain.model.ThemeModel

data class MainActivityState(
    val theme: ThemeModel = ThemeModel.SYSTEM_DEFAULT
)
