package com.shanaptech.bloodbank.Retrofit;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "http://ipda3-tech.com/blood-bank/api/v1/";

    public static ApiPostman getAPIService() {

        return RetrofitClient.getClient().create(ApiPostman.class);
    }
}
