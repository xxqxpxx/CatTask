package com.example.cattask.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.cattask.Model.User;
import com.example.cattask.R;

public class MainActivity extends AppCompatActivity {
    public  static User userdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLogin();

    }

    private void initLogin() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new LoginFragment())
                .commit();
    }
}
