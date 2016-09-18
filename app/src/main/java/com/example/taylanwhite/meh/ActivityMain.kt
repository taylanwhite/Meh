package com.example.taylanwhite.meh

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.GsonBuilder
import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmQuery
import kotlinx.android.synthetic.main.activity_daily_deal.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

import android.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.taylanwhite.meh.databinding.ActivityMainBinding


class ActivityMain : AppCompatActivity() {
    //var BASE_URL = "ZXzByssyPuglD85cLS1WJrwKda4ik9s0"

    //"https://api.meh.com/1/current.json?apikey=ZXzByssyPuglD85cLS1WJrwKda4ik9s0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
                R.layout.activity_main)

        val gson = GsonBuilder().setExclusionStrategies(object : ExclusionStrategy {
            override fun shouldSkipField(f: FieldAttributes): Boolean {
                return f.declaringClass == RealmObject::class.java
            }

            override fun shouldSkipClass(clazz: Class<*>): Boolean {
                return false
            }
        }).create()

        val retrofit: Retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://api.meh.com/1/current.json?apikey=")
                .build()

        val githubService: MehService = retrofit.create(
                MehService::class.java)

        val realm = Realm.getDefaultInstance()

        //get user if it's already saved
        val savedUser: DailyProduct? = RealmQuery.createQuery(realm,
                DailyProduct::class.java).findFirst()
        updateViews(binding, savedUser)

        githubService.getMehData("ZXzByssyPuglD85cLS1WJrwKda4ik9s0")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { user ->
                            realm.beginTransaction()
                            realm.copyToRealmOrUpdate(user)
                            realm.commitTransaction()
                            updateViews(binding, user)
                        },
                        { error ->
                            Log.e("Error", error.message)
                        }
                )


    }

    private fun updateViews(binding: ActivityMainBinding, savedUser: DailyProduct?) {
        Glide.with(this).load(savedUser?.photos).into(binding.imageDeal)
        binding.txtName.text = savedUser?.title
        binding.txtPrice.text = savedUser?.price
        binding.txtDescription.text = savedUser?.specs
                .toString()
    }


}
