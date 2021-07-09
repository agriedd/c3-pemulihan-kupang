package com.c3pemulihankupang.c3pemulihankupang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.c3pemulihankupang.c3pemulihankupang.databinding.MengenaiKamiActivityBinding;
import com.c3pemulihankupang.c3pemulihankupang.ui.mengenaikami.MengenaiKamiFragment;

public class MengenaiKamiActivity extends AppCompatActivity {

    private MengenaiKamiActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = MengenaiKamiActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MengenaiKamiFragment.newInstance())
                    .commitNow();
        }
    }
}