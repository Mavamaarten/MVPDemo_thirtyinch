package com.icapps.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.icapps.mvp.MVPApplication;
import com.icapps.mvp.R;
import com.icapps.mvp.adapter.CommentsAdapter;
import com.icapps.mvp.model.Comment;
import com.icapps.mvp.model.Post;
import com.icapps.mvp.presenter.CommentsPresenter;

import net.grandcentrix.thirtyinch.TiActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by maartenvangiel on 20/09/16.
 */

public class CommentsActivity extends TiActivity<CommentsPresenter, CommentsView> implements CommentsView, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.rcv_comments) RecyclerView commentsRecycler;
    @BindView(R.id.post_body) TextView postBody;
    @BindView(R.id.post_title) TextView postTitle;
    @BindView(R.id.contentView) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.comments) TextView commentsLabel;

    private Post post;
    private CommentsAdapter commentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        ButterKnife.bind(this);

        setTitle("Post details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        swipeRefreshLayout.setOnRefreshListener(this);

        commentsAdapter = new CommentsAdapter();

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setAutoMeasureEnabled(true);

        commentsRecycler.setLayoutManager(layoutManager);
        commentsRecycler.setAdapter(commentsAdapter);
        commentsRecycler.setNestedScrollingEnabled(false);
    }

    @NonNull
    @Override
    public CommentsPresenter providePresenter() {
        return new CommentsPresenter(((MVPApplication) getApplication()).getPostRepository(), ((Post) getIntent().getParcelableExtra("post")));
    }

    @Override
    public void setComments(List<Comment> comments) {
        commentsAdapter.setComments(comments);
        commentsLabel.setText(getString(R.string.comments_label, String.valueOf(comments.size())));
    }

    @Override
    public void setLoading(boolean isLoading) {
        swipeRefreshLayout.setRefreshing(isLoading);
    }

    @Override
    public void showError(Throwable error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void setPost(Post post) {
        this.post = post;
        postBody.setText(post.getBody());
        postTitle.setText(post.getTitle());
    }

    @Override
    public void onRefresh() {
        getPresenter().loadComments();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            supportFinishAfterTransition();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
