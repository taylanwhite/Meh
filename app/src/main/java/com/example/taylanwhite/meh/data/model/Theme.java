
package com.example.taylanwhite.meh.data.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Theme {

    @SerializedName("accentColor")
    @Expose
    private String accentColor;
    @SerializedName("backgroundColor")
    @Expose
    private String backgroundColor;
    @SerializedName("backgroundImage")
    @Expose
    private String backgroundImage;
    @SerializedName("foreground")
    @Expose
    private String foreground;

    /**
     * 
     * @return
     *     The accentColor
     */
    public String getAccentColor() {
        return accentColor;
    }

    /**
     * 
     * @param accentColor
     *     The accentColor
     */
    public void setAccentColor(String accentColor) {
        this.accentColor = accentColor;
    }

    /**
     * 
     * @return
     *     The backgroundColor
     */
    public String getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * 
     * @param backgroundColor
     *     The backgroundColor
     */
    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * 
     * @return
     *     The backgroundImage
     */
    public String getBackgroundImage() {
        return backgroundImage;
    }

    /**
     * 
     * @param backgroundImage
     *     The backgroundImage
     */
    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    /**
     * 
     * @return
     *     The foreground
     */
    public String getForeground() {
        return foreground;
    }

    /**
     * 
     * @param foreground
     *     The foreground
     */
    public void setForeground(String foreground) {
        this.foreground = foreground;
    }

}
