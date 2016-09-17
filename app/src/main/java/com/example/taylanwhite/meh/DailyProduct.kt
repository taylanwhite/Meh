package com.example.taylanwhite.meh

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by taylanwhite on 9/16/16.
 */
@RealmClass
open class DailyProduct : RealmObject(){

    @PrimaryKey
    @SerializedName("id")
    @Expose
    open var id: Int = 0


    @SerializedName("title")
    @Expose
    open var title: String? = null


    @SerializedName("price")
    @Expose
    open var price: String? = null

    @SerializedName("specifications")
    @Expose
    open var specs: String? = null

    @SerializedName("photos")
    @Expose
    private val photos: String? = null


}
