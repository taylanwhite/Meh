package com.example.taylanwhite.meh

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.util.ArrayList

/**
 * Created by taylanwhite on 9/16/16.
 */

class ProductList {

    /**
     * @return The contacts
     */
    /**
     * @param contacts The contacts
     */
    @SerializedName("contacts")
    @Expose
    var contacts = ArrayList<DailyProduct>()
}
