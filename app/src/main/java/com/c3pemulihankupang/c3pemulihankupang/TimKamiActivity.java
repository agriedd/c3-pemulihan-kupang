package com.c3pemulihankupang.c3pemulihankupang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.c3pemulihankupang.c3pemulihankupang.ui.timkami.TimKamiFragment;

public class TimKamiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tim_kami_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, TimKamiFragment.newInstance())
                    .commitNow();
        }
    }

//    @Override
//    public void onBackPressed() {
//        switch (keyCode) {
//            case KeyEvent.KEYCODE_BACK:
//                if (mWebView.canGoBack()) {
//                    mWebView.goBack();
//                } else {
//                    finish();
//                }
//                return true;
//        }
//    }
}