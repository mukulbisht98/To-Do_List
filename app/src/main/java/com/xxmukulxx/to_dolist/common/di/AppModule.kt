package com.xxmukulxx.to_dolist.common.di

import com.xxmukulxx.to_dolist.MyApplication
import com.xxmukulxx.to_dolist.common.data.data_store.DataStore
import com.xxmukulxx.to_dolist.common.data.data_store.vm.DataStoreViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideDataStore(): DataStore = DataStore(MyApplication.appContext)

    @Provides
    @Singleton
    fun provideDataStoreViewModel(dataStore: DataStore): DataStoreViewModel {
        return DataStoreViewModel(dataStore)
    }
}