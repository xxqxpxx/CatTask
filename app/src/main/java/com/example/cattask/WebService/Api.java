package com.example.cattask.WebService;

import com.example.cattask.Model.LoginResponse;
import com.example.cattask.Model.UploadObject;
import com.example.cattask.Model.User;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Api {

    @POST("users/add")
    Call<LoginResponse> Register(@Body User usr);

    @GET("login/{user}/{pass}")
    Call<LoginResponse> Login(@Path(value = "user", encoded = true) String user , @Path(value = "pass", encoded = true) String pass );

    @GET("users/current/{userid} ")
    Call<List<User>> getUserDetails(@Path(value = "userid", encoded = true) String id );

    @POST("users/update_imageprofile/{id}")
    Call<UploadObject> uploadPicture(@Path(value = "id", encoded = true) String id , @Part MultipartBody.Part file, @Part("name") RequestBody name);


   /* @POST("token")
    Call<MainResponse> Login(@Field("grant_type") String grant_type, @Field("username") String username, @Field("password") String password);



    @POST("api/trip/starttrip")
    Call<tripModel> startTrip(@HeaderMap Map<String, String> headers, @Body String tripId );

    @POST("api/trip/endtrip")
    Call<tripModel> endTrip(@HeaderMap Map<String, String> headers, @Body String tripId );

    @POST("api/transactions/CollectCash")
    Call<UserDTO> collectCash(@HeaderMap Map<String, String> headers , @Body CollectCashModel CollectCashModel);

    @GET("api/users/FindUsers")
    Call<List<UserDTO>> FindUsers( @HeaderMap Map<String, String> headers , @Query("phoneNumber") String phoneNumber);

    @POST("api/ticket/hopoff")
    Call<ResponseBody> hopoff(@HeaderMap Map<String, String> headers , @Body HopOnOFFModel hopOnOFFModel);

    @POST("api/ticket/hopon")
    Call<ResponseBody> hopon(@HeaderMap Map<String, String> headers , @Body HopOnOFFModel hopOnOFFModel);

    @POST("api/TripLocation/ReportTripLocations")
    Call<Integer> SendLocation(@HeaderMap Map<String, String> headers , @Body List<LocationModel> locationModels);

    @GET("/api/Transactions/GetMyCashCollections")
    Call<CollectionModel> getCollection(@HeaderMap Map<String, String> headers, @QueryMap Map<String, String> params);
*/
}
