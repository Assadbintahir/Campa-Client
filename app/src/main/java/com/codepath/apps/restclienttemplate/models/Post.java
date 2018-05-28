package com.codepath.apps.restclienttemplate.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "post")
public class Post {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "society_id")
    private String society_id;

    @ColumnInfo(name = "content")
    private String content;

    @ColumnInfo(name = "event_id")
    private String event_id;

    public String getSociety_id() {
        return society_id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setSociety_id(String society_id) {
        this.society_id = society_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }
}
