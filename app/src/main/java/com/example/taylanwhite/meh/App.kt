package com.example.taylanwhite.meh

import android.app.AlarmManager
import android.app.Application
import com.dtp.simplemvp.database.DataConnection
import com.example.taylanwhite.meh.presenter.DatabaseHelper
import com.squareup.picasso.Picasso

/**
 * Created by taylanwhite on 10/5/16.
 */
class App : Application() {

    companion object {
        lateinit var instance: Application
        lateinit var picasso: Picasso
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        picasso = Picasso.with(App.instance)

        DataConnection.init(DatabaseHelper(this))
    }
}