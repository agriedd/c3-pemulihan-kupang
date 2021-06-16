package com.c3pemulihankupang.c3pemulihankupang.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.c3pemulihankupang.c3pemulihankupang.R;
import com.c3pemulihankupang.c3pemulihankupang.databinding.ComponentMenuHomeBinding;
import com.c3pemulihankupang.c3pemulihankupang.databinding.ComponentMenuSmallBinding;
import com.c3pemulihankupang.c3pemulihankupang.models.MenuItem;
import com.c3pemulihankupang.c3pemulihankupang.models.MenuItemLink;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MenuNewAdapter extends RecyclerView.Adapter<MenuNewAdapter.viewHolder> {

    public interface MenuItemClickListener{
        void onClick(View v, MenuItem menuItem);
    }

    private Context context;
    private List<MenuItem> menuItemList;
    private MenuItemClickListener listener;

    public MenuNewAdapter(Context context, List<MenuItem> menuItemList, MenuItemClickListener listener) {
        this.context = context;
        this.menuItemList = menuItemList;
        this.listener = listener;
    }

    @NonNull
    @NotNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ComponentMenuSmallBinding view = ComponentMenuSmallBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolder holder, int position) {
        Log.d("wtf", "bind: ");
        holder.bind(context, menuItemList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return menuItemList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        public ComponentMenuSmallBinding binding;
        public viewHolder(@NonNull @NotNull ComponentMenuSmallBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            this.setIsRecyclable(false);
        }

        public void bind(Context context, MenuItem menuItem, MenuItemClickListener listener) {
            binding.title.setText(menuItem.getTitle());
            if(menuItem.getColor() != null){
                binding.floatingActionButton.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(context, menuItem.getColor())));
                binding.container.setCardBackgroundColor(ContextCompat.getColor(context, menuItem.getColor()));
            }
            if(menuItem instanceof MenuItemLink)
                binding.openLink.setVisibility(View.VISIBLE);
            binding.container.setOnClickListener(v -> {
                listener.onClick(v, menuItem);
            });
        }
    }

}
