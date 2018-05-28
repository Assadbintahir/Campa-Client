package com.codepath.apps.restclienttemplate.models;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UniversityDao {

    @Query("SELECT * FROM university")
    List<University> getAll();

    @Insert
    void insertAll(University... universities);

    @Insert
    void insertOnlySinglePost (University university);

    @Delete
    void delete(University university);

}
