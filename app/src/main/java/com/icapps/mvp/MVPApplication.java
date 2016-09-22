package com.icapps.mvp;

import android.app.Application;

import com.icapps.mvp.model.PostRepository;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by maartenvangiel on 20/09/16.
 */

public class MVPApplication extends Application {

    private PostRepository postRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        postRepository = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://jsonplaceholder.typicode.com")
                .build()
                .create(PostRepository.class);
    }

    public PostRepository getPostRepository() {
        return postRepository;
    }
}
