package com.example.jam3naapp2021.ui.group;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.Menu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.jam3naapp2021.CreateGroupController;
import com.example.jam3naapp2021.LoginActivity;
import com.example.jam3naapp2021.MainActivity;
import com.example.jam3naapp2021.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.example.jam3naapp2021.MainActivity;
import com.example.jam3naapp2021.R;
import com.example.jam3naapp2021.ui.gallery.GalleryFragment;

public class GroupFragment extends Fragment {

    private GroupViewModel groupViewModel;
 Menu menu;
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


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item=menu.findItem(R.id.action_create);
        if(item!=null)
            item.setVisible(true);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startActivity(new Intent(getActivity(), CreateGroupController.class));

                return true;
            }
        });



}}