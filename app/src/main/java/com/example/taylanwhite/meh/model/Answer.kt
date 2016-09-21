package com.example.taylanwhite.meh.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class Answer {


    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("text")
    @Expose
    var text: String? = null

    @SerializedName("voteCount")
    @Expose
    var voteCount: Int = 0

}
