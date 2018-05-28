package com.codepath.apps.restclienttemplate.utils;


import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.MyDatabase;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.models.User;
import com.codepath.apps.restclienttemplate.models.UserPeople;

import java.util.List;

public class PeopleDetail extends Fragment {
    TextView fullName;
    TextView UserName;
    TextView Department;
    TextView Designation;
    TextView OfficeHours;
    TextView OfficeLocation;
    TextView Email;
    TextView Extension;

    List<UserPeople> people;
    String id ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.people_detail, container, false);

        fullName = (TextView) view.findViewById(R.id.FullName);
        UserName = (TextView) view.findViewById(R.id.UserName);
        Department = (TextView) view.findViewById(R.id.Department);
        Designation = (TextView) view.findViewById(R.id.Designation);
        OfficeHours = (TextView) view.findViewById(R.id.office_housrs);
        OfficeLocation = (TextView) view.findViewById(R.id.OfficeLocation);
        Email = (TextView) view.findViewById(R.id.Email);
        Extension = (TextView) view.findViewById(R.id.Extension);

        id = getArguments().getString("peopleid");


        try {
            getPostFromDB();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    private void getPostFromDB() throws Exception {
        String DATABASE_NAME = "CampA";
        final MyDatabase db;
        db = Room.databaseBuilder(getActivity().getApplicationContext(),
                MyDatabase.class, DATABASE_NAME).allowMainThreadQueries()
                .build();

//        SharedPreferences sp = getActivity().getApplicationContext().getSharedPreferences("login", MODE_PRIVATE);
//        String uid = sp.getString("studentid", " ");
//        String authtoken = sp.getString("authtoken", " ");
//        List<User> people = db.userDao().getOne(uid);
//
//        if(people.size()>0 && !authtoken.equals(" ")){
//            ApiConnector connector = new ApiConnector(getActivity().getApplicationContext());
//            connector.getPeople(people.get(0).getUniversity_id(), authtoken);
//        }


        people = db.peopleDao().getOne(id);

        fullName.setText(people.get(0).getFirst_name()+" "+people.get(0).getLast_name());
        UserName.setText(people.get(0).getUsername());
        Department.setText(people.get(0).getDepartment());
        Designation.setText(people.get(0).getDesignation());
        OfficeHours.setText(people.get(0).getOffice_hours());
        OfficeLocation.setText(people.get(0).getOffice_location());
        Email.setText(people.get(0).getEmail());
        Extension.setText(people.get(0).getExt());

        getActivity().setTitle(people.get(0).getUsername());

    }
}
