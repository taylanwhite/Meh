package com.example.taylanwhite.meh.model

import java.util.ArrayList
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class Item {

    @SerializedName("attributes")
    @Expose
    var attributes: List<Any> = ArrayList()

    @SerializedName("condition")
    @Expose
    var condition: String? = null

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("price")
    @Expose
    var price: Int = 0

    @SerializedName("photo")
    @Expose
    var photo: String? = null

}
