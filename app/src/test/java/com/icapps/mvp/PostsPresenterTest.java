package com.icapps.mvp;

import com.icapps.mvp.model.PostRepository;
import com.icapps.mvp.presenter.PostsPresenter;
import com.icapps.mvp.view.PostsView;

import org.junit.Test;

import java.util.ArrayList;

import rx.Observable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by maartenvangiel on 21/09/16.
 */

public class PostsPresenterTest extends BaseTestWithRxThreadSupport{

    @Test public void testLoadPosts(){
        PostRepository postRepository = mock(PostRepository.class);
        when(postRepository.getPosts()).thenReturn(Observable.just(new ArrayList<>()));

        PostsView postView = mock(PostsView.class);

        PostsPresenter presenter = new PostsPresenter(postRepository);
        presenter.create();
        presenter.bindNewView(postView);
        presenter.wakeUp();

        verify(postView).setLoading(true);
        verify(postView).setPosts(anyList());
        verify(postView).setLoading(false);
        verify(postView, never()).showError(any());
    }

    @Test public void testPostsError(){
        PostRepository postRepository = mock(PostRepository.class);
        when(postRepository.getPosts()).thenReturn(Observable.error(new Exception()));

        PostsView postView = mock(PostsView.class);

        PostsPresenter presenter = new PostsPresenter(postRepository);
        presenter.create();
        presenter.bindNewView(postView);
        presenter.wakeUp();

        verify(postView).setLoading(true);
        verify(postView).setLoading(false);
        verify(postView).showError(any());
    }

}
