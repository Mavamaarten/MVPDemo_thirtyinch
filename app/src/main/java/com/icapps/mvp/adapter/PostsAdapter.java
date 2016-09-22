package com.icapps.mvp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.icapps.mvp.R;
import com.icapps.mvp.model.Post;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by maartenvangiel on 20/09/16.
 */

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
    private List<Post> posts;
    private PostsAdapterListener listener;

    public PostsAdapter(PostsAdapterListener listener) {
        this.listener = listener;
        posts = new ArrayList<>();
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.post_title)
        TextView postTitle;
        @BindView(R.id.post_body)
        TextView postBody;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row_post, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.postBody.setText(post.getBody());
        holder.postTitle.setText(post.getTitle());
        holder.itemView.setOnClickListener(v -> {
            listener.onPostClicked(post, position, holder.postTitle, holder.postBody);
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public interface PostsAdapterListener{
        void onPostClicked(Post post, int position, View titleView, View bodyView);
    }
}
