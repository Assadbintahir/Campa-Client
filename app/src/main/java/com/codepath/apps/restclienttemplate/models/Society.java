package com.codepath.apps.restclienttemplate.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "society")
public class Society {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "office_bearers")
    private String office_bearers;

    @ColumnInfo(name = "patron_id")
    private String patron_id;

    public String getPatron_id() {
        return patron_id;
    }

    public void setPatron_id(String patron_id) {
        this.patron_id = patron_id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOffice_bearers() {
        return office_bearers;
    }

    public void setOffice_bearers(String office_bearers) {
        this.office_bearers = office_bearers;
    }
}
