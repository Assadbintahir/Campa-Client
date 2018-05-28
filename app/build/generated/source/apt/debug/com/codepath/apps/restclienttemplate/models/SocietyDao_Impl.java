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

public class SocietyDao_Impl implements SocietyDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfSociety;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfSociety;

  public SocietyDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSociety = new EntityInsertionAdapter<Society>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `society`(`uid`,`name`,`description`,`office_bearers`,`patron_id`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Society value) {
        stmt.bindLong(1, value.getUid());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        if (value.getOffice_bearers() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getOffice_bearers());
        }
        if (value.getPatron_id() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPatron_id());
        }
      }
    };
    this.__deletionAdapterOfSociety = new EntityDeletionOrUpdateAdapter<Society>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `society` WHERE `uid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Society value) {
        stmt.bindLong(1, value.getUid());
      }
    };
  }

  @Override
  public void insertAll(Society... societies) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfSociety.insert(societies);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertOnlySinglePost(Society society) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfSociety.insert(society);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(Society society) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfSociety.handle(society);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Society> getAll() {
    final String _sql = "SELECT * FROM society";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("description");
      final int _cursorIndexOfOfficeBearers = _cursor.getColumnIndexOrThrow("office_bearers");
      final int _cursorIndexOfPatronId = _cursor.getColumnIndexOrThrow("patron_id");
      final List<Society> _result = new ArrayList<Society>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Society _item;
        _item = new Society();
        final int _tmpUid;
        _tmpUid = _cursor.getInt(_cursorIndexOfUid);
        _item.setUid(_tmpUid);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        _item.setDescription(_tmpDescription);
        final String _tmpOffice_bearers;
        _tmpOffice_bearers = _cursor.getString(_cursorIndexOfOfficeBearers);
        _item.setOffice_bearers(_tmpOffice_bearers);
        final String _tmpPatron_id;
        _tmpPatron_id = _cursor.getString(_cursorIndexOfPatronId);
        _item.setPatron_id(_tmpPatron_id);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
