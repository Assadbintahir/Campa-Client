package com.codepath.apps.restclienttemplate.models;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class UserDao_Impl implements UserDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfUser;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfUser;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public UserDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUser = new EntityInsertionAdapter<User>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `user`(`uid`,`student_id`,`people_id`,`univeristy_id`,`role`,`is_office_bearer`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, User value) {
        stmt.bindLong(1, value.getUid());
        if (value.getStudent_id() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getStudent_id());
        }
        if (value.getPeople_id() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPeople_id());
        }
        if (value.getUniversity_id() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getUniversity_id());
        }
        if (value.getRole() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getRole());
        }
        final int _tmp;
        _tmp = value.isIs_office_bearer() ? 1 : 0;
        stmt.bindLong(6, _tmp);
      }
    };
    this.__deletionAdapterOfUser = new EntityDeletionOrUpdateAdapter<User>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `user` WHERE `uid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, User value) {
        stmt.bindLong(1, value.getUid());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE  FROM user";
        return _query;
      }
    };
  }

  @Override
  public void insertAll(User... users) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfUser.insert(users);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertOnlySinglePost(User user) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfUser.insert(user);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(User user) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfUser.handle(user);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public List<User> getAll() {
    final String _sql = "SELECT * FROM user";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
      final int _cursorIndexOfStudentId = _cursor.getColumnIndexOrThrow("student_id");
      final int _cursorIndexOfPeopleId = _cursor.getColumnIndexOrThrow("people_id");
      final int _cursorIndexOfUniversityId = _cursor.getColumnIndexOrThrow("univeristy_id");
      final int _cursorIndexOfRole = _cursor.getColumnIndexOrThrow("role");
      final int _cursorIndexOfIsOfficeBearer = _cursor.getColumnIndexOrThrow("is_office_bearer");
      final List<User> _result = new ArrayList<User>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final User _item;
        _item = new User();
        final int _tmpUid;
        _tmpUid = _cursor.getInt(_cursorIndexOfUid);
        _item.setUid(_tmpUid);
        final String _tmpStudent_id;
        _tmpStudent_id = _cursor.getString(_cursorIndexOfStudentId);
        _item.setStudent_id(_tmpStudent_id);
        final String _tmpPeople_id;
        _tmpPeople_id = _cursor.getString(_cursorIndexOfPeopleId);
        _item.setPeople_id(_tmpPeople_id);
        final String _tmpUniversity_id;
        _tmpUniversity_id = _cursor.getString(_cursorIndexOfUniversityId);
        _item.setUniversity_id(_tmpUniversity_id);
        final String _tmpRole;
        _tmpRole = _cursor.getString(_cursorIndexOfRole);
        _item.setRole(_tmpRole);
        final boolean _tmpIs_office_bearer;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsOfficeBearer);
        _tmpIs_office_bearer = _tmp != 0;
        _item.setIs_office_bearer(_tmpIs_office_bearer);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<User> getOne(String student_id) {
    final String _sql = "SELECT * FROM user where student_id= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (student_id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, student_id);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
      final int _cursorIndexOfStudentId = _cursor.getColumnIndexOrThrow("student_id");
      final int _cursorIndexOfPeopleId = _cursor.getColumnIndexOrThrow("people_id");
      final int _cursorIndexOfUniversityId = _cursor.getColumnIndexOrThrow("univeristy_id");
      final int _cursorIndexOfRole = _cursor.getColumnIndexOrThrow("role");
      final int _cursorIndexOfIsOfficeBearer = _cursor.getColumnIndexOrThrow("is_office_bearer");
      final List<User> _result = new ArrayList<User>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final User _item;
        _item = new User();
        final int _tmpUid;
        _tmpUid = _cursor.getInt(_cursorIndexOfUid);
        _item.setUid(_tmpUid);
        final String _tmpStudent_id;
        _tmpStudent_id = _cursor.getString(_cursorIndexOfStudentId);
        _item.setStudent_id(_tmpStudent_id);
        final String _tmpPeople_id;
        _tmpPeople_id = _cursor.getString(_cursorIndexOfPeopleId);
        _item.setPeople_id(_tmpPeople_id);
        final String _tmpUniversity_id;
        _tmpUniversity_id = _cursor.getString(_cursorIndexOfUniversityId);
        _item.setUniversity_id(_tmpUniversity_id);
        final String _tmpRole;
        _tmpRole = _cursor.getString(_cursorIndexOfRole);
        _item.setRole(_tmpRole);
        final boolean _tmpIs_office_bearer;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsOfficeBearer);
        _tmpIs_office_bearer = _tmp != 0;
        _item.setIs_office_bearer(_tmpIs_office_bearer);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
