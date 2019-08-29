package com.example.smartbuoy.DATA.Retrofite;

import com.example.smartbuoy.DATA.Models.Event;
import com.example.smartbuoy.DATA.Models.ItemHomePlage;
import com.example.smartbuoy.DATA.Models.Plage;
import com.example.smartbuoy.DATA.Models.PlageDetailsMap;
import com.example.smartbuoy.DATA.Models.Plan;
import com.example.smartbuoy.DATA.Models.User;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitInterface {

    @Headers("Content-Type: application/json")
    @POST("mob/auth")
    public Call<JsonObject> login(@Body JsonObject object);

    @Headers("Content-Type: application/json")
    @POST("mob/user")
    public Call<User> createUser(@Body User user);

    @GET("mob/user/verifyemail/{email}")
    public Call<JsonObject> verifyMmail(@Path("email") String email);

    @GET("mob/plage/")
    public Call<List<ItemHomePlage>> allplage();

    @GET("mob/plage/location")
    public Call<List<PlageDetailsMap>> allPlageMap();

    @GET("mob/plage/{id}/{idUser}")
    public Call<Plage> getPlageById(@Path("id") String id, @Path("idUser") String idUser);

    @GET("mob/user/going/future/{id}")
    public Call<List<Plan>> getPlan(@Path("id") String id);

    @GET("mob/event")
    public Call<List<Event>> allEvents();

    @Headers("Content-Type: application/json")
    @POST("mob/event")
    public Call<JsonObject> addEvent(@Body Event event);

    @GET("mob/event/{id}")
    public Call<Event> getEvent(@Path("id") String id);

    @PUT("mob/user/follow")
    public  Call<JsonObject> followPlage(@Body JsonObject object);

    @GET("mob/user/follows/{id}")
    public Call<List<ItemHomePlage>> getListFavoris(@Path("id") String id);

    @PUT("mob/event/participer")
    public  Call<Event> joinEvent(@Body JsonObject object);


}
