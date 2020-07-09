package com.example.day74;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private Button mBtn;
    private ImageView mIv;
    private TextView mTv;
    private int count=4;
    private Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            String[] arr={"一","二","三","四","五"};
            mTv.setText(arr[count]);
            if (count==0){
                startActivity(new Intent(MainActivity.this,HomeActivity.class));
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initRec();
        mBtn.setOnClickListener(this);
    }

    private void initRec() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count>0){
                    try {
                        Thread.sleep(1000);
                        handler.sendEmptyMessage(1);
                        count--;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void initViews() {
        mBtn = findViewById(R.id.btn);
        mIv = findViewById(R.id.iv);
        mTv = findViewById(R.id.tv);
        anim();
    }

    private void anim() {
        AnimationSet set = new AnimationSet(this, null);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 200, 0, 200);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 720,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, 2, 1, 2,
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        set.addAnimation(alphaAnimation);
        set.addAnimation(translateAnimation);
        set.addAnimation(rotateAnimation);
        set.addAnimation(scaleAnimation);
        set.setDuration(5000);
        mIv.startAnimation(set);



    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this,HomeActivity.class));
    }
}
