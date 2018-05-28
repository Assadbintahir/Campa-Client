package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.utils.ApiConnector;
import com.codepath.oauth.OAuthLoginActionBarActivity;
import com.codepath.oauth.OAuthLoginActivity;

public class LoginActivity extends AppCompatActivity {
    private Button loginButton;
    private EditText editTextID;
    private EditText editTextPassword;
	SharedPreferences sp;
	String authtoken;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		loginButton = (Button) findViewById(R.id.LogInButton);
		editTextID = (EditText) findViewById(R.id.etID);
		editTextPassword = (EditText) findViewById(R.id.etPassword);


		sp = getSharedPreferences("login",MODE_PRIVATE);
		authtoken = sp.getString("authtoken", null);

		if(sp.getBoolean("logged",false) && authtoken !=null){
			onLoginSuccess();
		}

		loginButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

				ApiConnector connector = new ApiConnector(getApplication().getApplicationContext());
				String username = editTextID.getText().toString();
				String password = editTextPassword.getText().toString();

				if(!username.equals("") && !password.equals("")){
					try {
						connector.loginUser(username,password);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}

                //onLoginSuccess();
            }
        });
	}


	// Inflate the menu; this adds items to the action bar if it is present.
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	public void setSession(){
		sp.edit().putBoolean("logged",true).apply();
	}

	// OAuth authenticated successfully, launch primary authenticated activity
	// i.e Display application "homepage"

	public void onLoginSuccess() {
		// Intent i = new Intent(this, PhotosActivity.class);
		// startActivity(i);



		//sp.edit().putBoolean("logged",true).apply();
		//sp.edit().putString("authtoken", authtoken);

		Intent i = new Intent(this, MainActivity.class);
		i.putExtra("UserName", sp.getString("username", " "));
		startActivity(i);
	}

	// OAuth authentication flow failed, handle the error
	// i.e Display an error dialog or toast

	// Click handler method for the button used to start OAuth flow
	// Uses the client to initiate OAuth authorization
	// This should be tied to a button used to login
	public void loginToRest(View view) {
		//getClient().connect();
		onLoginSuccess();

	}

}
