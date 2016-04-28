package com.mx.common;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by boobooL on 2016/4/28 0028
 * Created 邮箱 ：boobooMX@163.com
 */
public class AutoLoadRecyclerView extends RecyclerView {

    public interface  loadMoreListener{
        void onLoadMore();
    }
    private loadMoreListener mLoadMoreListener;
    private AutoLoadScroller mAutoLoadScroller;
    private boolean isLoading=false;

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public AutoLoadRecyclerView(Context context) {
        this(context,null);
    }

    public AutoLoadRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mAutoLoadScroller=new AutoLoadScroller();
        addOnScrollListener(mAutoLoadScroller);

    }

    public void setLoadMoreListener(loadMoreListener loadMoreListener) {
        mLoadMoreListener = loadMoreListener;
    }

    private class AutoLoadScroller extends OnScrollListener{
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if(getLayoutManager() instanceof LinearLayoutManager){
                int lastVisiblePos=((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
                int itemCount=getAdapter().getItemCount();
                if(mLoadMoreListener!=null&&!isLoading&&lastVisiblePos>itemCount-2&&dy>0){
                    mLoadMoreListener.onLoadMore();
                    isLoading=true;
                }
            }
        }
    }
}
