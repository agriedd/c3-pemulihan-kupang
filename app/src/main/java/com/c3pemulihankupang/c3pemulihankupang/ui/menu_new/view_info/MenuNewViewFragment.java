package com.c3pemulihankupang.c3pemulihankupang.ui.menu_new.view_info;

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
import com.c3pemulihankupang.c3pemulihankupang.databinding.MenuNewViewFragmentBinding;
import com.c3pemulihankupang.c3pemulihankupang.ui.timkami.TimKamiFragment;
import com.c3pemulihankupang.c3pemulihankupang.webview.WebviewC3;

public class MenuNewViewFragment extends Fragment {

    private MenuNewViewViewModel mViewModel;
    private MenuNewViewFragmentBinding binding;
    private String url;

    public MenuNewViewFragment(String url) {
        this.url = url;
    }

    public static MenuNewViewFragment newInstance(String url) {
        return new MenuNewViewFragment(url);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(requireActivity()).get(MenuNewViewViewModel.class);
        View root = inflater.inflate(R.layout.menu_new_view_fragment, container, false);
        binding = MenuNewViewFragmentBinding.bind(root);
        initToolbar();
        initWebView();
        return root;
    }

    private void initToolbar() {
        AppCompatActivity activity = (AppCompatActivity) requireActivity();
        activity.setSupportActionBar(binding.toolbar);
        binding.toolbar.setNavigationOnClickListener(v -> {
            activity.onBackPressed();
        });
    }

    private void initWebView() {
        binding.webview.setWebViewClient(new WebviewC3(new TimKamiFragment.LoadingListener() {
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
        binding.webview.loadUrl(this.url);
    }

}