package com.codepath.apps.restclienttemplate;

import android.arch.persistence.room.Room;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.models.Post;
import com.codepath.apps.restclienttemplate.models.UserPeople;
import com.codepath.apps.restclienttemplate.utils.ApiConnector;
import com.google.firebase.messaging.FirebaseMessaging;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.app.Config;
import com.codepath.apps.restclienttemplate.utils.NotificationUtils;

import com.codepath.apps.restclienttemplate.Forms.CreatePost;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView UserName;
    private TextView UserEmail;

    private static final String TAG = MainActivity.class.getSimpleName();
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView =  navigationView.getHeaderView(0);
        UserName = (TextView) hView.findViewById(R.id.UserName);
        UserEmail = (TextView) hView.findViewById(R.id.UserID);

        setUserIdentityOnBar();

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_CAMPA);

                    displayFirebaseRegId();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");

                    Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();
                }
            }
        };
        //fetchUserDetails();
        displayFirebaseRegId();
        drawer.openDrawer(GravityCompat.START);

        //displaySelectedScreen(R.id.nav_campus_feed);
    }


    //fetches user detail after login
    private void fetchUserDetails(){
        ApiConnector connector = new ApiConnector(getApplication().getApplicationContext());
        SharedPreferences sp = getSharedPreferences("login",MODE_PRIVATE);
        try {
            connector.getUserDetail(sp.getString("authtoken", null));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //display Firebase Reg ID
    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);


        if (!TextUtils.isEmpty(regId)) {
            Toast.makeText(getApplicationContext(), "Push notification: " + regId, Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Firebase Reg Id is not received yet!" , Toast.LENGTH_LONG).show();
        }
    }

    //Sets the user name, image and ERP on sidebar.
    private void setUserIdentityOnBar(){
        String DATABASE_NAME = "CampA";
        final MyDatabase db;
        db = Room.databaseBuilder(getApplicationContext(),
                MyDatabase.class, DATABASE_NAME).allowMainThreadQueries()
                .build();

        SharedPreferences sp = getSharedPreferences("login", MODE_PRIVATE);
        String uid = sp.getString("peopleid", " ");
        List<UserPeople> people = db.peopleDao().getOne(uid);

        if(people.size() !=0) {
            String userName = people.get(0).getFirst_name() + people.get(0).getLast_name();
            String email = people.get(0).getEmail();
            Toast.makeText(getApplicationContext(), userName, Toast.LENGTH_SHORT);
            UserName.setText(userName);
            UserEmail.setText(email);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


    private void displaySelectedScreen(int id){
        android.support.v4.app.Fragment fragment = null;

        switch (id){
            case R.id.nav_campus_feed:
                fragment = new CampusFeed();
                break;
            case R.id.nav_my_events:
                fragment = new MyEvents();
                break;
            case R.id.nav_people:
                fragment = new People();
                break;
            case R.id.nav_settings:
                fragment = new Settings();
                break;
            case R.id.nav_logout:
                SharedPreferences sp;
                sp = getSharedPreferences("login",MODE_PRIVATE);
                sp.edit().putBoolean("logged",false).apply();
                sp.edit().putString("authtoken", null).apply();
                sp.edit().putString("peopleid", null).apply();
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(i);
                break;
            case R.id.nav_create_data:
                fragment = new CreatePost();
                break;
            case R.id.nav_societies:
                fragment = new Societies();

        }

        if(fragment != null){
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.id_content_main, fragment);
            ft.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        displaySelectedScreen(id);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
