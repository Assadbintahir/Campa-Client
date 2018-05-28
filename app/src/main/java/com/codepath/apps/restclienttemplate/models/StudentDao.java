package com.codepath.apps.restclienttemplate.models;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface StudentDao {
    @Query("SELECT * FROM student")
    List<Post> getAll();

    @Insert
    void insertAll(Student... students);

    @Insert
    void insertOnlySinglePost (Student student);

    @Delete
    void delete(Student student);
}
