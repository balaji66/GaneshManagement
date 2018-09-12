package com.durga.balaji66.ganeshmanagement.Apis;

import com.durga.balaji66.ganeshmanagement.Models.Game;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("adminLogin")
    Call<ResponseBody> AdminLogin(
            @Field("admin_mobile") String mobile,
            @Field("admin_password") String password
    );


    @GET("registeredPlayerAnxiety")
    Call<List<Game>> registeredPlayerAnxiety();

    @GET("registeredPlayerBalloon")
    Call<List<Game>> registeredPlayerBalloon();

    @GET("registeredPlayerBubble")
    Call<List<Game>> registeredPlayerBubble();

    @GET("getAllRegisteredPlayers")
    Call<List<Game>> getAllRegisteredPlayers();


}
