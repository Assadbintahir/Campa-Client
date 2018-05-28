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

public class UniversityDao_Impl implements UniversityDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfUniversity;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfUniversity;

  public UniversityDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUniversity = new EntityInsertionAdapter<University>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `university`(`uid`,`name`,`address`,`sector`,`logo_url`,`created_at`,`updated_at`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, University value) {
        stmt.bindLong(1, value.getUid());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getAddress() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAddress());
        }
        if (value.getSector() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getSector());
        }
        if (value.getLogo_url() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getLogo_url());
        }
        if (value.getCreated_at() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getCreated_at());
        }
        if (value.getUpdated_at() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getUpdated_at());
        }
      }
    };
    this.__deletionAdapterOfUniversity = new EntityDeletionOrUpdateAdapter<University>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `university` WHERE `uid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, University value) {
        stmt.bindLong(1, value.getUid());
      }
    };
  }

  @Override
  public void insertAll(University... universities) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfUniversity.insert(universities);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertOnlySinglePost(University university) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfUniversity.insert(university);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(University university) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfUniversity.handle(university);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<University> getAll() {
    final String _sql = "SELECT * FROM university";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfAddress = _cursor.getColumnIndexOrThrow("address");
      final int _cursorIndexOfSector = _cursor.getColumnIndexOrThrow("sector");
      final int _cursorIndexOfLogoUrl = _cursor.getColumnIndexOrThrow("logo_url");
      final int _cursorIndexOfCreatedAt = _cursor.getColumnIndexOrThrow("created_at");
      final int _cursorIndexOfUpdatedAt = _cursor.getColumnIndexOrThrow("updated_at");
      final List<University> _result = new ArrayList<University>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final University _item;
        _item = new University();
        final int _tmpUid;
        _tmpUid = _cursor.getInt(_cursorIndexOfUid);
        _item.setUid(_tmpUid);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        final String _tmpAddress;
        _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
        _item.setAddress(_tmpAddress);
        final String _tmpSector;
        _tmpSector = _cursor.getString(_cursorIndexOfSector);
        _item.setSector(_tmpSector);
        final String _tmpLogo_url;
        _tmpLogo_url = _cursor.getString(_cursorIndexOfLogoUrl);
        _item.setLogo_url(_tmpLogo_url);
        final String _tmpCreated_at;
        _tmpCreated_at = _cursor.getString(_cursorIndexOfCreatedAt);
        _item.setCreated_at(_tmpCreated_at);
        final String _tmpUpdated_at;
        _tmpUpdated_at = _cursor.getString(_cursorIndexOfUpdatedAt);
        _item.setUpdated_at(_tmpUpdated_at);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
