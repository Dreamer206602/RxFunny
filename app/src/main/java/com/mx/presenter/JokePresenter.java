package com.mx.presenter;

import com.mx.api.JokeApi;
import com.mx.api.RxService;
import com.mx.model.ContentlistEntity;
import com.mx.model.JokeEntity;
import com.mx.view.JokeView;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by boobooL on 2016/4/28 0028
 * Created 邮箱 ：boobooMX@163.com
 */
public class JokePresenter extends BasePresenter<JokeView> {

    @Override
    public void attachView(JokeView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void loadList(final  int page){
        RxService.createApi(JokeApi.class)
                .getJoke(page)
                .subscribeOn(Schedulers.io())
                .map(new Func1<JokeEntity, List<ContentlistEntity>>() {
                    @Override
                    public List<ContentlistEntity> call(JokeEntity jokeEntity) {
                        return jokeEntity.getShowapi_res_body().getContentlist();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ContentlistEntity>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        com.orhanobut.logger.Logger.e(e.getMessage());
                        getMvpView().showEmpty(null,null);
                    }

                    @Override
                    public void onNext(List<ContentlistEntity> contentlistEntities) {
                            if(page==1){
                                getMvpView().refresh(contentlistEntities);
                            }else{
                                getMvpView().loadMore(contentlistEntities);
                            }
                    }
                });
    }
}
