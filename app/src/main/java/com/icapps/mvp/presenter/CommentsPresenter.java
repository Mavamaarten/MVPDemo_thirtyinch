package com.icapps.mvp.presenter;

import com.icapps.mvp.model.Comment;
import com.icapps.mvp.model.Post;
import com.icapps.mvp.model.PostRepository;
import com.icapps.mvp.view.CommentsView;

import net.grandcentrix.thirtyinch.TiPresenter;
import net.grandcentrix.thirtyinch.rx.RxTiPresenterSubscriptionHandler;
import net.grandcentrix.thirtyinch.rx.RxTiPresenterUtils;

import java.util.List;

import rx.schedulers.Schedulers;

/**
 * Created by maartenvangiel on 20/09/16.
 */
public class CommentsPresenter extends TiPresenter<CommentsView> {
    private PostRepository postRepository;
    private RxTiPresenterSubscriptionHandler rxHelper = new RxTiPresenterSubscriptionHandler(this);
    private List<Comment> comments;

    private final Post post;

    public CommentsPresenter(PostRepository postRepository, final Post post) {
        this.postRepository = postRepository;
        this.post = post;
    }

    public void loadComments() {
        getView().setLoading(true);

        rxHelper.manageSubscription(
                postRepository.getPostComments(post.getId())
                        .subscribeOn(Schedulers.newThread())
                        .compose(RxTiPresenterUtils.deliverLatestToView(this))
                        .subscribe(comments -> {
                            this.comments = comments;
                            getView().setComments(comments);
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

        getView().setPost(post);

        if (comments == null) {
            loadComments();
        } else {
            getView().setComments(comments);
            getView().setLoading(false);
        }
    }
}
