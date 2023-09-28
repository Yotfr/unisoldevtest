package ru.yotfr.categories.util

import ru.yotfr.model.Category


internal fun Category.query(): String {
    return this.name.lowercase()
}