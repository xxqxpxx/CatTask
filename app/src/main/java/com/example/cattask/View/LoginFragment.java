package com.example.cattask.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cattask.Model.LoginResponse;
import com.example.cattask.R;
import com.example.cattask.WebService.Webservice;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.cattask.View.MainActivity.userdata;


public class LoginFragment extends Fragment {

    EditText email, password;
    Button button;
    TextView registration;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        email = (EditText) view.findViewById(R.id.input_email);
        password = (EditText) view.findViewById(R.id.input_password);
        button = (Button) view.findViewById(R.id.btn_login);
        registration = (TextView) view.findViewById(R.id.link_signup);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login(email.getText().toString().trim() , password.getText().toString().trim() );
            }
        });


        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Signup activity
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new RegestationFragment())
                        .commit();
            }
        });
        return view;
    }

    public void login( String user, String pass) {

        if (user.isEmpty() || pass.isEmpty())
            Toast.makeText(getActivity(), "Please enter your credentials correctly ", Toast.LENGTH_LONG).show();

        else
        Webservice.getInstance().getApi().Login( user , pass ).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (!response.body().getState().equals("0"))
                {
                    Toast.makeText(getActivity(), "Data Get Successfully", Toast.LENGTH_LONG).show();
                    userdata = response.body().getUserdata().get(0);
                    goToUserData();
                }

                else
                {
                    Toast.makeText(getActivity(), "There is no data matched", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "failure , check your connection", Toast.LENGTH_LONG).show();
                Log.e("login", "onFailure: ", t );
            }
        });
    }

    private void goToUserData() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new UserDetailsFragment())
                .commit();
    }
}