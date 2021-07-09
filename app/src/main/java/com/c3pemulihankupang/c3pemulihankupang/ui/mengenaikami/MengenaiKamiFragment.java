package com.c3pemulihankupang.c3pemulihankupang.ui.mengenaikami;

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
import com.c3pemulihankupang.c3pemulihankupang.databinding.MengenaiKamiFragmentBinding;
import com.c3pemulihankupang.c3pemulihankupang.models.MenuItem;
import com.c3pemulihankupang.c3pemulihankupang.models.MenuItemLink;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MengenaiKamiFragment extends Fragment {

    private MengenaiKamiViewModel mViewModel;
    private MengenaiKamiFragmentBinding binding;
    private MenuHomeAdapter adapter;
    private List<MenuItem> menuItemList = new ArrayList<>();

    public static MengenaiKamiFragment newInstance() {
        return new MengenaiKamiFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view   = inflater.inflate(R.layout.mengenai_kami_fragment, container, false);
        binding     = MengenaiKamiFragmentBinding.bind(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initMengenaiMenu();
    }

    private void initMengenaiMenu() {
        initMenuList();

        adapter = new MenuHomeAdapter(requireContext(), menuItemList, (v, menuItem) -> {
            if(menuItem instanceof MenuItemLink){
                MenuItemLink menuItemLink = (MenuItemLink) menuItem;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(menuItemLink.getUrl()));
                startActivity(intent);
            }
        });

        binding.daftarMengenaiKami.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
        binding.daftarMengenaiKami.setAdapter(adapter);
    }

    private void initMenuList() {
        menuItemList.add(
                new MenuItemLink(1, "Siapa Kami", "", R.drawable.livestreaming, "https://c3pemulihankupang.com/sk.php")
        );
        menuItemList.add(
                new MenuItemLink(2, "Gembala Kami", "", R.drawable.banner, "https://c3pemulihankupang.com/sk.php")
        );
        menuItemList.add(
                new MenuItemLink(3, "C3 Ryde", "", R.drawable.siapa_kami, "https://c3pemulihankupang.com/sk.php")
        );
        menuItemList.add(
                new MenuItemLink(4, "C3 Global", "", R.drawable.team, "https://c3pemulihankupang.com/sk.php")
        );
    }

    private void initToolbar() {
        AppCompatActivity appCompatActivity = (AppCompatActivity) requireActivity();
        appCompatActivity.setSupportActionBar(binding.toolbar);
        binding.toolbar.setNavigationOnClickListener(v -> {
            appCompatActivity.onBackPressed();
        });
    }
}