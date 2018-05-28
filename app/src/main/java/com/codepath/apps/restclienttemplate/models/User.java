package com.codepath.apps.restclienttemplate.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "user")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "student_id")
    private String student_id;

    @ColumnInfo(name = "people_id")
    private String people_id;

    @ColumnInfo(name = "univeristy_id")
    private String university_id;

    @ColumnInfo(name = "role")
    private String role;

    @ColumnInfo(name = "is_office_bearer")
    private boolean is_office_bearer;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getPeople_id() {
        return people_id;
    }

    public void setPeople_id(String people_id) {
        this.people_id = people_id;
    }

    public String getUniversity_id() {
        return university_id;
    }

    public void setUniversity_id(String university_id) {
        this.university_id = university_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isIs_office_bearer() {
        return is_office_bearer;
    }

    public void setIs_office_bearer(boolean is_office_bearer) {
        this.is_office_bearer = is_office_bearer;
    }
}
