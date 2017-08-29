package com.itheima.jason.swipedismissbehavior;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SwipeDismissBehavior behavior= new SwipeDismissBehavior();
        //设置滑动方向,any是不过左右,都可以
        behavior.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_ANY);
        behavior.setListener(listener);
        //通过根布局,来拿到控件的属性
        CoordinatorLayout.LayoutParams params=
                (CoordinatorLayout.LayoutParams) findViewById(R.id.tv).getLayoutParams();
        params.setBehavior(behavior);
    }
    private SwipeDismissBehavior.OnDismissListener listener = new SwipeDismissBehavior.OnDismissListener() {
        @Override
        public void onDismiss(View view) {

        }

        @Override
        public void onDragStateChanged(int state) {

        }
    };
}
