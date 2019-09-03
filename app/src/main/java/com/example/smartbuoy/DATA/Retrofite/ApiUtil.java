package com.example.smartbuoy.DATA.Retrofite;

public class ApiUtil {

    private static final String BASE_URL = "http://10.54.234.128:5002/";
    private static final String BASE_URL_WEB = "https://lampara.herokuapp.com/";

    public static RetrofitInterface getServiceClass(){
        return RetrofitAPI.getRetrofit(BASE_URL_WEB).create(RetrofitInterface.class);
    }

}
