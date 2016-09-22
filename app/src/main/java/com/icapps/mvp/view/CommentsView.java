package com.icapps.mvp.view;

import com.icapps.mvp.model.Comment;
import com.icapps.mvp.model.Post;

import net.grandcentrix.thirtyinch.TiView;
import net.grandcentrix.thirtyinch.callonmainthread.CallOnMainThread;
import net.grandcentrix.thirtyinch.distinctuntilchanged.DistinctUntilChanged;

import java.util.List;

/**
 * Created by maartenvangiel on 20/09/16.
 */
public interface CommentsView extends TiView {
    @CallOnMainThread
    @DistinctUntilChanged
    void setComments(List<Comment> comments);

    @CallOnMainThread
    @DistinctUntilChanged
    void setLoading(boolean isLoading);

    @CallOnMainThread
    void showError(Throwable error);

    @CallOnMainThread
    @DistinctUntilChanged
    void setPost(Post post);
}
