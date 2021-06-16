package com.c3pemulihankupang.c3pemulihankupang.ui.timkami;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.c3pemulihankupang.c3pemulihankupang.R;
import com.c3pemulihankupang.c3pemulihankupang.databinding.TimKamiFragmentBinding;
import com.c3pemulihankupang.c3pemulihankupang.webview.WebviewC3;

public class TimKamiFragment extends Fragment {

    private TimKamiViewModel mViewModel;
    private TimKamiFragmentBinding binding;

    public static interface LoadingListener{
        void onStart();
        void onComplete();
    }

    public static TimKamiFragment newInstance() {
        return new TimKamiFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tim_kami_fragment, container, false);
        binding = TimKamiFragmentBinding.bind(root);
        initToolbar();
        initWebview();
        return root;
    }

    private void initWebview() {
        binding.webview.setWebViewClient(new WebviewC3(new LoadingListener() {
            @Override
            public void onStart() {
                binding.loading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onComplete() {
                binding.loading.setVisibility(View.GONE);
            }
        }));
        binding.webview.getSettings().setDomStorageEnabled(true);
        binding.webview.getSettings().setJavaScriptEnabled(true);
        binding.webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        binding.webview.loadUrl("https://c3pemulihankupang.com/tim.php");
    }

    private void initToolbar() {
        AppCompatActivity activity = (AppCompatActivity) requireActivity();
        activity.setSupportActionBar(binding.toolbar);
        binding.toolbar.setNavigationOnClickListener(v -> activity.onBackPressed());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TimKamiViewModel.class);
    }
}