package com.example.taylanwhite.meh.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Story {

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("body")
    @Expose
    var body: String? = null

}
