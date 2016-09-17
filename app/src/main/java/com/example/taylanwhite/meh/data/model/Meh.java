
package com.example.taylanwhite.meh.data.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Meh {

    @SerializedName("deal")
    @Expose
    private Deal deal;
    @SerializedName("poll")
    @Expose
    private Poll poll;
    @SerializedName("video")
    @Expose
    private Video video;

    /**
     * 
     * @return
     *     The deal
     */
    public Deal getDeal() {
        return deal;
    }

    /**
     * 
     * @param deal
     *     The deal
     */
    public void setDeal(Deal deal) {
        this.deal = deal;
    }

    /**
     * 
     * @return
     *     The poll
     */
    public Poll getPoll() {
        return poll;
    }

    /**
     * 
     * @param poll
     *     The poll
     */
    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    /**
     * 
     * @return
     *     The video
     */
    public Video getVideo() {
        return video;
    }

    /**
     * 
     * @param video
     *     The video
     */
    public void setVideo(Video video) {
        this.video = video;
    }

}
