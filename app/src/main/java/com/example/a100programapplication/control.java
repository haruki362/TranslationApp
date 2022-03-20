package com.example.a100programapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class control extends FragmentStateAdapter {
    public control (FragmentActivity fragment) {
        super(fragment);
    }
    @NonNull
    @Override
    public Fragment createFragment (int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new TranslationFragment();
        }else if(position == 1) {
            fragment = new SavedFragment();
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
