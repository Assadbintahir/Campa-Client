package com.codepath.apps.restclienttemplate;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.models.Post;
import com.codepath.apps.restclienttemplate.models.Society;
import com.codepath.apps.restclienttemplate.models.UserPeople;
import com.codepath.apps.restclienttemplate.utils.PeopleDetail;

import java.util.List;

public class Societies extends Fragment {
    RecyclerView rv ;
    List<Society> societies;
    SocietyAdapter sa;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.societies, container, false);

        rv = view.findViewById(R.id.rv_societies);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        rv.setLayoutManager(llm);

        getPostFromDB();

        if(societies.size()!=0)
            sa = new SocietyAdapter(societies);

        rv.setAdapter(sa);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Societies");
    }

    private void getPostFromDB(){
        String DATABASE_NAME = "CampA";
        final MyDatabase db;
        db = Room.databaseBuilder(getActivity().getApplicationContext(),
                MyDatabase.class, DATABASE_NAME).allowMainThreadQueries()
                .build();


        societies = db.societyDao().getAll();

    }





    public class SocietyAdapter extends
            RecyclerView.Adapter<Societies.SocietyAdapter.MyViewHolder> {

        private List<Society> postList;

        /**
         * View holder class
         * */
        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView societyName;

            public MyViewHolder(View view) {
                super(view);
                societyName = (TextView) view.findViewById(R.id.item_society_name);
            }
        }

        public SocietyAdapter(List<Society> societyList) {
            this.postList = societyList;
        }

        @Override
        public void onBindViewHolder(Societies.SocietyAdapter.MyViewHolder holder, int position) {
            Society c = postList.get(position);
            holder.societyName.setText(c.getName());
        }

        @Override
        public int getItemCount() {
            return postList.size();
        }

        @Override
        public Societies.SocietyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_society,parent, false);
            return new Societies.SocietyAdapter.MyViewHolder(v);
        }
    }
}
