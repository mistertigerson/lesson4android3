package com.example.lesson4android3.remote;

import com.example.lesson4android3.data.models.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface HerokuAPI {

    @GET("posts")
    Call<List<PostModel>> getPosts();

    @POST("posts")
    Call<PostModel> createMocker(@Body PostModel model);

    @PUT("posts/{id}")
    Call<PostModel> update(@Path("id") String id, @Body PostModel model);

    @DELETE("posts/{id}")
    Call<PostModel> deleteMockerModel(
            @Path("id") Integer id
    );

}
