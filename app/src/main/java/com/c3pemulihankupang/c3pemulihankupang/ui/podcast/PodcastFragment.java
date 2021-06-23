package com.c3pemulihankupang.c3pemulihankupang.ui.podcast;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.c3pemulihankupang.c3pemulihankupang.R;
import com.c3pemulihankupang.c3pemulihankupang.databinding.PodcastFragmentBinding;
import com.c3pemulihankupang.c3pemulihankupang.ui.timkami.TimKamiFragment;
import com.c3pemulihankupang.c3pemulihankupang.webview.WebviewC3;

public class PodcastFragment extends Fragment {

    private PodcastViewModel mViewModel;
    private PodcastFragmentBinding binding;

    public static PodcastFragment newInstance() {
        return new PodcastFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.podcast_fragment, container, false);
        mViewModel = new ViewModelProvider(requireActivity()).get(PodcastViewModel.class);
        binding = PodcastFragmentBinding.bind(root);
        initWebView();
        return root;
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
        binding.webview.loadUrl("https://c3pemulihankupang.com/podcast.php");
    }

}