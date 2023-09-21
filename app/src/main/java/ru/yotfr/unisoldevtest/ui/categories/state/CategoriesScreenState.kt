package ru.yotfr.unisoldevtest.ui.categories.state

import ru.yotfr.unisoldevtest.domain.model.CategoryModel

/**
 * @param[trigger] Необходим для вызова рекомпозиции в Compose скрине при изменении
 * элементов листа categories.
 * (Для реализации поэтапной загрузки информации о категории и добавлении этой категории в список)
 */
data class CategoriesScreenState(
    val isLoading: Boolean = false,
    val categories: List<CategoryModel> = emptyList()
)