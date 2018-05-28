package com.codepath.apps.restclienttemplate.models;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class PostDao_Impl implements PostDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfPost;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfPost;

  public PostDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPost = new EntityInsertionAdapter<Post>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `post`(`uid`,`society_id`,`content`,`event_id`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Post value) {
        stmt.bindLong(1, value.getUid());
        if (value.getSociety_id() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getSociety_id());
        }
        if (value.getContent() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getContent());
        }
        if (value.getEvent_id() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getEvent_id());
        }
      }
    };
    this.__deletionAdapterOfPost = new EntityDeletionOrUpdateAdapter<Post>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `post` WHERE `uid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Post value) {
        stmt.bindLong(1, value.getUid());
      }
    };
  }

  @Override
  public void insertAll(Post... posts) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfPost.insert(posts);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertOnlySinglePost(Post post) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfPost.insert(post);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(Post post) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfPost.handle(post);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Post> getAll() {
    final String _sql = "SELECT * FROM post";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
      final int _cursorIndexOfSocietyId = _cursor.getColumnIndexOrThrow("society_id");
      final int _cursorIndexOfContent = _cursor.getColumnIndexOrThrow("content");
      final int _cursorIndexOfEventId = _cursor.getColumnIndexOrThrow("event_id");
      final List<Post> _result = new ArrayList<Post>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Post _item;
        _item = new Post();
        final int _tmpUid;
        _tmpUid = _cursor.getInt(_cursorIndexOfUid);
        _item.setUid(_tmpUid);
        final String _tmpSociety_id;
        _tmpSociety_id = _cursor.getString(_cursorIndexOfSocietyId);
        _item.setSociety_id(_tmpSociety_id);
        final String _tmpContent;
        _tmpContent = _cursor.getString(_cursorIndexOfContent);
        _item.setContent(_tmpContent);
        final String _tmpEvent_id;
        _tmpEvent_id = _cursor.getString(_cursorIndexOfEventId);
        _item.setEvent_id(_tmpEvent_id);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
