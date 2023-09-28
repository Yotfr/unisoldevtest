package ru.yotfr.shared

import ru.yotfr.model.Category


fun Category.query(): String {
    return this.name.lowercase()
}