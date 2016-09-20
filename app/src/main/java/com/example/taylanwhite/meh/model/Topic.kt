package com.example.taylanwhite.meh.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Topic {


    @SerializedName("commentCount")
    @Expose
    var commentCount: Int = 0

    @SerializedName("createdAt")
    @Expose
    var createdAt: String? = null

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("replyCount")
    @Expose
    var replyCount: Int = 0

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("voteCount")
    @Expose
    var voteCount: Int = 0

}
