package com.codepath.apps.restclienttemplate.models;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface SocietyDao {
    @Query("SELECT * FROM society")
    List<Society> getAll();

    @Insert
    void insertAll(Society... societies);

    @Insert
    void insertOnlySinglePost (Society society);

    @Delete
    void delete(Society society);
}
