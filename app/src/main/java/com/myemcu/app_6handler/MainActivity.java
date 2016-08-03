package com.myemcu.app_6handler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTxt; // 抽取出的对象。

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTxt = (TextView) findViewById(R.id.txt);
        mTxt.setText("生成的随机数为：\n"+Math.random());

        // 新启动一个线程，打算实现动态更新主线程MainActivity中的随机数。
        new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                    while (true) {
                        Thread.sleep(300);              // 程序休眠0.3s
                        Double random = Math.random();  // 生成随机数
                        mTxt.setText("生成的随机数为："+random);
                    }
               }
               catch (Exception e) {
                   e.printStackTrace();
               }
           }
       }).start();

        //-主线程自己刷新随机数---------------------------------------------------------------------------
        //仅控制台能刷新而界面文本则不能
        try {
                for (int i=0;i<5;i++) {
                    Thread.sleep(300); // 线程休眠0.3s
                    Double random = Math.random();
                    System.out.println(random); // 生成控制台打印
                    mTxt.setText("生成的随机数为："+random);
                }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}
