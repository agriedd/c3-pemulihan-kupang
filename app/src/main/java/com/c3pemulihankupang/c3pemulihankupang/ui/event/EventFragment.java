package com.c3pemulihankupang.c3pemulihankupang.ui.event;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.c3pemulihankupang.c3pemulihankupang.R;
import com.c3pemulihankupang.c3pemulihankupang.databinding.EventFragmentBinding;
import com.c3pemulihankupang.c3pemulihankupang.ui.podcast.PodcastViewModel;
import com.c3pemulihankupang.c3pemulihankupang.ui.timkami.TimKamiFragment;
import com.c3pemulihankupang.c3pemulihankupang.webview.JavascriptInterface;
import com.c3pemulihankupang.c3pemulihankupang.webview.WebviewC3;

import org.jetbrains.annotations.NotNull;

public class EventFragment extends Fragment {

    private EventViewModel mViewModel;
    private EventFragmentBinding binding;

    public static EventFragment newInstance() {
        return new EventFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(requireActivity()).get(EventViewModel.class);
        View view = inflater.inflate(R.layout.event_fragment, container, false);
        binding = EventFragmentBinding.bind(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initWebView();
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
        binding.webview.addJavascriptInterface(new JavascriptInterface(requireContext()), "Android");
        binding.webview.loadUrl("https://c3pemulihankupang.com/kegiatan.php");
    }

}