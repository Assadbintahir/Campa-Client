package com.codepath.apps.restclienttemplate;

import android.arch.persistence.room.Room;
import android.content.ClipData;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.models.Post;
import com.codepath.apps.restclienttemplate.models.User;
import com.codepath.apps.restclienttemplate.models.UserPeople;
import com.codepath.apps.restclienttemplate.utils.ApiConnector;
import com.codepath.apps.restclienttemplate.utils.PeopleDetail;

import org.w3c.dom.Text;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class People extends Fragment {

    RecyclerView rv ;
    List<UserPeople> peopleData;
    PeopleAdapter pa;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.people, container, false);

        rv = view.findViewById(R.id.rv_people);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        rv.setLayoutManager(llm);

        try {
            getPostFromDB();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(peopleData.size()>0)
            pa = new PeopleAdapter(peopleData);
        rv.setAdapter(pa);



        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("UserPeople");
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


        peopleData = db.peopleDao().getAll();
    }



    public class PeopleAdapter extends
            RecyclerView.Adapter<People.PeopleAdapter.MyViewHolder> {

        private List<UserPeople> userPeople;

        /**
         * View holder class
         * */
        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView UserName;
            public TextView email;
            public ImageView image;

            public MyViewHolder(final View view) {
                super(view);
                UserName = (TextView) view.findViewById(R.id.item_people_name);
                email = (TextView) view.findViewById(R.id.item_people_email);
            }
        }

        public PeopleAdapter(List<UserPeople> userPeople) {
            this.userPeople = userPeople;
        }

        @Override
        public void onBindViewHolder(People.PeopleAdapter.MyViewHolder holder, final int position) {
            UserPeople c = userPeople.get(position);
            holder.UserName.setText(c.getFirst_name() + c.getLast_name());
            holder.email.setText(c.getEmail());

            holder.UserName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = peopleData.get(position).getPeopleid();

                    Fragment frag = new PeopleDetail();
                    android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    Bundle args = new Bundle();
                    args.putString("peopleid", id);
                    frag.setArguments(args);
                    ft.replace(R.id.id_content_main, frag);
                    ft.addToBackStack("People");
                    ft.commit();

                }
            });
        }

        @Override
        public int getItemCount() {
            return peopleData.size();
        }

        @Override
        public People.PeopleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_people,parent, false);
            return new People.PeopleAdapter.MyViewHolder(v);
        }
    }
}
