package ru.yotfr.database

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import ru.yotfr.database.datastore.UserPreferencesDataStore
import ru.yotfr.database.provider.UserPreferencesDataStoreProvider
import ru.yotfr.database.provider.UserPreferencesDataStoreProviderImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object InternalDataStoreModule {

    private const val USER_PREFERENCES = "USER_PREFERENCES"
    @Singleton
    @Provides
    internal fun providePreferencesDataStore(
        @ApplicationContext appContext: Context
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { appContext.preferencesDataStoreFile(USER_PREFERENCES) }
        )
    }
    @Provides
    @Singleton
    internal fun provideUserPreferenceDataStore(
        dataStore: DataStore<Preferences>
    ): UserPreferencesDataStore {
        return UserPreferencesDataStore(dataStore)
    }

    @Provides
    @Singleton
    fun provideUserPreferencesDataStoreProvider(
        userPreferencesDataStore: UserPreferencesDataStore
    ): UserPreferencesDataStoreProvider {
        return UserPreferencesDataStoreProviderImpl(userPreferencesDataStore)
    }


}