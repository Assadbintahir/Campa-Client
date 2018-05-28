package com.codepath.apps.restclienttemplate;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.codepath.apps.restclienttemplate.models.Event;
import com.codepath.apps.restclienttemplate.models.EventDao;
import com.codepath.apps.restclienttemplate.models.UserPeople;
import com.codepath.apps.restclienttemplate.models.UserPeopleDao;
import com.codepath.apps.restclienttemplate.models.Post;
import com.codepath.apps.restclienttemplate.models.PostDao;
import com.codepath.apps.restclienttemplate.models.Society;
import com.codepath.apps.restclienttemplate.models.SocietyDao;
import com.codepath.apps.restclienttemplate.models.Student;
import com.codepath.apps.restclienttemplate.models.StudentDao;
import com.codepath.apps.restclienttemplate.models.University;
import com.codepath.apps.restclienttemplate.models.UniversityDao;
import com.codepath.apps.restclienttemplate.models.User;
import com.codepath.apps.restclienttemplate.models.UserDao;

@Database(entities= {Post.class, Event.class, UserPeople.class, Society.class, Student.class,
                        University.class, User.class},
                        version=2)

public abstract class MyDatabase extends RoomDatabase {
    public abstract PostDao postDao();
    public abstract EventDao eventDao();
    public abstract UserPeopleDao peopleDao();
    public abstract SocietyDao societyDao();
    public abstract StudentDao studentDao();
    public abstract UniversityDao universityDao();
    public abstract UserDao userDao();

}
