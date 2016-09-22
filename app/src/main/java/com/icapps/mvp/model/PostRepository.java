package com.icapps.mvp.model;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by maartenvangiel on 20/09/16.
 */

public interface PostRepository {

    @GET("/posts")
    Observable<List<Post>> getPosts();

    @GET("/posts/{id}")
    Observable<Post> getPost(@Path("id") int postId);

    @GET("/posts/{id}/comments")
    Observable<List<Comment>> getPostComments(@Path("id") int postId);

    @GET("/posts")
    Observable<List<Post>> getPostsByUser(@Query("userId") int userId);
}
