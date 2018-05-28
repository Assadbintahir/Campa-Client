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

public class UserPeopleDao_Impl implements UserPeopleDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfUserPeople;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfUserPeople;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public UserPeopleDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserPeople = new EntityInsertionAdapter<UserPeople>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `userpeople`(`uid`,`people_id`,`first_name`,`last_name`,`username`,`designation`,`gender`,`department`,`profile_pic`,`office_hours`,`office_location`,`email`,`contact`,`ext`,`cnic`,`university_id`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserPeople value) {
        stmt.bindLong(1, value.getUid());
        if (value.getPeopleid() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getPeopleid());
        }
        if (value.getFirst_name() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getFirst_name());
        }
        if (value.getLast_name() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getLast_name());
        }
        if (value.getUsername() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getUsername());
        }
        if (value.getDesignation() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getDesignation());
        }
        if (value.getGender() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getGender());
        }
        if (value.getDepartment() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getDepartment());
        }
        if (value.getProfile_pic() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getProfile_pic());
        }
        if (value.getOffice_hours() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getOffice_hours());
        }
        if (value.getOffice_location() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getOffice_location());
        }
        if (value.getEmail() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getEmail());
        }
        if (value.getContact() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getContact());
        }
        if (value.getExt() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getExt());
        }
        if (value.getCnic() == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.getCnic());
        }
        if (value.getUniversity_id() == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, value.getUniversity_id());
        }
      }
    };
    this.__deletionAdapterOfUserPeople = new EntityDeletionOrUpdateAdapter<UserPeople>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `userpeople` WHERE `uid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserPeople value) {
        stmt.bindLong(1, value.getUid());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE  FROM userpeople";
        return _query;
      }
    };
  }

  @Override
  public void insertAll(ArrayList<UserPeople> people) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfUserPeople.insert(people);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertOnlySinglePost(UserPeople userPeople) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfUserPeople.insert(userPeople);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(UserPeople userPeople) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfUserPeople.handle(userPeople);
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
  public List<UserPeople> getAll() {
    final String _sql = "SELECT * FROM userpeople";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
      final int _cursorIndexOfPeopleid = _cursor.getColumnIndexOrThrow("people_id");
      final int _cursorIndexOfFirstName = _cursor.getColumnIndexOrThrow("first_name");
      final int _cursorIndexOfLastName = _cursor.getColumnIndexOrThrow("last_name");
      final int _cursorIndexOfUsername = _cursor.getColumnIndexOrThrow("username");
      final int _cursorIndexOfDesignation = _cursor.getColumnIndexOrThrow("designation");
      final int _cursorIndexOfGender = _cursor.getColumnIndexOrThrow("gender");
      final int _cursorIndexOfDepartment = _cursor.getColumnIndexOrThrow("department");
      final int _cursorIndexOfProfilePic = _cursor.getColumnIndexOrThrow("profile_pic");
      final int _cursorIndexOfOfficeHours = _cursor.getColumnIndexOrThrow("office_hours");
      final int _cursorIndexOfOfficeLocation = _cursor.getColumnIndexOrThrow("office_location");
      final int _cursorIndexOfEmail = _cursor.getColumnIndexOrThrow("email");
      final int _cursorIndexOfContact = _cursor.getColumnIndexOrThrow("contact");
      final int _cursorIndexOfExt = _cursor.getColumnIndexOrThrow("ext");
      final int _cursorIndexOfCnic = _cursor.getColumnIndexOrThrow("cnic");
      final int _cursorIndexOfUniversityId = _cursor.getColumnIndexOrThrow("university_id");
      final List<UserPeople> _result = new ArrayList<UserPeople>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final UserPeople _item;
        _item = new UserPeople();
        final int _tmpUid;
        _tmpUid = _cursor.getInt(_cursorIndexOfUid);
        _item.setUid(_tmpUid);
        final String _tmpPeopleid;
        _tmpPeopleid = _cursor.getString(_cursorIndexOfPeopleid);
        _item.setPeopleid(_tmpPeopleid);
        final String _tmpFirst_name;
        _tmpFirst_name = _cursor.getString(_cursorIndexOfFirstName);
        _item.setFirst_name(_tmpFirst_name);
        final String _tmpLast_name;
        _tmpLast_name = _cursor.getString(_cursorIndexOfLastName);
        _item.setLast_name(_tmpLast_name);
        final String _tmpUsername;
        _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
        _item.setUsername(_tmpUsername);
        final String _tmpDesignation;
        _tmpDesignation = _cursor.getString(_cursorIndexOfDesignation);
        _item.setDesignation(_tmpDesignation);
        final String _tmpGender;
        _tmpGender = _cursor.getString(_cursorIndexOfGender);
        _item.setGender(_tmpGender);
        final String _tmpDepartment;
        _tmpDepartment = _cursor.getString(_cursorIndexOfDepartment);
        _item.setDepartment(_tmpDepartment);
        final String _tmpProfile_pic;
        _tmpProfile_pic = _cursor.getString(_cursorIndexOfProfilePic);
        _item.setProfile_pic(_tmpProfile_pic);
        final String _tmpOffice_hours;
        _tmpOffice_hours = _cursor.getString(_cursorIndexOfOfficeHours);
        _item.setOffice_hours(_tmpOffice_hours);
        final String _tmpOffice_location;
        _tmpOffice_location = _cursor.getString(_cursorIndexOfOfficeLocation);
        _item.setOffice_location(_tmpOffice_location);
        final String _tmpEmail;
        _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        _item.setEmail(_tmpEmail);
        final String _tmpContact;
        _tmpContact = _cursor.getString(_cursorIndexOfContact);
        _item.setContact(_tmpContact);
        final String _tmpExt;
        _tmpExt = _cursor.getString(_cursorIndexOfExt);
        _item.setExt(_tmpExt);
        final String _tmpCnic;
        _tmpCnic = _cursor.getString(_cursorIndexOfCnic);
        _item.setCnic(_tmpCnic);
        final String _tmpUniversity_id;
        _tmpUniversity_id = _cursor.getString(_cursorIndexOfUniversityId);
        _item.setUniversity_id(_tmpUniversity_id);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<UserPeople> getOne(String people_id) {
    final String _sql = "SELECT * FROM userpeople where people_id= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (people_id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, people_id);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
      final int _cursorIndexOfPeopleid = _cursor.getColumnIndexOrThrow("people_id");
      final int _cursorIndexOfFirstName = _cursor.getColumnIndexOrThrow("first_name");
      final int _cursorIndexOfLastName = _cursor.getColumnIndexOrThrow("last_name");
      final int _cursorIndexOfUsername = _cursor.getColumnIndexOrThrow("username");
      final int _cursorIndexOfDesignation = _cursor.getColumnIndexOrThrow("designation");
      final int _cursorIndexOfGender = _cursor.getColumnIndexOrThrow("gender");
      final int _cursorIndexOfDepartment = _cursor.getColumnIndexOrThrow("department");
      final int _cursorIndexOfProfilePic = _cursor.getColumnIndexOrThrow("profile_pic");
      final int _cursorIndexOfOfficeHours = _cursor.getColumnIndexOrThrow("office_hours");
      final int _cursorIndexOfOfficeLocation = _cursor.getColumnIndexOrThrow("office_location");
      final int _cursorIndexOfEmail = _cursor.getColumnIndexOrThrow("email");
      final int _cursorIndexOfContact = _cursor.getColumnIndexOrThrow("contact");
      final int _cursorIndexOfExt = _cursor.getColumnIndexOrThrow("ext");
      final int _cursorIndexOfCnic = _cursor.getColumnIndexOrThrow("cnic");
      final int _cursorIndexOfUniversityId = _cursor.getColumnIndexOrThrow("university_id");
      final List<UserPeople> _result = new ArrayList<UserPeople>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final UserPeople _item;
        _item = new UserPeople();
        final int _tmpUid;
        _tmpUid = _cursor.getInt(_cursorIndexOfUid);
        _item.setUid(_tmpUid);
        final String _tmpPeopleid;
        _tmpPeopleid = _cursor.getString(_cursorIndexOfPeopleid);
        _item.setPeopleid(_tmpPeopleid);
        final String _tmpFirst_name;
        _tmpFirst_name = _cursor.getString(_cursorIndexOfFirstName);
        _item.setFirst_name(_tmpFirst_name);
        final String _tmpLast_name;
        _tmpLast_name = _cursor.getString(_cursorIndexOfLastName);
        _item.setLast_name(_tmpLast_name);
        final String _tmpUsername;
        _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
        _item.setUsername(_tmpUsername);
        final String _tmpDesignation;
        _tmpDesignation = _cursor.getString(_cursorIndexOfDesignation);
        _item.setDesignation(_tmpDesignation);
        final String _tmpGender;
        _tmpGender = _cursor.getString(_cursorIndexOfGender);
        _item.setGender(_tmpGender);
        final String _tmpDepartment;
        _tmpDepartment = _cursor.getString(_cursorIndexOfDepartment);
        _item.setDepartment(_tmpDepartment);
        final String _tmpProfile_pic;
        _tmpProfile_pic = _cursor.getString(_cursorIndexOfProfilePic);
        _item.setProfile_pic(_tmpProfile_pic);
        final String _tmpOffice_hours;
        _tmpOffice_hours = _cursor.getString(_cursorIndexOfOfficeHours);
        _item.setOffice_hours(_tmpOffice_hours);
        final String _tmpOffice_location;
        _tmpOffice_location = _cursor.getString(_cursorIndexOfOfficeLocation);
        _item.setOffice_location(_tmpOffice_location);
        final String _tmpEmail;
        _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        _item.setEmail(_tmpEmail);
        final String _tmpContact;
        _tmpContact = _cursor.getString(_cursorIndexOfContact);
        _item.setContact(_tmpContact);
        final String _tmpExt;
        _tmpExt = _cursor.getString(_cursorIndexOfExt);
        _item.setExt(_tmpExt);
        final String _tmpCnic;
        _tmpCnic = _cursor.getString(_cursorIndexOfCnic);
        _item.setCnic(_tmpCnic);
        final String _tmpUniversity_id;
        _tmpUniversity_id = _cursor.getString(_cursorIndexOfUniversityId);
        _item.setUniversity_id(_tmpUniversity_id);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
