
package com.example.taylanwhite.meh.data.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Topic__ {

    @SerializedName("commentCount")
    @Expose
    private int commentCount;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("replyCount")
    @Expose
    private int replyCount;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("voteCount")
    @Expose
    private int voteCount;

    /**
     * 
     * @return
     *     The commentCount
     */
    public int getCommentCount() {
        return commentCount;
    }

    /**
     * 
     * @param commentCount
     *     The commentCount
     */
    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * 
     * @return
     *     The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * 
     * @param createdAt
     *     The createdAt
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The replyCount
     */
    public int getReplyCount() {
        return replyCount;
    }

    /**
     * 
     * @param replyCount
     *     The replyCount
     */
    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return
     *     The voteCount
     */
    public int getVoteCount() {
        return voteCount;
    }

    /**
     * 
     * @param voteCount
     *     The voteCount
     */
    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

}
