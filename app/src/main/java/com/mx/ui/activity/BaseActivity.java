package com.mx.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mx.view.MvpView;

/**
 * Created by boobooL on 2016/4/28 0028
 * Created 邮箱 ：boobooMX@163.com
 */
public class BaseActivity extends AppCompatActivity implements MvpView {
    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg, View.OnClickListener onClickListener) {

    }

    @Override
    public void showEmpty(String msg, View.OnClickListener onClickListener) {

    }

    @Override
    public void showEmpty(String msg, View.OnClickListener onClickListener, int imgageId) {

    }

    @Override
    public void showNetError(View.OnClickListener onClickListener) {

    }
}
