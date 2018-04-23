package com.example.cattask.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.cattask.Model.LoginResponse;
import com.example.cattask.Model.User;
import com.example.cattask.R;
import com.example.cattask.WebService.Webservice;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegestationFragment extends Fragment {

    EditText firstname, midname , lastname , username , password , confirmPassword , email , mobile ;
    RadioGroup genderRadioGroup;
    Button button;
    int gender = 0;

    public RegestationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_regestation, container, false);
        button = (Button) view.findViewById(R.id.Regbutton);
        firstname = (EditText) view.findViewById(R.id.firstname);
        midname = (EditText) view.findViewById(R.id.midname);
        lastname = (EditText) view.findViewById(R.id.lastname);
        password = (EditText) view.findViewById(R.id.password);
        username = (EditText) view.findViewById(R.id.username);
        confirmPassword = (EditText) view.findViewById(R.id.confirmpassword);
        email = (EditText) view.findViewById(R.id.email);
        mobile = (EditText) view.findViewById(R.id.number);
        genderRadioGroup = (RadioGroup) view.findViewById(R.id.genderRadioGroup);
        genderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.maleRadioButton: {
                        gender = 0;
                        break;
                    }
                    case R.id.femaleRadioButton: {
                        gender = 1;
                        break;
                    }
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               register();
            }
        });
        return view;
    }

    private void register() {
        User user = new User();

        user.setEmail(email.getText().toString());
        user.setFirstname(firstname.getText().toString());
        user.setMidname(midname.getText().toString());
        user.setLastname(lastname.getText().toString());
        user.setUsername(username.getText().toString());
        user.setPassword(password.getText().toString());
        user.setConfirm_password(confirmPassword.getText().toString());
        user.setMobile_number(mobile.getText().toString());

        Webservice.getInstance().getApi().Register( user ).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                    if (!response.body().getState().equals("1")) {
                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                        goToLogin();
                    }
                    else
                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "failure , check your connection", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void goToLogin() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new LoginFragment())
                .commit();
    }


}
