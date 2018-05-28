package com.codepath.apps.restclienttemplate.models;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PostDao {
    @Query("SELECT * FROM post")
    List<Post> getAll();

    @Insert
    void insertAll(Post... posts);

    @Insert
    void insertOnlySinglePost (Post post);

    @Delete
    void delete(Post post);
}
