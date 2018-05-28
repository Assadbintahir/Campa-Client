package com.codepath.apps.restclienttemplate;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class changePassword extends Fragment {
    Button button;
    EditText newPassword;
    EditText confirmNewPassword;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.change_password, container, false);



        button = (Button) view.findViewById(R.id.cp_Submit);
        newPassword = (EditText) view.findViewById(R.id.et_new_pw);
        confirmNewPassword = (EditText) view.findViewById(R.id.et_confirm_new_pw);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newPassword.getText().toString().equals(confirmNewPassword.getText().toString())){
                    Toast.makeText(getActivity().getApplicationContext(), "Password Changed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return  view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Change Password");
    }
}
