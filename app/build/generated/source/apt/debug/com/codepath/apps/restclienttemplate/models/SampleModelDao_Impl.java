package com.codepath.apps.restclienttemplate.models;

import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class SampleModelDao_Impl implements SampleModelDao {
  private final RoomDatabase __db;

  public SampleModelDao_Impl(RoomDatabase __db) {
    this.__db = __db;
  }

  @Override
  public SampleModel byId(long id) {
    final String _sql = "SELECT * FROM SampleModel WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final SampleModel _result;
      if(_cursor.moveToFirst()) {
        _result = new SampleModel();
        if (_cursor.isNull(_cursorIndexOfId)) {
          _result.id = null;
        } else {
          _result.id = _cursor.getLong(_cursorIndexOfId);
        }
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _result.setName(_tmpName);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<SampleModel> recentItems() {
    final String _sql = "SELECT * FROM SampleModel ORDER BY ID DESC LIMIT 300";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final List<SampleModel> _result = new ArrayList<SampleModel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final SampleModel _item;
        _item = new SampleModel();
        if (_cursor.isNull(_cursorIndexOfId)) {
          _item.id = null;
        } else {
          _item.id = _cursor.getLong(_cursorIndexOfId);
        }
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
