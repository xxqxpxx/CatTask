package com.example.cattask.WebService;

import com.example.cattask.Model.Image;
import com.example.cattask.Model.LoginResponse;
import com.example.cattask.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    @POST("users/add")
    Call<LoginResponse> Register(@Body User usr);

    @GET("login/{user}/{pass}")
    Call<LoginResponse> Login(@Path(value = "user", encoded = true) String user , @Path(value = "pass", encoded = true) String pass );

    @GET("users/current/{userid} ")
    Call<List<User>> getUserDetails(@Path(value = "userid", encoded = true) String id );

    @POST("users/update_imageprofile/{id}")
    Call<Object> uploadPicture(@Path(value = "id", encoded = true) String id ,@Body Image string);

    @GET("allusers")
    Call<List<User>> getAllUsers();

}
