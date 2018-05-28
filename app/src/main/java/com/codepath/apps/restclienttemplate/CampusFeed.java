package com.codepath.apps.restclienttemplate;

import android.arch.persistence.room.Room;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.app.Config;
import com.codepath.apps.restclienttemplate.models.Post;
import com.codepath.apps.restclienttemplate.models.UserPeople;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class CampusFeed extends Fragment {

    List <Post> postData = new ArrayList<Post>();
    RecyclerView rv;
    PostAdapter pa;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.campus_feed, container, false);
        View view =  inflater.inflate(R.layout.campus_feed, container, false);

        rv = (RecyclerView) view.findViewById(R.id.rv_news_feed);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(mLayoutManager);

        getPostFromDB();

        if(postData.size()!=0)
        pa = new PostAdapter(postData);

        rv.setAdapter(pa);

        SharedPreferences pref = getActivity().getSharedPreferences(Config.SHARED_PREF, 0);
        SharedPreferences.Editor editor = pref.edit();
        String id = pref.getString("regId", " ");
        System.out.println(id);





        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Campus Feed");

    }


    private void getPostFromDB(){
        String DATABASE_NAME = "CampA";
        final MyDatabase db;
        db = Room.databaseBuilder(getActivity().getApplicationContext(),
                MyDatabase.class, DATABASE_NAME).allowMainThreadQueries()
                .build();


        postData = db.postDao().getAll();

    }





    public class PostAdapter extends
            RecyclerView.Adapter<PostAdapter.MyViewHolder> {

        private List<Post> postList;

        /**
         * View holder class
         * */
        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView societyText;
            public TextView desc;
            public ImageView image;

            public MyViewHolder(View view) {
                super(view);
                societyText = (TextView) view.findViewById(R.id.item_societyname);
                image = (ImageView) view.findViewById(R.id.itemsocietyimage);
            }
        }

        public PostAdapter(List<Post> postList) {
            this.postList = postList;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Post c = postList.get(position);
            holder.societyText.setText(c.getSociety_id());
        }

        @Override
        public int getItemCount() {
            return postList.size();
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_newsfeed,parent, false);
            return new MyViewHolder(v);
        }
    }
}

