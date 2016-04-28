package com.mx.presenter;

import com.mx.view.MvpView;

/**
 * Created by boobooL on 2016/4/28 0028
 * Created 邮箱 ：boobooMX@163.com
 */
public class BasePresenter<T extends MvpView>implements Presenter<T> {
    private T mMvpView;
    @Override
    public void attachView(T mvpView) {
        mMvpView=mvpView;
    }

    @Override
    public void detachView() {
            mMvpView=null;
    }

    public  boolean isViewAttached(){
        return  mMvpView!=null;
    }
    public T getMvpView(){
        return mMvpView;
    }

    public void checkViewAttached(){
        if(!isViewAttached())throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends  RuntimeException{
        public MvpViewNotAttachedException(){
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
