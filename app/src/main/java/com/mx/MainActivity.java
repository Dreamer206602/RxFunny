package com.mx;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mx.common.AutoLoadRecyclerView;
import com.mx.common.DividerItemDecoration;
import com.mx.model.ContentlistEntity;
import com.mx.presenter.JokePresenter;
import com.mx.ui.activity.BaseActivity;
import com.mx.util.TimeUtil;
import com.mx.view.JokeView;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements JokeView,
        SwipeRefreshLayout.OnRefreshListener, AutoLoadRecyclerView.loadMoreListener {

    @Bind(R.id.record_RecyclerView)
    AutoLoadRecyclerView mAutoLoadRecyclerView;
    @Bind(R.id.joke_refresh_layout)
    SwipeRefreshLayout mJokeRefreshLayout;
    @Bind(R.id.common_error_txt)
    TextView mCommonErrorTxt;
    @Bind(R.id.retry_btn)
    Button mRetryBtn;
    @Bind(R.id.common_error)
    RelativeLayout mCommonError;
    private JokePresenter mJokePresenter;
    private int page = 1;
    private List<ContentlistEntity> mContentlistEntities;
    private CommonAdapter<ContentlistEntity> mCommonAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
        initData();
        loadData();
    }


    private void initView() {
        mJokeRefreshLayout.setOnRefreshListener(this);
        mAutoLoadRecyclerView.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL_LIST));
        mAutoLoadRecyclerView.setLoadMoreListener(this);
    }

    private void initData() {

        mContentlistEntities = new ArrayList<>();
        mCommonAdapter = new CommonAdapter<ContentlistEntity>(this, R.layout.joke_list_item, mContentlistEntities) {
            @Override
            public void convert(ViewHolder holder, ContentlistEntity contentlistEntity) {

                holder.setText(R.id.title, "#" + contentlistEntity.getTitle() + "")
                        .setText(R.id.time, TimeUtil.getDateBySplit(contentlistEntity.getCt()))
                        .setText(R.id.content, Html.fromHtml(contentlistEntity.getText().toString()) + "");

            }
        };

        mAutoLoadRecyclerView.setAdapter(mCommonAdapter);
        mJokePresenter = new JokePresenter();
        mJokePresenter.attachView(this);

    }

    private void loadData() {
        mJokePresenter.loadList(page);
        page++;
    }

    //下拉SwipeRefreshLayout的加载内容
    @Override
    public void onRefresh() {
        page = 1;
        loadData();
    }


    /**
     * RecyclerView的加载更多的回调
     */
    @Override
    public void onLoadMore() {
        loadData();
    }


    /**
     * 回调 以下三个方法是presenter回调的函数 用于更新界面
     * @param data
     */
    @Override
    public void refresh(List<ContentlistEntity> data) {
        mCommonError.setVisibility(View.GONE);
        mContentlistEntities.clear();
        mContentlistEntities.addAll(data);
        mCommonAdapter.notifyDataSetChanged();
        mJokeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void loadMore(List<ContentlistEntity> data) {
        mCommonError.setVisibility(View.GONE);
        mContentlistEntities.addAll(data);
        mCommonAdapter.notifyDataSetChanged();
        mAutoLoadRecyclerView.setLoading(false);

    }

    @Override
    public void showError(String msg, View.OnClickListener onClickListener) {
        super.showError(msg, onClickListener);
        mCommonError.setVisibility(View.VISIBLE);
        mAutoLoadRecyclerView.setLoading(false);
        mJokeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showEmpty(String msg, View.OnClickListener onClickListener) {
        super.showEmpty(msg, onClickListener);
        mCommonError.setVisibility(View.VISIBLE);
        mAutoLoadRecyclerView.setLoading(false);
        mJokeRefreshLayout.setRefreshing(false);
    }

    @OnClick(R.id.retry_btn)
    void setRetryBtnClick() {
        onRefresh();
    }

}
