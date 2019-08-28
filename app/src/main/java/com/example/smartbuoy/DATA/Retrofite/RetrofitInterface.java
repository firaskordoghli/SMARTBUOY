package com.example.smartbuoy.DATA.Retrofite;

import com.example.smartbuoy.DATA.Models.Event;
import com.example.smartbuoy.DATA.Models.ItemHomePlage;
import com.example.smartbuoy.DATA.Models.Plage;
import com.example.smartbuoy.DATA.Models.PlageDetailsMap;
import com.example.smartbuoy.DATA.Models.Plan;
import com.example.smartbuoy.DATA.Models.User;
import com.google.gson.JsonObject;

import java.util.List;

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
    @POST("mob/auth")
    public Call<JsonObject> login (@Body JsonObject object);

    @Headers("Content-Type: application/json")
    @POST("mob/user")
    public Call<User> createUser (@Body User user);

    @GET("mob/user/verifyemail/{email}")
    public Call<JsonObject> verifyMmail(@Path("email") String email);

    @GET("mob/plage/")
    public Call<List<ItemHomePlage>> allplage();

    @GET("mob/plage/location")
    public Call<List<PlageDetailsMap>> allPlageMap();

    @GET("mob/plage/{id}")
    public Call<Plage> getPlageById(@Path("id") String id);

    @GET("mob/user/going/future/{id}")
    public Call<List<Plan>> getPlan(@Path("id") String id);

    @GET("mob/event")
    public Call<List<Event>> allEvents();

    @Headers("Content-Type: application/json")
    @POST("mob/event")
    public Call<JsonObject> addEvent (@Body Event event);

    @GET("mob/event/{id}")
    public Call<Event> getEvent(@Path("id") String id);



}
