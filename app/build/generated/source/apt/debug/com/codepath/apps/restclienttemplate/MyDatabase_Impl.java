package com.codepath.apps.restclienttemplate;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import com.codepath.apps.restclienttemplate.models.EventDao;
import com.codepath.apps.restclienttemplate.models.EventDao_Impl;
import com.codepath.apps.restclienttemplate.models.PostDao;
import com.codepath.apps.restclienttemplate.models.PostDao_Impl;
import com.codepath.apps.restclienttemplate.models.SocietyDao;
import com.codepath.apps.restclienttemplate.models.SocietyDao_Impl;
import com.codepath.apps.restclienttemplate.models.StudentDao;
import com.codepath.apps.restclienttemplate.models.StudentDao_Impl;
import com.codepath.apps.restclienttemplate.models.UniversityDao;
import com.codepath.apps.restclienttemplate.models.UniversityDao_Impl;
import com.codepath.apps.restclienttemplate.models.UserDao;
import com.codepath.apps.restclienttemplate.models.UserDao_Impl;
import com.codepath.apps.restclienttemplate.models.UserPeopleDao;
import com.codepath.apps.restclienttemplate.models.UserPeopleDao_Impl;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.util.HashMap;
import java.util.HashSet;

public class MyDatabase_Impl extends MyDatabase {
  private volatile PostDao _postDao;

  private volatile EventDao _eventDao;

  private volatile UserPeopleDao _userPeopleDao;

  private volatile SocietyDao _societyDao;

  private volatile StudentDao _studentDao;

  private volatile UniversityDao _universityDao;

