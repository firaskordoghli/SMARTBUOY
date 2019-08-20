package com.example.smartbuoy.DATA.Retrofite;

public class ApiUtil {

    private static final String BASE_URL = "http://10.54.234.137:5002/";

    public static RetrofitInterface getServiceClass(){
        return RetrofitAPI.getRetrofit(BASE_URL).create(RetrofitInterface.class);
    }

}
