package com.example.cattask.View;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cattask.Model.User;
import com.example.cattask.R;
import com.example.cattask.WebService.Webservice;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

import static com.example.cattask.View.MainActivity.userdata;

public class UserDetailsFragment extends Fragment {

    TextView id , fullname , username , email , mobile , gender , creationDate ;
    Button updateImage , allusers;
    String userid;
    public UserDetailsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_user_details, container, false);

        email = (TextView) view.findViewById(R.id.email);
        id = (TextView) view.findViewById(R.id.id);
        fullname = (TextView) view.findViewById(R.id.fullname);
        mobile = (TextView) view.findViewById(R.id.phone);
        username = (TextView) view.findViewById(R.id.username);
        gender = (TextView) view.findViewById(R.id.gender);
        creationDate = (TextView) view.findViewById(R.id.creationDate);
        updateImage= (Button) view.findViewById(R.id.updateImage);
        allusers= (Button) view.findViewById(R.id.allusers);


        userid = userdata.getId();

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Response<List<User>> response = Webservice.getInstance().getApi().getUserDetails( userid ).execute();

            User user = response.body().get(0);
            id.setText(userid);
            email.setText(user.getEmail());
            fullname.setText(user.getFullname());
            mobile.setText(user.getMobile_number());
            username.setText(user.getUsername());
            creationDate.setText(user.getCreation_date());

            if (user.getFkgenderid()==1)
                gender.setText("Male");
            else
                gender.setText("Female");

        } catch (IOException e) {
            e.printStackTrace();
        }

        updateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUpdateProfile();
            }
        });

        allusers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAllUsers();
            }
        });


        return view;
    }

    private void goToAllUsers() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new AllUsersFragment())
                .commit();
    }

    private void goToUpdateProfile() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new UpdatePictureFragment())
                .commit();
    }

}
