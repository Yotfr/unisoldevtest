package ru.yotfr.unisoldevtest.domain.model

data class CategoryModel(
    val category: Category,
    val previewUrl: String,
    val wallpapersCount: Int,
    val aspectRatio: Float
)