package com.mx.view;

import com.mx.model.ContentlistEntity;

import java.util.List;

/**
 * Created by boobooL on 2016/4/28 0028
 * Created 邮箱 ：boobooMX@163.com
 */
public interface JokeView {
    void refresh(List<ContentlistEntity>data);//刷新
    void loadMore(List<ContentlistEntity>data);//加载更多
}
