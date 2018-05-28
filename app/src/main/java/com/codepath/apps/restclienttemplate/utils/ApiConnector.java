package com.codepath.apps.restclienttemplate.utils;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;

import com.codepath.apps.restclienttemplate.LoginActivity;
import com.codepath.apps.restclienttemplate.MainActivity;
import com.codepath.apps.restclienttemplate.MyDatabase;
import com.codepath.apps.restclienttemplate.models.User;
import com.codepath.apps.restclienttemplate.models.UserPeople;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import cz.msebera.android.httpclient.util.EntityUtils;

import static android.content.Context.MODE_PRIVATE;

public class ApiConnector {
    ConnectionManager connection;
    static InputStream is = null;
    static JSONObject jObj = null;
    static JSONArray jArr = null;
    static String json = "";
    Context ctx;
    private String baseURL = "http://www.ourcampa.com";
    private String loginURL;
    private String userDetail;
    private String getPeople;

    public ApiConnector(Context ctx){
        this.ctx = ctx;
        connection = new ConnectionManager();
        loginURL = baseURL + "/auth/local";
        userDetail = baseURL + "/api/users";
        getPeople = baseURL + "/api/people";

    }


    //Get Auth Token from Server
    public void loginUser(final String username, String password) throws Exception{
        // Building Parameters
        final List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(loginURL);
                    httpPost.setEntity(new UrlEncodedFormEntity(params));
                    HttpResponse response = httpClient.execute(httpPost);
                    String responseString = EntityUtils.toString(response.getEntity());

                    try {
                         jObj = new JSONObject(responseString);
                         String authtoken = jObj.getString("token");

                         if(authtoken!=null) {
                             SharedPreferences sp = ctx.getSharedPreferences("login", MODE_PRIVATE);
                             sp.edit().putString("authtoken", authtoken).apply();
                             sp.edit().putBoolean("logged", true).apply();
                             sp.edit().putString("username", username).apply();

                             getUserDetail(authtoken);



                         }

                    } catch (JSONException e) {
                        throw new Exception("JSON Error getTokenFromServer");
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }



    //Get User detail from server
    public void getUserDetail(final String authtoken) throws Exception{

        // Building Parameters
        final List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("authtoken", authtoken));

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpPost = new HttpGet(userDetail);

                    //httpPost.setHeader(new BasicHeader("Content-Type","application/json"));
                    httpPost.setHeader(new BasicHeader("Authorization","Bearer "+ authtoken));
                    HttpResponse response = httpClient.execute(httpPost);
                    String responseString = EntityUtils.toString(response.getEntity());

                    try {
                        jObj = new JSONObject(responseString);


                        String DATABASE_NAME = "CampA";
                        final MyDatabase db;
                        db = Room.databaseBuilder(ctx,
                                MyDatabase.class, DATABASE_NAME)
                                .build();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                User user = new User();
                                UserPeople people = new UserPeople();
                                try {
                                    user.setStudent_id(jObj.getJSONObject("payload").getString("_id"));
                                    user.setPeople_id(jObj.getJSONObject("payload").getJSONObject("people_id").getString("_id"));
                                    user.setUniversity_id(jObj.getJSONObject("payload").getJSONObject("university_id").getString("_id"));
                                    user.setRole(jObj.getJSONObject("payload").getString("role"));
                                    user.setIs_office_bearer(jObj.getJSONObject("payload").getBoolean("is_office_bearer"));


                                    people.setPeopleid(jObj.getJSONObject("payload").getJSONObject("people_id").getString("_id"));
                                    people.setFirst_name(jObj.getJSONObject("payload").getJSONObject("people_id").getString("first_name"));
                                    people.setLast_name(jObj.getJSONObject("payload").getJSONObject("people_id").getString("last_name"));
                                    people.setUsername(jObj.getJSONObject("payload").getJSONObject("people_id").getString("username"));
                                    people.setDesignation(jObj.getJSONObject("payload").getJSONObject("people_id").getString("designation"));
                                    people.setGender(jObj.getJSONObject("payload").getJSONObject("people_id").getString("gender"));
                                    people.setDepartment(jObj.getJSONObject("payload").getJSONObject("people_id").getString("department"));
                                    people.setEmail(jObj.getJSONObject("payload").getJSONObject("people_id").getString("email"));
                                    people.setContact(jObj.getJSONObject("payload").getJSONObject("people_id").getString("contact"));
                                    people.setExt(jObj.getJSONObject("payload").getJSONObject("people_id").getString("ext"));
                                    people.setCnic(jObj.getJSONObject("payload").getJSONObject("people_id").getString("CNIC"));
                                    people.setProfile_pic(jObj.getJSONObject("payload").getJSONObject("people_id").getString("profile_pic"));
                                    people.setUniversity_id(jObj.getJSONObject("payload").getJSONObject("people_id").getString("university_id"));

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                db.userDao().insertOnlySinglePost(user);
                                db.peopleDao().insertOnlySinglePost(people);

                                SharedPreferences sp = ctx.getSharedPreferences("login", MODE_PRIVATE);
                                sp.edit().putString("peopleid", people.getPeopleid()).apply();
                                sp.edit().putString("studentid", user.getStudent_id()).apply();

                                try {
                                    getPeople(people.getUniversity_id(), authtoken);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                Intent i = new Intent(ctx, MainActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                                ctx.startActivity(i);
                            }
                        }) .start();

                    } catch (JSONException e) {
                        throw new Exception("JSON Error getTokenFromServer");
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });


    }




    //Get People from server
    public void getPeople(final String univeristyid, final String authtoken) throws Exception{

        // Building Parameters
        final List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("authtoken", authtoken));

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpPost = new HttpGet(getPeople+"/"+univeristyid);

                    //httpPost.setHeader(new BasicHeader("Content-Type","application/json"));
                    httpPost.setHeader(new BasicHeader("Authorization","Bearer "+ authtoken));
                    HttpResponse response = httpClient.execute(httpPost);
                    String responseString = EntityUtils.toString(response.getEntity());

                    try {
                        jObj = new JSONObject(responseString);


                        String DATABASE_NAME = "CampA";
                        final MyDatabase db;
                        db = Room.databaseBuilder(ctx,
                                MyDatabase.class, DATABASE_NAME)
                                .build();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                JSONArray listPeople = jObj.getJSONArray("payload");
                                UserPeople people;

                                    List<UserPeople> pd = db.peopleDao().getAll();
                                    UserPeople d = pd.get(0);
                                    db.peopleDao().deleteAll();
                                    db.peopleDao().insertOnlySinglePost(d);

                                for (int i =0; i<listPeople.length(); i++) {

                                    JSONObject object = (JSONObject) listPeople.get(i);



                                        people = new UserPeople();
                                        people.setPeopleid(object.getString("_id"));
                                        people.setFirst_name(object.getString("first_name"));
                                        people.setLast_name(object.getString("last_name"));
                                        people.setUsername(object.getString("username"));
                                        people.setDesignation(object.getString("designation"));
                                        people.setGender(object.getString("gender"));
                                        people.setDepartment(object.getString("department"));
                                        people.setEmail(object.getString("email"));
                                        people.setContact(object.getString("contact"));
                                        people.setExt(object.getString("ext"));
                                        people.setCnic(object.getString("CNIC"));
                                        people.setProfile_pic(object.getString("profile_pic"));
                                        people.setUniversity_id(object.getJSONObject("university_id").getString("_id"));
//

                                        db.peopleDao().insertOnlySinglePost(people);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }) .start();

                    } catch (JSONException e) {
                        throw new Exception("JSON Error getTokenFromServer");
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });


    }



}
