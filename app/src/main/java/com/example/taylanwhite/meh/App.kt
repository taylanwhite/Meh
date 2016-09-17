package com.example.taylanwhite.meh

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by taylanwhite on 9/17/16.
 */


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val realmConfig = RealmConfiguration.Builder(
                this).deleteRealmIfMigrationNeeded().build()
        Realm.setDefaultConfiguration(realmConfig)
    }
}