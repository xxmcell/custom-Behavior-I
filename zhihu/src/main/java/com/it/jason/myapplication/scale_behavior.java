package com.it.jason.myapplication;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by jason on 2017/7/5.
 */

public class scale_behavior extends FloatingActionButton.Behavior {
    public scale_behavior(Context context, AttributeSet attrs){
        super();
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes== ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        //告诉bottomsheetbehavior隐藏
        if((dyConsumed>0 && dyUnconsumed==0)||(dyConsumed==0 && dyUnconsumed>0) && !isAnimate){
            AnimatorUtil.scaleHide(child,Listener);
            if(onStateChangeListener!=null){
                onStateChangeListener.onchange(false);
            }
        }else if((dyConsumed<0 && dyUnconsumed==0)||(dyConsumed==0 && dyUnconsumed<0)&&!isAnimate){
            if(onStateChangeListener!=null){
                onStateChangeListener.onchange(true);
            }
            AnimatorUtil.scaleShow(child,Listener);
        }
    }
    //保证在一定条件下播放动画
    private boolean isAnimate=false;
    private ViewPropertyAnimatorListener Listener= new ViewPropertyAnimatorListener() {
        @Override
        public void onAnimationStart(View view) {
                isAnimate=true;
        }

        @Override
        public void onAnimationEnd(View view) {
                isAnimate=true;
        }

        @Override
        public void onAnimationCancel(View view) {
                isAnimate=false;
        }
    };
    //给scale_behavior制造一个from方法,以方便回调使用
    public static <V extends View> scale_behavior from(V view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (!(params instanceof CoordinatorLayout.LayoutParams)) {
            throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
        }
        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) params)
                .getBehavior();
        if (!(behavior instanceof scale_behavior)) {
            throw new IllegalArgumentException(
                    "The view is not associated with scale_behavior");
        }
        return (scale_behavior) behavior;
    }
    public interface OnStateChangeListener{
        void onchange(boolean isShow);
    }
    public OnStateChangeListener onStateChangeListener;
    public void setOnStateChangeListener (OnStateChangeListener onStateChangeListener){
        this.onStateChangeListener=onStateChangeListener;
    }
}
