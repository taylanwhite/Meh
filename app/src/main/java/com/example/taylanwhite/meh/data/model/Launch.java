
package com.example.taylanwhite.meh.data.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Launch {

    @SerializedName("soldOutAt")
    @Expose
    private Object soldOutAt;

    /**
     * 
     * @return
     *     The soldOutAt
     */
    public Object getSoldOutAt() {
        return soldOutAt;
    }

    /**
     * 
     * @param soldOutAt
     *     The soldOutAt
     */
    public void setSoldOutAt(Object soldOutAt) {
        this.soldOutAt = soldOutAt;
    }

}
