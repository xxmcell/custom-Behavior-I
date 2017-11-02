package com.it.bottomsheetbehaviortest;

import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	private BottomSheetBehavior<View> bottomSheetBehavior;
	private BottomSheetDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button bt_bottom_sheet_control = (Button) findViewById(R.id.bt_bottom_sheet_control);
		Button bt_bottom_dialog_control= (Button) findViewById(R.id.bt_bottom_dialog_control);
		bt_bottom_sheet_control.setOnClickListener(listener);
		bt_bottom_dialog_control.setOnClickListener(listener);
		//从布局中找到BottomSheetBehavior的实例
		bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.sheet_layout));
		bottomSheetBehavior.setPeekHeight(0);
		//准备一个对话框
		createBottomSheetDialog();
	}
	private List<String> datas;
	private void createBottomSheetDialog() {
		dialog = new BottomSheetDialog(this);
		View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_bottom_sheet, null, false);
		dialog.setContentView(dialogView);
		RecyclerView recyclerView= (RecyclerView) dialogView.findViewById(R.id.recycler_view);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		//准备数据
		datas=new ArrayList<String>();
		for (int i = 0; i < 30; i++) {
			datas.add("条目"+i);
		}
		MyAdapter adapter=new MyAdapter(datas);
		recyclerView.setAdapter(adapter);
	}

	private View.OnClickListener listener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.bt_bottom_sheet_control:
					//判断状态
					//两种状态:
					//1,扩展
					//2,闭合
					if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {

						bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
					} else if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {

						bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
					}
					break;
				case R.id.bt_bottom_dialog_control:
					//判断dialog是否显示,若显示则隐藏,否则显示
					if(dialog.isShowing()){
						dialog.dismiss();
					}else{
						dialog.show();
					}
					break;
			}
		}
	};
}
