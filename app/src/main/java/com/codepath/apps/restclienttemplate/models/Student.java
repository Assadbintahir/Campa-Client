package com.codepath.apps.restclienttemplate.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "student")
public class Student {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "first_name")
    private String first_name;

    @ColumnInfo(name = "last_name")
    private String last_name;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "gender")
    private String gender;

    @ColumnInfo(name = "program")
    private String program;

    @ColumnInfo(name = "admission_semester")
    private String admisson_semester;

    @ColumnInfo(name = "degree")
    private String degree;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "contact")
    private String contact;

    @ColumnInfo(name = "cnic")
    private String cnic;

    @ColumnInfo(name = "profile_pic")
    private String progile_pic;

    @ColumnInfo(name = "university_id")
    private String university_id;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getAdmisson_semester() {
        return admisson_semester;
    }

    public void setAdmisson_semester(String admisson_semester) {
        this.admisson_semester = admisson_semester;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
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

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getProgile_pic() {
        return progile_pic;
    }

    public void setProgile_pic(String progile_pic) {
        this.progile_pic = progile_pic;
    }

    public String getUniversity_id() {
        return university_id;
    }

    public void setUniversity_id(String university_id) {
        this.university_id = university_id;
    }
}
