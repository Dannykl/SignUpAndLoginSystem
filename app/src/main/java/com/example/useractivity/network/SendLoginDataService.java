package com.example.useractivity.network;


import com.example.useractivity.model.Credential;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface SendLoginDataService
{
//      @GET("/tune/api/album/login.php")
//      Call<Credential> sendLogInAData(@Query("email") String email, @Query("password") String password);

//      @POST("/tune/api/album/login.php")
//      @FormUrlEncoded
//      Call<Credential> sendLogInAData(@Field("emailAddress") String emailAddress, @Field("password") String password);



//      @GET("/tune/api/album/login.php/{email}/{password}")
//      Call<Credential> sendLogInAData(@Path("email") String email, @Path("password") String password);


      @FormUrlEncoded
      @POST("/tune/api/album/login.php")
      Call<Credential> sendLogInAData(@Field("email") String email, @Field("password") String password);

      @FormUrlEncoded
      @POST("/tune/api/album/signup.php")
      Call<Credential> sendSignUpAData(@Field("firstName") String firstName, @Field("lastName") String lastName,
                                      @Field("email") String email, @Field("password") String password);
}


