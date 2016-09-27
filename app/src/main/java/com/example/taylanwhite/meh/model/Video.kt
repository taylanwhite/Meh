package com.example.taylanwhite.meh.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Video : RealmObject(){


    @SerializedName("id")
    @Expose
    var id: String? = null


    @SerializedName("startDate")
    @Expose
    var startDate: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

//    @SerializedName("topic")
//    @Expose
//    var topic: Topic? = null

}
