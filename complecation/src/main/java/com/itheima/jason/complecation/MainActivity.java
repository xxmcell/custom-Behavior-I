package com.itheima.jason.complecation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView= LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,null,false);
                MyViewHolder viewHolder=new MyViewHolder(itemView);
                return viewHolder;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                    MyViewHolder mvh= (MyViewHolder) holder;
                mvh.tv.setText("条目"+position);
            }

            @Override
            public int getItemCount() {
                return 30;
            }
            class MyViewHolder extends RecyclerView.ViewHolder{
                private  TextView tv;
                public MyViewHolder(View itemView) {
                    super(itemView);
                tv= itemView.findViewById(android.R.id.text1);

                }
            }
        });
    }
}
