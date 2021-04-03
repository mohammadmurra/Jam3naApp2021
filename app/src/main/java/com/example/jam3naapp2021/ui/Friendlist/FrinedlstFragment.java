package com.example.jam3naapp2021.ui.Friendlist;

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

import com.example.jam3naapp2021.R;

public class FrinedlstFragment extends Fragment {

    private FriendlistViewModel friendlistViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        friendlistViewModel =
                new ViewModelProvider(this).get(FriendlistViewModel.class);
        View root = inflater.inflate(R.layout.fragment_friendlist, container, false);
        final TextView textView = root.findViewById(R.id.text_friendlist);
        friendlistViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}