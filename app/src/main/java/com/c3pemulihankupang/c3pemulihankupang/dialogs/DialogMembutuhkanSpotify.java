package com.c3pemulihankupang.c3pemulihankupang.dialogs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.c3pemulihankupang.c3pemulihankupang.R;
import com.c3pemulihankupang.c3pemulihankupang.databinding.ComponentPemberitahuanSpotifyBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.jetbrains.annotations.NotNull;

public class DialogMembutuhkanSpotify extends BottomSheetDialogFragment {

    private ComponentPemberitahuanSpotifyBinding binding;

    public DialogMembutuhkanSpotify() {

    }

    public static DialogMembutuhkanSpotify  getInstance(){
        return new DialogMembutuhkanSpotify();
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.component_pemberitahuan_spotify, container, false);
        binding = ComponentPemberitahuanSpotifyBinding.bind(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.tutupDialog.setOnClickListener(v -> {
            this.dismiss();
        });
        binding.installSpotify.setOnClickListener(v -> {
            final String appPackageName = "com.spotify.music";
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }
        });
    }
}
