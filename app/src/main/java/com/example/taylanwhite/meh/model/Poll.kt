package com.example.taylanwhite.meh.model

import java.util.ArrayList
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class Poll {


    @SerializedName("answers")
    @Expose
    var answers: List<Answer> = ArrayList()

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("startDate")
    @Expose
    var startDate: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("topic")
    @Expose
    var topic: Topic? = null

}
