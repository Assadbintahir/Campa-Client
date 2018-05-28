package com.codepath.apps.restclienttemplate.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "userpeople")
public class UserPeople {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int uid;

    @ColumnInfo(name = "people_id")
    private String peopleid;

    @ColumnInfo(name = "first_name")
    private String first_name;

    @ColumnInfo(name = "last_name")
    private String last_name;


    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "designation")
    private String designation;

    @ColumnInfo(name = "gender")
    private String gender;

    @ColumnInfo(name = "department")
    private String department;

    @ColumnInfo(name = "profile_pic")
    private String profile_pic;

    @ColumnInfo(name = "office_hours")
    private String office_hours;

    @ColumnInfo(name = "office_location")
    private String office_location;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "contact")
    private String contact;

    @ColumnInfo(name = "ext")
    private String ext;

    @ColumnInfo(name = "cnic")
    private String cnic;

    @ColumnInfo(name = "university_id")
    private String university_id;

    public String getPeopleid() {
        return peopleid;
    }

    @NonNull
    public int getUid() {
        return uid;
    }

    public void setUid(@NonNull int uid) {
        this.uid = uid;
    }

    public void setPeopleid(String uid) {
        this.peopleid = uid;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getOffice_hours() {
        return office_hours;
    }

    public void setOffice_hours(String office_hours) {
        this.office_hours = office_hours;
    }

    public String getOffice_location() {
        return office_location;
    }

    public void setOffice_location(String office_location) {
        this.office_location = office_location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getUniversity_id() {
        return university_id;
    }

    public void setUniversity_id(String university_id) {
        this.university_id = university_id;
    }
}
