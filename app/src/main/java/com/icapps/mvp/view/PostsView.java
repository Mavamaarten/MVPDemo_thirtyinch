package com.icapps.mvp.view;

import com.icapps.mvp.model.Post;

import net.grandcentrix.thirtyinch.TiView;
import net.grandcentrix.thirtyinch.callonmainthread.CallOnMainThread;
import net.grandcentrix.thirtyinch.distinctuntilchanged.DistinctUntilChanged;

import java.util.List;

/**
 * Created by maartenvangiel on 20/09/16.
 */

public interface PostsView extends TiView {
    @CallOnMainThread
    @DistinctUntilChanged
    void setPosts(List<Post> posts);

    @CallOnMainThread
    @DistinctUntilChanged
    void setLoading(boolean isLoading);

    @CallOnMainThread
    void showError(Throwable error);
}
