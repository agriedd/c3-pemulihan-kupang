package com.c3pemulihankupang.c3pemulihankupang.ui.menu_new;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.c3pemulihankupang.c3pemulihankupang.MainActivity;
import com.c3pemulihankupang.c3pemulihankupang.MenuNewView;
import com.c3pemulihankupang.c3pemulihankupang.R;
import com.c3pemulihankupang.c3pemulihankupang.adapters.MenuHomeAdapter;
import com.c3pemulihankupang.c3pemulihankupang.adapters.MenuNewAdapter;
import com.c3pemulihankupang.c3pemulihankupang.databinding.MenuNewFragmentBinding;
import com.c3pemulihankupang.c3pemulihankupang.models.MenuItem;
import com.c3pemulihankupang.c3pemulihankupang.models.MenuItemIntent;

import java.util.ArrayList;
import java.util.List;

public class MenuNewFragment extends Fragment {

    private MenuNewViewModel mViewModel;
    private MenuNewFragmentBinding binding;
    private List<MenuItem> menuItemList = new ArrayList<>();
    private MenuNewAdapter adapter;

    public static MenuNewFragment newInstance() {
        return new MenuNewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.menu_new_fragment, container, false);
        mViewModel = new ViewModelProvider(requireActivity()).get(MenuNewViewModel.class);
        binding = MenuNewFragmentBinding.bind(root);

        initMenu();

        return root;
    }

    private void initMenu() {
        MenuItem menu_doa = new MenuItemIntent(1, "Butuh Doa?","Tim kami akan senang mendoakan Anda dan mendukung Anda dengan segala permintaan doa yang Anda miliki. Selain itu, kami ingin merayakan bersama Anda & bersyukur kepada Tuhan atas jawaban doa yang Anda miliki!", R.drawable.team, new Intent(requireActivity(), MainActivity.class))
                .setColor(R.color.light_blue_A400)
                .setStyle(R.style.Theme_C3PemulihanKupang_CardAmber);
        menuItemList.add(menu_doa);
        MenuItem menu_vip_card = new MenuItemIntent(2, "New / VIP Card?", "VIP Card", R.drawable.team, new Intent(requireActivity(), MainActivity.class))
                .setColor(R.color.pink_500)
                .setStyle(R.style.Theme_C3PemulihanKupang_CardAmber);
        menuItemList.add(menu_vip_card);
        MenuItem menu_komitmen = new MenuItemIntent(3, "Komitmen", "Komitmen", R.drawable.team, new Intent(requireActivity(), MainActivity.class))
                .setColor(R.color.amber_700)
                .setStyle(R.style.Theme_C3PemulihanKupang_CardAmber);
        menuItemList.add(menu_komitmen);

        adapter = new MenuNewAdapter(requireContext(), menuItemList, new MenuNewAdapter.MenuItemClickListener() {
            @Override
            public void onClick(View v, MenuItem menuItem) {
                Intent intent = new Intent(requireContext(), MenuNewView.class);
                if(menuItem.getId() == 1){
                    intent.putExtra("url", "https://c3pemulihankupang.com/doa.php");
                    startActivity(intent);
                } else if(menuItem.getId() == 2){
                    intent.putExtra("url", "https://c3pemulihankupang.com/hallo.php");
                    startActivity(intent);
                } else if(menuItem.getId() == 3){
                    intent.putExtra("url", "https://c3pemulihankupang.com/bergabung.php");
                    startActivity(intent);
                }
            }
        });
        binding.daftarMenuNew.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
        binding.daftarMenuNew.setAdapter(adapter);

    }

}