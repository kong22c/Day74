package com.example.day74;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.day74.adapter.VpAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private ViewPager mVp;
    private TabLayout mTl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
    }
    private void initViews() {
        mVp = findViewById(R.id.vp);
        mTl = findViewById(R.id.tl);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new CollFragment());
        ArrayList<String> list = new ArrayList<>();
        list.add("首页");
        list.add("收藏");
        mTl.setupWithViewPager(mVp);
        VpAdapter adapter = new VpAdapter(getSupportFragmentManager(), fragments, list);
        mVp.setAdapter(adapter);

    }
}
