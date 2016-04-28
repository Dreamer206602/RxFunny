package com.mx.presenter;

import com.mx.view.MvpView;

/**
 * Created by boobooL on 2016/4/28 0028
 * Created 邮箱 ：boobooMX@163.com
 */
public interface Presenter<V extends MvpView> {
    void attachView(V mvpView);
    void detachView();
}