  private volatile UserDao _userDao;

  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(2) {
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `post` (`uid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `society_id` TEXT, `content` TEXT, `event_id` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `event` (`uid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `society_id` TEXT, `name` TEXT, `description` TEXT, `type` TEXT, `is_public` INTEGER NOT NULL, `form_type` TEXT, `content` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `userpeople` (`uid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `people_id` TEXT, `first_name` TEXT, `last_name` TEXT, `username` TEXT, `designation` TEXT, `gender` TEXT, `department` TEXT, `profile_pic` TEXT, `office_hours` TEXT, `office_location` TEXT, `email` TEXT, `contact` TEXT, `ext` TEXT, `cnic` TEXT, `university_id` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `society` (`uid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `description` TEXT, `office_bearers` TEXT, `patron_id` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `student` (`uid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `first_name` TEXT, `last_name` TEXT, `username` TEXT, `gender` TEXT, `program` TEXT, `admission_semester` TEXT, `degree` TEXT, `email` TEXT, `contact` TEXT, `cnic` TEXT, `profile_pic` TEXT, `university_id` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `university` (`uid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `address` TEXT, `sector` TEXT, `logo_url` TEXT, `created_at` TEXT, `updated_at` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `user` (`uid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `student_id` TEXT, `people_id` TEXT, `univeristy_id` TEXT, `role` TEXT, `is_office_bearer` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"f80daaf8aabf599d52d1ed550d3501f4\")");
      }

      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `post`");
        _db.execSQL("DROP TABLE IF EXISTS `event`");
        _db.execSQL("DROP TABLE IF EXISTS `userpeople`");
        _db.execSQL("DROP TABLE IF EXISTS `society`");
        _db.execSQL("DROP TABLE IF EXISTS `student`");
        _db.execSQL("DROP TABLE IF EXISTS `university`");
        _db.execSQL("DROP TABLE IF EXISTS `user`");
      }

      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsPost = new HashMap<String, TableInfo.Column>(4);
        _columnsPost.put("uid", new TableInfo.Column("uid", "INTEGER", true, 1));
        _columnsPost.put("society_id", new TableInfo.Column("society_id", "TEXT", false, 0));
        _columnsPost.put("content", new TableInfo.Column("content", "TEXT", false, 0));
        _columnsPost.put("event_id", new TableInfo.Column("event_id", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPost = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPost = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPost = new TableInfo("post", _columnsPost, _foreignKeysPost, _indicesPost);
        final TableInfo _existingPost = TableInfo.read(_db, "post");
        if (! _infoPost.equals(_existingPost)) {
          throw new IllegalStateException("Migration didn't properly handle post(com.codepath.apps.restclienttemplate.models.Post).\n"
                  + " Expected:\n" + _infoPost + "\n"
                  + " Found:\n" + _existingPost);
        }
        final HashMap<String, TableInfo.Column> _columnsEvent = new HashMap<String, TableInfo.Column>(8);
        _columnsEvent.put("uid", new TableInfo.Column("uid", "INTEGER", true, 1));
        _columnsEvent.put("society_id", new TableInfo.Column("society_id", "TEXT", false, 0));
        _columnsEvent.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        _columnsEvent.put("description", new TableInfo.Column("description", "TEXT", false, 0));
        _columnsEvent.put("type", new TableInfo.Column("type", "TEXT", false, 0));
        _columnsEvent.put("is_public", new TableInfo.Column("is_public", "INTEGER", true, 0));
        _columnsEvent.put("form_type", new TableInfo.Column("form_type", "TEXT", false, 0));
        _columnsEvent.put("content", new TableInfo.Column("content", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEvent = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesEvent = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoEvent = new TableInfo("event", _columnsEvent, _foreignKeysEvent, _indicesEvent);
        final TableInfo _existingEvent = TableInfo.read(_db, "event");
        if (! _infoEvent.equals(_existingEvent)) {
          throw new IllegalStateException("Migration didn't properly handle event(com.codepath.apps.restclienttemplate.models.Event).\n"
                  + " Expected:\n" + _infoEvent + "\n"
                  + " Found:\n" + _existingEvent);
        }
        final HashMap<String, TableInfo.Column> _columnsUserpeople = new HashMap<String, TableInfo.Column>(16);
        _columnsUserpeople.put("uid", new TableInfo.Column("uid", "INTEGER", true, 1));
        _columnsUserpeople.put("people_id", new TableInfo.Column("people_id", "TEXT", false, 0));
        _columnsUserpeople.put("first_name", new TableInfo.Column("first_name", "TEXT", false, 0));
        _columnsUserpeople.put("last_name", new TableInfo.Column("last_name", "TEXT", false, 0));
        _columnsUserpeople.put("username", new TableInfo.Column("username", "TEXT", false, 0));
        _columnsUserpeople.put("designation", new TableInfo.Column("designation", "TEXT", false, 0));
        _columnsUserpeople.put("gender", new TableInfo.Column("gender", "TEXT", false, 0));
        _columnsUserpeople.put("department", new TableInfo.Column("department", "TEXT", false, 0));
        _columnsUserpeople.put("profile_pic", new TableInfo.Column("profile_pic", "TEXT", false, 0));
        _columnsUserpeople.put("office_hours", new TableInfo.Column("office_hours", "TEXT", false, 0));
        _columnsUserpeople.put("office_location", new TableInfo.Column("office_location", "TEXT", false, 0));
        _columnsUserpeople.put("email", new TableInfo.Column("email", "TEXT", false, 0));
        _columnsUserpeople.put("contact", new TableInfo.Column("contact", "TEXT", false, 0));
        _columnsUserpeople.put("ext", new TableInfo.Column("ext", "TEXT", false, 0));
        _columnsUserpeople.put("cnic", new TableInfo.Column("cnic", "TEXT", false, 0));
        _columnsUserpeople.put("university_id", new TableInfo.Column("university_id", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserpeople = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserpeople = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserpeople = new TableInfo("userpeople", _columnsUserpeople, _foreignKeysUserpeople, _indicesUserpeople);
        final TableInfo _existingUserpeople = TableInfo.read(_db, "userpeople");
        if (! _infoUserpeople.equals(_existingUserpeople)) {
          throw new IllegalStateException("Migration didn't properly handle userpeople(com.codepath.apps.restclienttemplate.models.UserPeople).\n"
                  + " Expected:\n" + _infoUserpeople + "\n"
                  + " Found:\n" + _existingUserpeople);
        }
        final HashMap<String, TableInfo.Column> _columnsSociety = new HashMap<String, TableInfo.Column>(5);
        _columnsSociety.put("uid", new TableInfo.Column("uid", "INTEGER", true, 1));
        _columnsSociety.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        _columnsSociety.put("description", new TableInfo.Column("description", "TEXT", false, 0));
        _columnsSociety.put("office_bearers", new TableInfo.Column("office_bearers", "TEXT", false, 0));
        _columnsSociety.put("patron_id", new TableInfo.Column("patron_id", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSociety = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSociety = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSociety = new TableInfo("society", _columnsSociety, _foreignKeysSociety, _indicesSociety);
        final TableInfo _existingSociety = TableInfo.read(_db, "society");
        if (! _infoSociety.equals(_existingSociety)) {
          throw new IllegalStateException("Migration didn't properly handle society(com.codepath.apps.restclienttemplate.models.Society).\n"
                  + " Expected:\n" + _infoSociety + "\n"
                  + " Found:\n" + _existingSociety);
        }
        final HashMap<String, TableInfo.Column> _columnsStudent = new HashMap<String, TableInfo.Column>(13);
        _columnsStudent.put("uid", new TableInfo.Column("uid", "INTEGER", true, 1));
        _columnsStudent.put("first_name", new TableInfo.Column("first_name", "TEXT", false, 0));
        _columnsStudent.put("last_name", new TableInfo.Column("last_name", "TEXT", false, 0));
        _columnsStudent.put("username", new TableInfo.Column("username", "TEXT", false, 0));
        _columnsStudent.put("gender", new TableInfo.Column("gender", "TEXT", false, 0));
        _columnsStudent.put("program", new TableInfo.Column("program", "TEXT", false, 0));
        _columnsStudent.put("admission_semester", new TableInfo.Column("admission_semester", "TEXT", false, 0));
        _columnsStudent.put("degree", new TableInfo.Column("degree", "TEXT", false, 0));
        _columnsStudent.put("email", new TableInfo.Column("email", "TEXT", false, 0));
        _columnsStudent.put("contact", new TableInfo.Column("contact", "TEXT", false, 0));
        _columnsStudent.put("cnic", new TableInfo.Column("cnic", "TEXT", false, 0));
        _columnsStudent.put("profile_pic", new TableInfo.Column("profile_pic", "TEXT", false, 0));
        _columnsStudent.put("university_id", new TableInfo.Column("university_id", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysStudent = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesStudent = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoStudent = new TableInfo("student", _columnsStudent, _foreignKeysStudent, _indicesStudent);
        final TableInfo _existingStudent = TableInfo.read(_db, "student");
        if (! _infoStudent.equals(_existingStudent)) {
          throw new IllegalStateException("Migration didn't properly handle student(com.codepath.apps.restclienttemplate.models.Student).\n"
                  + " Expected:\n" + _infoStudent + "\n"
                  + " Found:\n" + _existingStudent);
        }
        final HashMap<String, TableInfo.Column> _columnsUniversity = new HashMap<String, TableInfo.Column>(7);
        _columnsUniversity.put("uid", new TableInfo.Column("uid", "INTEGER", true, 1));
        _columnsUniversity.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        _columnsUniversity.put("address", new TableInfo.Column("address", "TEXT", false, 0));
        _columnsUniversity.put("sector", new TableInfo.Column("sector", "TEXT", false, 0));
        _columnsUniversity.put("logo_url", new TableInfo.Column("logo_url", "TEXT", false, 0));
        _columnsUniversity.put("created_at", new TableInfo.Column("created_at", "TEXT", false, 0));
        _columnsUniversity.put("updated_at", new TableInfo.Column("updated_at", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUniversity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUniversity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUniversity = new TableInfo("university", _columnsUniversity, _foreignKeysUniversity, _indicesUniversity);
        final TableInfo _existingUniversity = TableInfo.read(_db, "university");
        if (! _infoUniversity.equals(_existingUniversity)) {
          throw new IllegalStateException("Migration didn't properly handle university(com.codepath.apps.restclienttemplate.models.University).\n"
                  + " Expected:\n" + _infoUniversity + "\n"
                  + " Found:\n" + _existingUniversity);
        }
        final HashMap<String, TableInfo.Column> _columnsUser = new HashMap<String, TableInfo.Column>(6);
        _columnsUser.put("uid", new TableInfo.Column("uid", "INTEGER", true, 1));
        _columnsUser.put("student_id", new TableInfo.Column("student_id", "TEXT", false, 0));
        _columnsUser.put("people_id", new TableInfo.Column("people_id", "TEXT", false, 0));
        _columnsUser.put("univeristy_id", new TableInfo.Column("univeristy_id", "TEXT", false, 0));
        _columnsUser.put("role", new TableInfo.Column("role", "TEXT", false, 0));
        _columnsUser.put("is_office_bearer", new TableInfo.Column("is_office_bearer", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUser = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUser = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUser = new TableInfo("user", _columnsUser, _foreignKeysUser, _indicesUser);
        final TableInfo _existingUser = TableInfo.read(_db, "user");
        if (! _infoUser.equals(_existingUser)) {
          throw new IllegalStateException("Migration didn't properly handle user(com.codepath.apps.restclienttemplate.models.User).\n"
                  + " Expected:\n" + _infoUser + "\n"
                  + " Found:\n" + _existingUser);
        }
      }
    }, "f80daaf8aabf599d52d1ed550d3501f4");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "post","event","userpeople","society","student","university","user");
  }

  @Override
  public PostDao postDao() {
    if (_postDao != null) {
      return _postDao;
    } else {
      synchronized(this) {
        if(_postDao == null) {
          _postDao = new PostDao_Impl(this);
        }
        return _postDao;
      }
    }
  }

  @Override
  public EventDao eventDao() {
    if (_eventDao != null) {
      return _eventDao;
    } else {
      synchronized(this) {
        if(_eventDao == null) {
          _eventDao = new EventDao_Impl(this);
        }
        return _eventDao;
      }
    }
  }

  @Override
  public UserPeopleDao peopleDao() {
    if (_userPeopleDao != null) {
      return _userPeopleDao;
    } else {
      synchronized(this) {
        if(_userPeopleDao == null) {
          _userPeopleDao = new UserPeopleDao_Impl(this);
        }
        return _userPeopleDao;
      }
    }
  }

  @Override
  public SocietyDao societyDao() {
    if (_societyDao != null) {
      return _societyDao;
    } else {
      synchronized(this) {
        if(_societyDao == null) {
          _societyDao = new SocietyDao_Impl(this);
        }
        return _societyDao;
      }
    }
  }

  @Override
  public StudentDao studentDao() {
    if (_studentDao != null) {
      return _studentDao;
    } else {
      synchronized(this) {
        if(_studentDao == null) {
          _studentDao = new StudentDao_Impl(this);
        }
        return _studentDao;
      }
    }
  }

  @Override
  public UniversityDao universityDao() {
    if (_universityDao != null) {
      return _universityDao;
    } else {
      synchronized(this) {
        if(_universityDao == null) {
          _universityDao = new UniversityDao_Impl(this);
        }
        return _universityDao;
      }
    }
  }

  @Override
  public UserDao userDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }
}
