package com.example.taylanwhite.meh.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class Theme {

    @SerializedName("accentColor")
    @Expose
    var accentColor: String? = null

    @SerializedName("backgroundColor")
    @Expose
    var backgroundColor: String? = null

    @SerializedName("backgroundImage")
    @Expose
    var backgroundImage: String? = null

    @SerializedName("foreground")
    @Expose
    var foreground: String? = null

}
