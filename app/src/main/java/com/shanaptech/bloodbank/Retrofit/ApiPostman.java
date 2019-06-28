package com.shanaptech.bloodbank.Retrofit;

import com.shanaptech.bloodbank.Data.Governorates.Governorates;
import com.shanaptech.bloodbank.Data.login.Login;
import com.shanaptech.bloodbank.Utils.UserInfo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiPostman {


    @GET("governorates")
    Call<Governorates> getGovernorates();

    @GET("cities")
    Call<Governorates> getcities(@Query("governorate_id") int governorate_id);


    @GET("blood-types")
    Call<Governorates> getBloodTypes();

    @POST("login")
    Call<Login> Post_info(@Field("phone") String phone
            , @Field("password") String password);










}
