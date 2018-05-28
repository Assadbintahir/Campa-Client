package com.codepath.apps.restclienttemplate.models;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user where student_id= :student_id")
    List<User> getOne(String student_id);

    @Query("DELETE  FROM user")
    void deleteAll();

    @Insert
    void insertAll(User... users);

    @Insert
    void insertOnlySinglePost (User user);

    @Delete
    void delete(User user);
}
