package com.myemcu.app_6handler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 抽取出的对象。
    private TextView mTxt;
    private Handler  mMyHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTxt = (TextView) findViewById(R.id.txt);

        // 创建Habdler对象
        mMyHandler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                if (msg.what==0x12) {
                    mTxt.setText("生成的随机数为：\n"+Math.random());
                }
            }
        };

        // 新启动一个线程(即:子线程)，打算实现动态更新主线程MainActivity中的随机数。
        new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                    while (true) {
                        Thread.sleep(500);              // 线程延时0.5s
                        Message msg = new Message();    // 建立消息
                        msg.what=0x12;                  // 标记消息
                        mMyHandler.sendMessage(msg);    // 发送消息
                    }
               }
               catch (Exception e) {
                   e.printStackTrace();
               }
           }
       }).start();

    }

}
