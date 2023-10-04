package ru.yotfr.shared

import ru.yotfr.shared.model.Category


fun Category.query(): String {
    return this.name.lowercase()
}