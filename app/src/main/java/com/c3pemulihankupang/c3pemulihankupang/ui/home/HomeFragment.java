package com.c3pemulihankupang.c3pemulihankupang.ui.home;

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

import com.c3pemulihankupang.c3pemulihankupang.R;
import com.c3pemulihankupang.c3pemulihankupang.adapters.MenuHomeAdapter;
import com.c3pemulihankupang.c3pemulihankupang.databinding.FragmentHomeBinding;
import com.c3pemulihankupang.c3pemulihankupang.models.MenuItem;

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

        menuItemList.add(new MenuItem( 1, "Hello", "Hello World", "" ));
        menuItemList.add(new MenuItem( 1, "Hello", "Hello World", "" ));
        menuItemList.add(new MenuItem( 1, "Hello", "Hello World", "" ));
        menuItemList.add(new MenuItem( 1, "Hello", "Hello World", "" ));
        menuItemList.add(new MenuItem( 1, "Hello", "Hello World", "" ));
        menuItemList.add(new MenuItem( 1, "Hello", "Hello World", "" ));
        menuItemList.add(new MenuItem( 1, "Hello", "Hello World", "" ));

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

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}