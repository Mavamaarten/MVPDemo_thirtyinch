package com.icapps.mvp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.icapps.mvp.R;
import com.icapps.mvp.model.Comment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by maartenvangiel on 20/09/16.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {
    private List<Comment> comments;

    public CommentsAdapter() {
        comments = new ArrayList<>();
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.comment_body) TextView commentBody;
        @BindView(R.id.comment_name) TextView commentName;
        @BindView(R.id.comment_email) TextView commentEmail;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row_comment, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Comment comment = comments.get(position);
        holder.commentBody.setText(comment.getBody());
        holder.commentName.setText(comment.getName());
        holder.commentEmail.setText(comment.getEmail());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }
}
