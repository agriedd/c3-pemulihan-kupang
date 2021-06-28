package com.c3pemulihankupang.c3pemulihankupang.webview;

import android.content.Context;
import android.widget.Toast;

import com.c3pemulihankupang.c3pemulihankupang.MainActivity;

public class JavascriptInterface {
    Context mContext;

    /** Instantiate the interface and set the context */
    public JavascriptInterface(Context c) {
        mContext = c;
    }

    /** Show a toast from the web page */
    @android.webkit.JavascriptInterface
    public void playSpotify(String id) {
        MainActivity activity = (MainActivity) mContext;
        if(activity != null){
            activity.play(id);
        }
    }
}
