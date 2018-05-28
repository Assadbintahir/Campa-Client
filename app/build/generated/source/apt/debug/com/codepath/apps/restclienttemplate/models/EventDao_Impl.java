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

public class EventDao_Impl implements EventDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfEvent;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfEvent;

  public EventDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEvent = new EntityInsertionAdapter<Event>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `event`(`uid`,`society_id`,`name`,`description`,`type`,`is_public`,`form_type`,`content`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Event value) {
        stmt.bindLong(1, value.getUid());
        if (value.getSociety_id() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getSociety_id());
        }
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDescription());
        }
        if (value.getType() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getType());
        }
        final int _tmp;
        _tmp = value.isIs_public() ? 1 : 0;
        stmt.bindLong(6, _tmp);
        if (value.getForm_type() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getForm_type());
        }
        if (value.getContent() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getContent());
        }
      }
    };
    this.__deletionAdapterOfEvent = new EntityDeletionOrUpdateAdapter<Event>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `event` WHERE `uid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Event value) {
        stmt.bindLong(1, value.getUid());
      }
    };
  }

  @Override
  public void insertAll(Event... events) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfEvent.insert(events);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertOnlySinglePost(Event event) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfEvent.insert(event);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(Event event) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfEvent.handle(event);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Event> getAll() {
    final String _sql = "SELECT * FROM event";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
      final int _cursorIndexOfSocietyId = _cursor.getColumnIndexOrThrow("society_id");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("description");
      final int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
      final int _cursorIndexOfIsPublic = _cursor.getColumnIndexOrThrow("is_public");
      final int _cursorIndexOfFormType = _cursor.getColumnIndexOrThrow("form_type");
      final int _cursorIndexOfContent = _cursor.getColumnIndexOrThrow("content");
      final List<Event> _result = new ArrayList<Event>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Event _item;
        _item = new Event();
        final int _tmpUid;
        _tmpUid = _cursor.getInt(_cursorIndexOfUid);
        _item.setUid(_tmpUid);
        final String _tmpSociety_id;
        _tmpSociety_id = _cursor.getString(_cursorIndexOfSocietyId);
        _item.setSociety_id(_tmpSociety_id);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        _item.setDescription(_tmpDescription);
        final String _tmpType;
        _tmpType = _cursor.getString(_cursorIndexOfType);
        _item.setType(_tmpType);
        final boolean _tmpIs_public;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsPublic);
        _tmpIs_public = _tmp != 0;
        _item.setIs_public(_tmpIs_public);
        final String _tmpForm_type;
        _tmpForm_type = _cursor.getString(_cursorIndexOfFormType);
        _item.setForm_type(_tmpForm_type);
        final String _tmpContent;
        _tmpContent = _cursor.getString(_cursorIndexOfContent);
        _item.setContent(_tmpContent);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
