package com.c3pemulihankupang.c3pemulihankupang.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.c3pemulihankupang.c3pemulihankupang.R;
import com.c3pemulihankupang.c3pemulihankupang.databinding.ComponentMenuHomeBinding;
import com.c3pemulihankupang.c3pemulihankupang.models.MenuItem;
import com.c3pemulihankupang.c3pemulihankupang.models.MenuItemLink;

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
        ComponentMenuHomeBinding view = ComponentMenuHomeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolder holder, int position) {
        holder.bind(context, menuItemList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return menuItemList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        public ComponentMenuHomeBinding binding;
        public viewHolder(@NonNull @NotNull ComponentMenuHomeBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void bind(Context context, MenuItem menuItem, MenuItemClickListener listener) {
            binding.title.setText(menuItem.getTitle());
            binding.subtitle.setText(menuItem.getSubtitle());
            if(menuItem.getDrawable_image() != null)
                binding.image.setImageResource(menuItem.getDrawable_image());
            if(menuItem instanceof MenuItemLink)
                binding.openLink.setVisibility(View.VISIBLE);
            binding.container.setOnClickListener(v -> {
                listener.onClick(v, menuItem);
            });
        }
    }

}
