package com.example.taylanwhite.meh.model

import java.util.ArrayList
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class Deal(fastTitle: String?, fastDescription: String?, fastPicture: List<String>) {


    @SerializedName("features")
    @Expose
    var features: String? = null

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("items")
    @Expose
    var items: List<Item> = ArrayList()

    @SerializedName("launches")
    @Expose
    var launches: List<Launch> = ArrayList()

    @SerializedName("photos")
    @Expose
    var photos: List<String> = ArrayList()

    @SerializedName("title")
    @Expose
    open var title: String? = null

    @SerializedName("specifications")
    @Expose
    var specifications: String? = null

    @SerializedName("story")
    @Expose
    var story: Story? = null

    @SerializedName("theme")
    @Expose
    var theme: Theme? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("topic")
    @Expose
    var topic: Topic? = null


    init {
        title = fastTitle
        features = fastDescription
        photos = fastPicture
    }

}
