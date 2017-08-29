package com.itheima.jason.myapplication;

import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //使用toolbar
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //模版写法
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<String> list =new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("条目"+i);
        }
        recyclerView.setAdapter(new MyAdapter(list));
        //找到bottomSheetBehavior
        //当fab触发的时候,让bottomSheetBehavior触发的显示或隐藏
        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.sheet_layout));
        bottomSheetBehavior.setPeekHeight(0);
        scale_behavior scaleBehavior= scale_behavior.from(findViewById(R.id.fab));
        scaleBehavior.setOnStateChangeListener(listener);
    }
    private scale_behavior.OnStateChangeListener listener = new scale_behavior.OnStateChangeListener() {
        @Override
        public void onchange(boolean isShow) {
            bottomSheetBehavior.setState(isShow? BottomSheetBehavior.STATE_EXPANDED : BottomSheetBehavior.STATE_COLLAPSED);
        }
    };
}
