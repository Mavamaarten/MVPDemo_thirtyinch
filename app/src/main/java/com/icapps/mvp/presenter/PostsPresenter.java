package com.icapps.mvp.presenter;

import com.icapps.mvp.model.Post;
import com.icapps.mvp.model.PostRepository;
import com.icapps.mvp.view.PostsView;

import net.grandcentrix.thirtyinch.TiPresenter;
import net.grandcentrix.thirtyinch.rx.RxTiPresenterSubscriptionHandler;
import net.grandcentrix.thirtyinch.rx.RxTiPresenterUtils;

import java.util.List;

import rx.schedulers.Schedulers;

/**
 * Created by maartenvangiel on 20/09/16.
 */
public class PostsPresenter extends TiPresenter<PostsView> {
    private PostRepository postRepository;
    private RxTiPresenterSubscriptionHandler rxHelper = new RxTiPresenterSubscriptionHandler(this);
    private List<Post> posts;

    public PostsPresenter(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void loadPosts() {
        getView().setLoading(true);

        rxHelper.manageSubscription(
                postRepository.getPosts()
                        .subscribeOn(Schedulers.newThread())
                        .compose(RxTiPresenterUtils.deliverLatestToView(this))
                        .subscribe(posts -> {
                            this.posts = posts;
                            getView().setPosts(posts);
                            getView().setLoading(false);
                        }, throwable -> {
                            getView().showError(throwable);
                            getView().setLoading(false);
                        })
        );
    }

    @Override
    protected void onWakeUp() {
        super.onWakeUp();

        if (posts == null) {
            loadPosts();
        } else {
            getView().setLoading(false);
            getView().setPosts(posts);
        }
    }
}
