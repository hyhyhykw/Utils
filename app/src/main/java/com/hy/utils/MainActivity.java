package com.hy.utils;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Logger.e("测试一下看看");
    }

    public void click(View view) {
        if (MyViewUtils.isFastDoubleClick())return;
        Logger.e("测试一下啊啊啊啊啊");
    }
}
