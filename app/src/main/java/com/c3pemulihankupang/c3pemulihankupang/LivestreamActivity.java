package com.c3pemulihankupang.c3pemulihankupang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.c3pemulihankupang.c3pemulihankupang.ui.livestream.LivestreamFragment;

public class LivestreamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.livestream_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, LivestreamFragment.newInstance())
                    .commitNow();
        }
    }
}