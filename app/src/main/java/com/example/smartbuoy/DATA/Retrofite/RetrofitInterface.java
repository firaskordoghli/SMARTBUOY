package com.example.smartbuoy.DATA.Retrofite;

import com.example.smartbuoy.DATA.Models.User;
import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @Headers("Content-Type: application/json")
    @POST("mob/user")
    public Call<User> createUser (@Body User user);

    @GET("mob/user/verifyemail/{email}")
    public Call<JsonObject> verifyMmail(@Path("email") String email);


    @Headers("Content-Type: application/json")
    @POST("mob/auth")
    public Call<JsonObject> login (@Body JsonObject object);

}
