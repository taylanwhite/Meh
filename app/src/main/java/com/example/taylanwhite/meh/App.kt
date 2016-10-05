package com.example.taylanwhite.meh

import android.app.Application
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
    }
}