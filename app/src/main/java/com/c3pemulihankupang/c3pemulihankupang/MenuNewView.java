package com.c3pemulihankupang.c3pemulihankupang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.c3pemulihankupang.c3pemulihankupang.databinding.MenuNewViewActivityBinding;
import com.c3pemulihankupang.c3pemulihankupang.ui.menu_new.view_info.MenuNewViewFragment;

public class MenuNewView extends AppCompatActivity {

    private MenuNewViewActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MenuNewViewActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (savedInstanceState == null) {
            String url = getIntent().getExtras().getString("url");
            if(url != null)
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, MenuNewViewFragment.newInstance( url ))
                        .commitNow();
            else{
                //do nothing
            }
        }
    }
}