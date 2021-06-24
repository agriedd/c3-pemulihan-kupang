package com.c3pemulihankupang.c3pemulihankupang.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.c3pemulihankupang.c3pemulihankupang.LivestreamActivity;
import com.c3pemulihankupang.c3pemulihankupang.MainActivity;
import com.c3pemulihankupang.c3pemulihankupang.R;
import com.c3pemulihankupang.c3pemulihankupang.TimKamiActivity;
import com.c3pemulihankupang.c3pemulihankupang.adapters.MenuHomeAdapter;
import com.c3pemulihankupang.c3pemulihankupang.databinding.FragmentHomeBinding;
import com.c3pemulihankupang.c3pemulihankupang.models.MenuItem;
import com.c3pemulihankupang.c3pemulihankupang.models.MenuItemIntent;
import com.c3pemulihankupang.c3pemulihankupang.models.MenuItemLink;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private List<MenuItem> menuItemList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        bindRecyclerMenu();

        View root = binding.getRoot();
        return root;
    }

    private void bindRecyclerMenu() {

        menuItemList.add(new MenuItemLink( 1, "Banner", "Informasi", R.drawable.banner , "http://c3pemulihankupang.com"));
        menuItemList.add(new MenuItemIntent( 2, "Tim Kami", "Informasi Tim Kami", R.drawable.team, new Intent(requireContext(), TimKamiActivity.class)));
        menuItemList.add(new MenuItemIntent( 3, "Live Streaming", "Informasi Live Streaming", R.drawable.livestreaming, new Intent(requireContext(), LivestreamActivity.class) ));
        menuItemList.add(new MenuItemIntent( 4, "Mengenai Kami", "Siapa Kami & Apa Yang Kami Lakukan", R.drawable.siapa_kami, null));

        binding.menuView.setLayoutManager(
                new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        );
        binding.menuView.setAdapter(
                new MenuHomeAdapter(
                        requireContext(),
                        menuItemList,
                        this::onMenuClick
                )
        );
    }

    private void onMenuClick(View view, MenuItem menuItem) {
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}