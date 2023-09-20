package ru.yotfr.unisoldevtest.data.mapper

import ru.yotfr.unisoldevtest.domain.model.Category

fun Category.query(): String {
    return this.name.lowercase()
}