package com.example.jam3naapp2021.ui.group;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.jam3naapp2021.MainActivity;
import com.example.jam3naapp2021.R;
import com.example.jam3naapp2021.ui.gallery.GalleryFragment;

public class GroupFragment extends Fragment {

    private GroupViewModel groupViewModel;
ImageButton gogroup;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_group, container, false);
       gogroup=root.findViewById(R.id.Groupgo);

       gogroup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(getActivity(), GroupModule.class));
           }
       });
        return root;
    }

}