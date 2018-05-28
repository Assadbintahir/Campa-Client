package com.codepath.apps.restclienttemplate.utils;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;

public class ConnectionManager {

    static InputStream is = null;
    static JSONObject jObj = null;
    static JSONArray jArr = null;
    static String json = "";
    private static final String TAG = "ConnectionManager";



    public String getTokenFromServer(final String url, final List<NameValuePair> params) throws Exception {

        String authtoken = null;

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // defaultHttpClient
                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(url);
                    httpPost.setEntity(new UrlEncodedFormEntity(params));



                    HttpResponse response = httpClient.execute(httpPost);

                    String responseString = EntityUtils.toString(response.getEntity());
                    if (response.getStatusLine().getStatusCode() != 200) {
                        Log.w("Register", responseString);
                        // try parse the string to a JSON object
                        try {
                            jObj = new JSONObject(responseString);


                        } catch (JSONException e) {
                            throw new Exception("JSON Error getTokenFromServer");
                        }

                        throw new Exception("Error signing-in");
                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        // Making HTTP request


        // return JSON String
        return authtoken;

    }
}
