package com.example.taylanwhite.meh.data.model

import java.util.ArrayList
import javax.annotation.Generated
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Generated("org.jsonschema2pojo")
class Deal {

    /**

     * @return
     * *     The features
     */
    /**

     * @param features
     * *     The features
     */
    @SerializedName("features")
    @Expose
    var features: String? = null
    /**

     * @return
     * *     The id
     */
    /**

     * @param id
     * *     The id
     */
    @SerializedName("id")
    @Expose
    var id: String? = null
    /**

     * @return
     * *     The items
     */
    /**

     * @param items
     * *     The items
     */
    @SerializedName("items")
    @Expose
    var items: List<Item> = ArrayList()
    /**

     * @return
     * *     The launches
     */
    /**

     * @param launches
     * *     The launches
     */
    @SerializedName("launches")
    @Expose
    var launches: List<Launch> = ArrayList()
    /**

     * @return
     * *     The photos
     */
    /**

     * @param photos
     * *     The photos
     */
    @SerializedName("photos")
    @Expose
    var photos: List<String> = ArrayList()
    /**

     * @return
     * *     The title
     */
    /**

     * @param title
     * *     The title
     */
    @SerializedName("title")
    @Expose
    var title: String? = null
    /**

     * @return
     * *     The specifications
     */
    /**

     * @param specifications
     * *     The specifications
     */
    @SerializedName("specifications")
    @Expose
    var specifications: String? = null
    /**

     * @return
     * *     The story
     */
    /**

     * @param story
     * *     The story
     */
    @SerializedName("story")
    @Expose
    var story: Story? = null
    /**

     * @return
     * *     The theme
     */
    /**

     * @param theme
     * *     The theme
     */
    @SerializedName("theme")
    @Expose
    var theme: Theme? = null
    /**

     * @return
     * *     The url
     */
    /**

     * @param url
     * *     The url
     */
    @SerializedName("url")
    @Expose
    var url: String? = null
    /**

     * @return
     * *     The topic
     */
    /**

     * @param topic
     * *     The topic
     */
    @SerializedName("topic")
    @Expose
    var topic: Topic? = null

}
