package com.icapps.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.icapps.mvp.MVPApplication;
import com.icapps.mvp.R;
import com.icapps.mvp.adapter.PostsAdapter;
import com.icapps.mvp.model.Post;
import com.icapps.mvp.presenter.PostsPresenter;

import net.grandcentrix.thirtyinch.TiActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostsActivity extends TiActivity<PostsPresenter, PostsView> implements SwipeRefreshLayout.OnRefreshListener, PostsView, PostsAdapter.PostsAdapterListener {
    @BindView(R.id.rcv_posts) RecyclerView postsRecycler;
    @BindView(R.id.contentView) SwipeRefreshLayout swipeRefresh;

    PostsAdapter postsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        ButterKnife.bind(this);

        swipeRefresh.setOnRefreshListener(this);

        postsAdapter = new PostsAdapter(this);
        postsRecycler.setAdapter(postsAdapter);
        postsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @NonNull
    @Override
    public PostsPresenter providePresenter() {
        return new PostsPresenter(((MVPApplication) getApplication()).getPostRepository());
    }

    @Override
    public void setPosts(List<Post> posts) {
        postsAdapter.setPosts(posts);
    }

    @Override
    public void setLoading(boolean isLoading) {
        swipeRefresh.setRefreshing(isLoading);
    }

    @Override
    public void showError(Throwable error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRefresh() {
        getPresenter().loadPosts();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onPostClicked(Post post, int position, View titleView, View bodyView) {
        Intent intent = new Intent(this, CommentsActivity.class);
        intent.putExtra("post", post);

        Pair<View, String> p1 = Pair.create(titleView, "postTitle");
        Pair<View, String> p2 = Pair.create(bodyView, "postBody");
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, p1, p2);

        startActivity(intent, options.toBundle());
    }
}
