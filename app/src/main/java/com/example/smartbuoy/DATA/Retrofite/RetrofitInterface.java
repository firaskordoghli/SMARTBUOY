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

    @GET("mob/plage/nearyou/{lat}/{lng}/{distance}")
    public Call<List<ItemHomePlage>> plageNearYou(@Path("lat") String lat,@Path("lng") String lng,@Path("distance") String distance);

    @GET("mob/plage/recommanded/{id}")
    public Call<List<ItemHomePlage>> plageRecommended(@Path("id") String id);

    @GET("mob/plage/tag/{tag}")
    public Call<List<ItemHomePlage>> plageByTag(@Path("id") String id);

    @GET("mob/plage/location")
    public Call<List<PlageDetailsMap>> allPlageMap();

    @GET("mob/plage/{id}/{idUser}")
    public Call<Plage> getPlageById(@Path("id") String id, @Path("idUser") String idUser);

    @GET("mob/user/going/future/{id}")
    public Call<List<Plan>> getPlan(@Path("id") String id);

    @GET("mob/event/upcome")
    public Call<List<Event>> eventUpcoming();

    @GET("mob/event/prev")
    public Call<List<Event>> eventsPrevious();

    @GET("mob/event/my/{id}")
    public Call<List<Event>> myEvents(@Path("id") String id);

    @Headers("Content-Type: application/json")
    @POST("mob/event")
    public Call<JsonObject> addEvent(@Body Event event);

    @GET("mob/event/{id}/{idUser}")
    public Call<Event> getEvent(@Path("id") String id,@Path("idUser") String idUser);

    @PUT("mob/user/follow")
    public  Call<JsonObject> followPlage(@Body JsonObject object);

    @GET("mob/user/follows/{id}")
    public Call<List<ItemHomePlage>> getListFavoris(@Path("id") String id);

    @PUT("mob/event/participer")
    public  Call<String> joinEvent(@Body JsonObject object);

    @GET("mob/rate/{idUser}/{idPlage}")
    public Call<Float> getRate(@Path("idUser") String idUser,@Path("idPlage") String id);

    @PUT("mob/plage/rate")
    public  Call<JsonObject> ratePlage(@Body JsonObject object);

}
