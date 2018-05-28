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

public class StudentDao_Impl implements StudentDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfStudent;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfStudent;

  public StudentDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfStudent = new EntityInsertionAdapter<Student>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `student`(`uid`,`first_name`,`last_name`,`username`,`gender`,`program`,`admission_semester`,`degree`,`email`,`contact`,`cnic`,`profile_pic`,`university_id`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Student value) {
        stmt.bindLong(1, value.getUid());
        if (value.getFirst_name() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getFirst_name());
        }
        if (value.getLast_name() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getLast_name());
        }
        if (value.getUsername() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getUsername());
        }
        if (value.getGender() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getGender());
        }
        if (value.getProgram() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getProgram());
        }
        if (value.getAdmisson_semester() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getAdmisson_semester());
        }
        if (value.getDegree() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getDegree());
        }
        if (value.getEmail() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getEmail());
        }
        if (value.getContact() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getContact());
        }
        if (value.getCnic() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getCnic());
        }
        if (value.getProgile_pic() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getProgile_pic());
        }
        if (value.getUniversity_id() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getUniversity_id());
        }
      }
    };
    this.__deletionAdapterOfStudent = new EntityDeletionOrUpdateAdapter<Student>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `student` WHERE `uid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Student value) {
        stmt.bindLong(1, value.getUid());
      }
    };
  }

  @Override
  public void insertAll(Student... students) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfStudent.insert(students);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertOnlySinglePost(Student student) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfStudent.insert(student);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(Student student) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfStudent.handle(student);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Post> getAll() {
    final String _sql = "SELECT * FROM student";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
      final List<Post> _result = new ArrayList<Post>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Post _item;
        _item = new Post();
        final int _tmpUid;
        _tmpUid = _cursor.getInt(_cursorIndexOfUid);
        _item.setUid(_tmpUid);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
