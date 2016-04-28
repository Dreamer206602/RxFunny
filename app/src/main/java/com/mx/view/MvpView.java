package com.mx.view;

import android.view.View;

/**
 * Created by boobooL on 2016/4/28 0028
 * Created 邮箱 ：boobooMX@163.com
 */
public interface MvpView {
    void showLoading(String msg);
    void hideLoading();

    void showError(String msg, View.OnClickListener onClickListener);
    void showEmpty(String msg,View.OnClickListener onClickListener);
    void showEmpty(String msg,View.OnClickListener onClickListener,int imgageId);
    void showNetError(View.OnClickListener onClickListener);

}
