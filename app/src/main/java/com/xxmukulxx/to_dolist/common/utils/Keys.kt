package com.xxmukulxx.to_dolist.common.utils

import android.content.Context
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore


const val DATA_STORE_NAME = "data_store"

val Context.dataStore by preferencesDataStore(name = DATA_STORE_NAME)
//key name
val THEME_DATA by lazy { intPreferencesKey("THEME_DATA") }