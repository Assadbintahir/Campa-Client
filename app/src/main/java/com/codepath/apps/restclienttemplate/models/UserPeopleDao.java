package com.codepath.apps.restclienttemplate.models;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface UserPeopleDao {
    @Query("SELECT * FROM userpeople")
    List<UserPeople> getAll();

    @Query("SELECT * FROM userpeople where people_id= :people_id")
    List<UserPeople> getOne(String people_id);

    @Query("DELETE  FROM userpeople")
    void deleteAll();

    @Insert
    void insertAll(ArrayList<UserPeople> people);

    @Insert
    void insertOnlySinglePost (UserPeople userPeople);

    @Delete
    void delete(UserPeople userPeople);

}
