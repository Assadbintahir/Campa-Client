package com.codepath.apps.restclienttemplate.Forms;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.MyDatabase;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.models.Event;
import com.codepath.apps.restclienttemplate.models.Post;
import com.codepath.apps.restclienttemplate.models.Society;
import com.codepath.apps.restclienttemplate.models.University;
import com.codepath.apps.restclienttemplate.models.UserPeople;

import java.util.ArrayList;
import java.util.List;

public class CreatePost extends Fragment {
    private EditText society_name;
    private EditText post_detail;
    private EditText created_at;
    private Button submit;



    List <Event> eventData = new ArrayList<Event>();
    List <Post> postData = new ArrayList<Post>();
    List <Society> societyData = new ArrayList<Society>();
    List <UserPeople> userPeopleData = new ArrayList<UserPeople>();

    List <University> universityData= new ArrayList<University>();

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.create_post, container, false);

        society_name = (EditText) view.findViewById(R.id.society_name);
        post_detail = (EditText) view.findViewById(R.id.post_detail);
        created_at = (EditText) view.findViewById(R.id.created_at);
        submit = (Button) view.findViewById(R.id.btn_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getActivity().getApplicationContext();

//                prepareEventData();
//                storeEventToDB();
//                Toast.makeText(context, "Event is poppulated", Toast.LENGTH_SHORT).show();
//
                preparePostData();
                storePostToDB();
//                Toast.makeText(context, "Post is poppulated", Toast.LENGTH_SHORT).show();
//
                prepareSocietyData();
                storeSocietyToDB();
//
//                preparePeopleData();
//                storeUserPeopleToDB();

//                prepareUniversityData();
//                storeUniverityToDB();



                

            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Create Post");
    }




    //Custom methods for data fetching and storing
    private void storeEventToDB(){
        String DATABASE_NAME = "CampA";
        final MyDatabase db;
        db = Room.databaseBuilder(getActivity().getApplicationContext(),
                MyDatabase.class, DATABASE_NAME)
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i<10; i++){
                    db.eventDao().insertOnlySinglePost(eventData.get(i));
                }
            }
        }) .start();
    }

    private void prepareEventData(){
        String societyName = "IBA Sports Society";
        String name = "Sports Olympide 2017";
        String description = "IBA Karachi organizes this premier event each year in which all the universitites participate";
        String type = "Type";
        boolean is_public = true;
        String formType = "1";
        String content = "Video or Image";

        Event event;


        for (int i = 0; i<10; i++){
            event = new Event();
            event.setSociety_id(societyName+" "+ i);
            event.setName(societyName+" "+ i);
            event.setDescription(description+" "+ i);
            event.setType(type+" "+ i);
            event.setIs_public(is_public);
            event.setForm_type(formType+" "+ i);
            event.setContent(content+" "+ i);
            //event.setUid(i);

            eventData.add(event);
        }
    }

    private void storePostToDB(){
        String DATABASE_NAME = "CampA";
        final MyDatabase db;
        db = Room.databaseBuilder(getActivity().getApplicationContext(),
                MyDatabase.class, DATABASE_NAME)
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i<10; i++){
                    db.postDao().insertOnlySinglePost(postData.get(i));
                }
            }
        }) .start();
    }

    private void preparePostData(){
        String societyName = "IBA Sports Society";
        String content = "This post is about how IBA's cricket team defeated SZABIST team";
        String eventID = "event_id";
        Post post;

        for (int i = 0; i<10; i++){
            post = new Post();
            post.setSociety_id(societyName+" "+ i);
            post.setEvent_id(eventID+" "+ i);
            post.setContent(content+" "+ i);
            postData.add(post);
        }
    }

    private void storeSocietyToDB(){
        String DATABASE_NAME = "CampA";
        final MyDatabase db;
        db = Room.databaseBuilder(getActivity().getApplicationContext(),
                MyDatabase.class, DATABASE_NAME)
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i<10; i++){
                    db.societyDao().insertOnlySinglePost(societyData.get(i));
                }

                List<Society> societies  = db.societyDao().getAll();
                Society society = societies.get(5);
            }
        }) .start();
    }

    private void prepareSocietyData(){
        String societyName = "IBA Arts Society";
        String description = "IBA Arts society creates a vibrant culture of arts in IBA";
        String patron_ID = "patron_ID";
        String office_bearer = "office_bearer";
        Society society;

        for (int i = 0; i<10; i++){
            society = new Society();
            society.setName(societyName+" "+ i);
            society.setDescription(description+" "+ i);
            society.setOffice_bearers(office_bearer+" "+ i);
            society.setPatron_id(patron_ID +" "+ i);
            societyData.add(society);
        }
    }

    private void storeUserPeopleToDB(){
        String DATABASE_NAME = "CampA";
        final MyDatabase db;
        db = Room.databaseBuilder(getActivity().getApplicationContext(),
                MyDatabase.class, DATABASE_NAME)
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i<10; i++){
                    db.peopleDao().insertOnlySinglePost(userPeopleData.get(i));
                }

                List<UserPeople> societies  = db.peopleDao().getAll();
                UserPeople society = societies.get(5);
            }
        }) .start();
    }

    private void preparePeopleData(){
        String first_name = "Asad";
        String last_name = "Ullah";
        String username = "a_ullah";
        String designation = "Assistant Professor";
        String gender = "Male";
        String department = "Computer Science";
        String office_hours = "Mon-Wed 3-5pm";
        String office_location = "Aman CED, 3rd Floor, Room # 25";
        String email = "asad@iba.edu.pk";
        String profile_pic = "profile pic address";
        String contact = "0685671597";
        String ext = "1350";
        String cnic = "cnic";
        String university_id = "university_id";
        UserPeople userPeople;

        for (int i = 0; i<10; i++){
            userPeople = new UserPeople();
            userPeople.setFirst_name(first_name+" "+ i);
            userPeople.setLast_name(last_name+" "+ i);
            userPeople.setUsername(username+" "+ i);
            userPeople.setDesignation(designation+" "+ i);
            userPeople.setDepartment(department+" "+ i);
            userPeople.setGender(gender+" "+ i);
            userPeople.setOffice_hours(office_hours+" "+ i);
            userPeople.setOffice_location(office_location+" "+ i);
            userPeople.setEmail(email+" "+ i);
            userPeople.setProfile_pic(profile_pic+" "+ i);
            userPeople.setContact(contact+" "+ i);
            userPeople.setExt(ext+" "+ i);
            userPeople.setCnic(cnic+" "+ i);
            userPeople.setUniversity_id(university_id+" "+ i);

            userPeopleData.add(userPeople);
        }
    }

    private void storeUniverityToDB(){
        String DATABASE_NAME = "CampA";
        final MyDatabase db;
        db = Room.databaseBuilder(getActivity().getApplicationContext(),
                MyDatabase.class, DATABASE_NAME)
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i<10; i++){
                    db.universityDao().insertOnlySinglePost(universityData.get(i));
                }

                List<University> societies  = db.universityDao().getAll();
                University society = societies.get(5);
            }
        }) .start();
    }

    private void prepareUniversityData(){
        String name = "IBA Karachi";
        String address = "University Road, Maskan Chorangi, IBA Karachi";
        String sector = "public sector";
        String logo_url = "logo_url";
        String created_at = "creation time";
        String updated_at = "update_time";
        University society;

        for (int i = 0; i<10; i++){
            society = new University();
            society.setName(name+" "+ i);
            society.setAddress(address+" "+ i);
            society.setSector(sector+" "+ i);
            society.setLogo_url(logo_url +" "+ i);
            society.setCreated_at(created_at +" "+ i);
            society.setUpdated_at(updated_at +" "+ i);

            universityData.add(society);
        }
    }
}
