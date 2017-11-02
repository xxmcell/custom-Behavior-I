package com.it.jason.snackbar;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tv);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //三个参数,对象,文本,时长
                /**
                 * snackbar在最后,一定要使用show();
                 */
                Snackbar.make(textView,"逗你玩",Snackbar.LENGTH_LONG ).setAction("不要搞笑", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"都跟你说了,别闹",Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });
    }
}
