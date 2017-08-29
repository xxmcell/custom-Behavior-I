package com.itheima.jason.bottomsheelbehavior;

import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button bottom_sheet;
    private BottomSheetBehavior<View> bottomSheetBehavior;
    private Button bottom_dialog;
    private BottomSheetDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottom_sheet = (Button) findViewById(R.id.bt_bottom_sheet_control);
        bottom_dialog = (Button) findViewById(R.id.bt_bottom_dialog_control);
        bottom_sheet.setOnClickListener(listener);
        bottom_dialog.setOnClickListener(listener);
        //从布局中找到bottomSheetBehavior的实例
        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.sheet_layout));
        bottomSheetBehavior.setPeekHeight(0);
       // bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        //准备一个对话框
        createBottomSheetDialog();
    }

    private void createBottomSheetDialog() {
        dialog = new BottomSheetDialog(this);
        View dialogVIew= LayoutInflater.from(this).inflate(R.layout.dialog_bottom_sheet,null,false);
        //向dialog的容器中添加内容
        dialog.setContentView(dialogVIew);
        RecyclerView recyclerView= (RecyclerView) dialogVIew.findViewById(R.id.recycler_view);
        //设置一个布局管理
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<String> datas= new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            datas.add("条目"+i);
        }
       // dialogVIew.getParent();
        MyRecyclerView adapter=new MyRecyclerView(datas);
        recyclerView.setAdapter(adapter);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.bt_bottom_sheet_control:
                    //两种状态,展开和闭合
                    //如果状态是展开
                    if(bottomSheetBehavior.getState()==BottomSheetBehavior.STATE_EXPANDED){
                        System.out.println(""+bottomSheetBehavior.getState());
                        //点击后把状态置为折叠
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }else if(bottomSheetBehavior.getState()==BottomSheetBehavior.STATE_COLLAPSED){
                        System.out.println(""+bottomSheetBehavior.getState());
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    }
                    break;
                case R.id.bt_bottom_dialog_control:
                        //判断dialog是否显示,若显示则隐藏,否则显示
                    if(dialog.isShowing()){
                        dialog.dismiss();
                    }else {
                        dialog.show();
                    }
                    break;
            }

        }
    };
}
