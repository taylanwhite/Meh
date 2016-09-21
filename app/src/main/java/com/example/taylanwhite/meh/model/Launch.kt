package com.example.taylanwhite.meh.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class Launch {

    @SerializedName("soldOutAt")
    @Expose
    var soldOutAt: Any? = null

}
