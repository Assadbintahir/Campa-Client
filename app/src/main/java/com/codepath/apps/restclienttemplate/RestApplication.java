package com.codepath.apps.restclienttemplate;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

/*
 * This is the Android application itself and is used to configure various settings
 * including the image cache in memory and on disk. This also adds a singleton
 * for accessing the relevant rest client.
 *
 *     RestClient client = RestApplication.getRestClient(Context context);
 *     // use client to send requests to API
 *
 */
public class RestApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
	}

	public static RestClient getRestClient(Context context) {
		Toast.makeText(context, "Inide the Client method", Toast.LENGTH_SHORT);
		return (RestClient) RestClient.getInstance(RestClient.class, context);
	}
}