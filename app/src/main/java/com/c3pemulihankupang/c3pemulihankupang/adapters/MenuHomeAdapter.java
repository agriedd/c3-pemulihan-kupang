package com.c3pemulihankupang.c3pemulihankupang.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.c3pemulihankupang.c3pemulihankupang.R;
import com.c3pemulihankupang.c3pemulihankupang.models.MenuItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MenuHomeAdapter extends RecyclerView.Adapter<MenuHomeAdapter.viewHolder> {

    public interface MenuItemClickListener{
        void onClick(View v, MenuItem menuItem);
    }

    private Context context;
    private List<MenuItem> menuItemList;
    private MenuItemClickListener listener;

    public MenuHomeAdapter(Context context, List<MenuItem> menuItemList, MenuItemClickListener listener) {
        this.context = context;
        this.menuItemList = menuItemList;
        this.listener = listener;
    }

    @NonNull
    @NotNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.component_menu_home, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return menuItemList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        public viewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }

}
