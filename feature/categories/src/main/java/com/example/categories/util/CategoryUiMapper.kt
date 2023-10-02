package com.example.categories.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.yotfr.resources.R
import ru.yotfr.model.Category

@Composable
fun Category.displayName(): String {
    return when(this) {
        Category.BACKGROUNDS -> stringResource(id = R.string.backgrounds)
        Category.FASHION -> stringResource(id = R.string.fashion)
        Category.NATURE -> stringResource(id = R.string.nature)
        Category.SCIENCE -> stringResource(id = R.string.science)
        Category.EDUCATION -> stringResource(id = R.string.education)
        Category.FEELINGS -> stringResource(id = R.string.feelings)
        Category.HEALTH -> stringResource(id = R.string.health)
        Category.PEOPLE -> stringResource(id = R.string.people)
        Category.RELIGION -> stringResource(id = R.string.religion)
        Category.PLACES -> stringResource(id = R.string.places)
        Category.ANIMALS -> stringResource(id = R.string.animals)
        Category.INDUSTRY -> stringResource(id = R.string.industry)
        Category.COMPUTER -> stringResource(id = R.string.computer)
        Category.FOOD -> stringResource(id = R.string.food)
        Category.SPORTS -> stringResource(id = R.string.sports)
        Category.TRANSPORTATION -> stringResource(id = R.string.transportation)
        Category.TRAVEL -> stringResource(id = R.string.travel)
        Category.BUILDINGS -> stringResource(id = R.string.buildings)
        Category.BUSINESS -> stringResource(id = R.string.business)
        Category.MUSIC -> stringResource(id = R.string.music)
    }
}