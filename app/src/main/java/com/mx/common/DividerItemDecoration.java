package com.mx.common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mx.R;

/**
 * Created by boobooL on 2016/4/28 0028
 * Created 邮箱 ：boobooMX@163.com
 */

/**
 * 添加RecyclerView的分割线
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
    private Drawable mDivider;
    private Context mContext;
    private int mOrientation;

    public DividerItemDecoration(Context context, int orientation) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            mDivider = context.getDrawable(R.drawable.list_divider);
        } else {
            mDivider = context.getResources().getDrawable(R.drawable.list_divider);
        }
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {
       if(orientation!=HORIZONTAL_LIST&&orientation!=VERTICAL_LIST){
           throw  new IllegalArgumentException("invalid orientation");

       }
        mOrientation=orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if(mOrientation==VERTICAL_LIST){
            drawVertical(c,parent);
        }else{
            drawHoriaontal(c,parent);
        }
    }

    private void drawHoriaontal(Canvas c, RecyclerView parent) {
        final int top=parent.getPaddingTop();
        final int bottom=parent.getHeight()-parent.getPaddingBottom();

        final  int childCount=parent.getChildCount();
        for (int i = 0; i <childCount ; i++) {
            final View child=parent.getChildAt(i);
            final RecyclerView.LayoutParams params= (RecyclerView.LayoutParams) child.getLayoutParams();
            final  int left=child.getRight()+params.rightMargin;
            final  int right=left+mDivider.getIntrinsicHeight();
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        final int left=parent.getPaddingLeft();
        final int right=parent.getWidth()-parent.getPaddingRight();
        final  int childCount=parent.getChildCount();
        for (int i = 0; i <childCount ; i++) {
            final View child=parent.getChildAt(i);
            final  RecyclerView.LayoutParams params= (RecyclerView.LayoutParams) child.getLayoutParams();

            final int top=child.getBottom()+params.bottomMargin;
            final  int bottom=top+mDivider.getIntrinsicHeight();
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if(mOrientation==VERTICAL_LIST){
            outRect.set(0,0,0,mDivider.getIntrinsicHeight());
        }else{
            outRect.set(0,0,mDivider.getIntrinsicWidth(),0);
        }
    }
}
