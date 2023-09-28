package ru.yotfr.unisoldevtest.ui.categories.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.yotfr.unisoldevtest.R
import ru.yotfr.model.Category

@Composable
fun ru.yotfr.model.Category.displayName(): String {
    return when(this) {
        ru.yotfr.model.Category.BACKGROUNDS -> stringResource(id = R.string.backgrounds)
        ru.yotfr.model.Category.FASHION -> stringResource(id = R.string.fashion)
        ru.yotfr.model.Category.NATURE -> stringResource(id = R.string.nature)
        ru.yotfr.model.Category.SCIENCE -> stringResource(id = R.string.science)
        ru.yotfr.model.Category.EDUCATION -> stringResource(id = R.string.education)
        ru.yotfr.model.Category.FEELINGS -> stringResource(id = R.string.feelings)
        ru.yotfr.model.Category.HEALTH -> stringResource(id = R.string.health)
        ru.yotfr.model.Category.PEOPLE -> stringResource(id = R.string.people)
        ru.yotfr.model.Category.RELIGION -> stringResource(id = R.string.religion)
        ru.yotfr.model.Category.PLACES -> stringResource(id = R.string.places)
        ru.yotfr.model.Category.ANIMALS -> stringResource(id = R.string.animals)
        ru.yotfr.model.Category.INDUSTRY -> stringResource(id = R.string.industry)
        ru.yotfr.model.Category.COMPUTER -> stringResource(id = R.string.computer)
        ru.yotfr.model.Category.FOOD -> stringResource(id = R.string.food)
        ru.yotfr.model.Category.SPORTS -> stringResource(id = R.string.sports)
        ru.yotfr.model.Category.TRANSPORTATION -> stringResource(id = R.string.transportation)
        ru.yotfr.model.Category.TRAVEL -> stringResource(id = R.string.travel)
        ru.yotfr.model.Category.BUILDINGS -> stringResource(id = R.string.buildings)
        ru.yotfr.model.Category.BUSINESS -> stringResource(id = R.string.business)
        ru.yotfr.model.Category.MUSIC -> stringResource(id = R.string.music)
    }
}