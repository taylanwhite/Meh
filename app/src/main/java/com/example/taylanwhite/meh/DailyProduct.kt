package com.example.taylanwhite.meh

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by taylanwhite on 9/16/16.
 */

class DailyProduct {

    @SerializedName("title")
    @Expose
    var title: String? = null


    @SerializedName("price")
    @Expose
    var price: String? = null

    @SerializedName("specifications")
    @Expose
    var specs: String? = null

    @SerializedName("photos")
    @Expose
    private val photos: String? = null


}
