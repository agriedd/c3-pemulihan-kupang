package com.c3pemulihankupang.c3pemulihankupang.ui.livestream;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.c3pemulihankupang.c3pemulihankupang.R;
import com.c3pemulihankupang.c3pemulihankupang.adapters.MenuHomeAdapter;
import com.c3pemulihankupang.c3pemulihankupang.databinding.LivestreamFragmentBinding;
import com.c3pemulihankupang.c3pemulihankupang.models.MenuItem;
import com.c3pemulihankupang.c3pemulihankupang.models.MenuItemIntent;
import com.c3pemulihankupang.c3pemulihankupang.models.MenuItemLink;
import com.c3pemulihankupang.c3pemulihankupang.models.MenuItemLinkIcon;

import java.util.ArrayList;
import java.util.List;

public class LivestreamFragment extends Fragment {

    private LivestreamViewModel mViewModel;
    private LivestreamFragmentBinding binding;
    private List<MenuItem> menuItemList = new ArrayList<>();
    private MenuHomeAdapter menuHomeAdapter;

    public static LivestreamFragment newInstance() {
        return new LivestreamFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root   = inflater.inflate(R.layout.livestream_fragment, container, false);
        binding     = LivestreamFragmentBinding.bind(root);
        initToolbar();
        initRecyclerMenu();
        return root;
    }

    private void initRecyclerMenu() {

        menuItemList.add(new MenuItemLink(1, "C3 Online", "C3 Online", R.drawable.banner, "https://c3pemulihan.online.church/"));
        menuItemList.add(new MenuItemLinkIcon(2, "Youtube", "Youtube C3 Online", R.drawable.livestreaming, "https://www.youtube.com/channel/UCc3VjGHxlzhe2vEgLYVB10g", R.drawable.yt_logo_rgb_dark));

        menuHomeAdapter = new MenuHomeAdapter(requireContext(), menuItemList, new MenuHomeAdapter.MenuItemClickListener() {
            @Override
            public void onClick(View v, MenuItem menuItem) {
                if(menuItem instanceof MenuItemLink){
                    MenuItemLink menuItemLink = (MenuItemLink) menuItem;
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(menuItemLink.getUrl()));
                    startActivity(intent);
                } else if(menuItem instanceof MenuItemIntent){
                    MenuItemIntent menuItemIntent = (MenuItemIntent) menuItem;
                    if(menuItemIntent.getIntent() != null)
                        startActivity(menuItemIntent.getIntent());
                }
            }
        });
        binding.menuList.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
        binding.menuList.setAdapter(menuHomeAdapter);
    }

    private void initToolbar() {
        AppCompatActivity appCompatActivity = (AppCompatActivity) requireActivity();
        appCompatActivity.setSupportActionBar(binding.toolbar);
        binding.toolbar.setNavigationOnClickListener(v -> {
            appCompatActivity.onBackPressed();
        });
    }
}