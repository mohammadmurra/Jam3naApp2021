package com.example.jam3naapp2021.ui.gallery;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.example.jam3naapp2021.CreateGroupController;
import com.example.jam3naapp2021.LoginActivity;
import com.example.jam3naapp2021.MainActivity;
import com.example.jam3naapp2021.R;
//import com.example.jam3naapp2021.profileController;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.concurrent.Executor;

public class GalleryFragment extends Fragment {
    TextView  gender;
    TextView profileEmail,profilePhone,profileName , UserAge , UserHeight , UserWeight;
    ImageView profileImageView;
    String x;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;
    String visitorID ;
    StorageReference storageReference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


//
//
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        profileName = root.findViewById(R.id.ProfileNameText);
        profileEmail = root.findViewById(R.id.profileEmil);
        profilePhone = root.findViewById(R.id.profilePhone);
        profileImageView = root.findViewById(R.id.profileImage);
        // saveBtn = findViewById(R.id.saveProfileInfo);
        gender=root.findViewById(R.id.profileGender);
        UserAge = root.findViewById(R.id.birthDate);
        UserHeight= root.findViewById(R.id.profileLength);
        UserWeight = root.findViewById(R.id.profileWidgh);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        visitorID = fAuth.getCurrentUser().getUid();


        DocumentReference documentReference = fStore.collection("Visitor").document(fAuth.getCurrentUser().getUid());
        Log.d("UID",visitorID);
        documentReference.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                x = value.getString("fname") +""+ value.getString("lname");
                profileName.setText(x);

                profileEmail.setText(value.getString("Uemail"));
                profilePhone.setText(value.getString("Uphone"));
                gender.setText(value.getString("Ugender"));
                UserAge.setText((CharSequence) value.getDate("dateOfBirth"));
                UserHeight.setText(value.getString("Uheigt"));
                UserWeight.setText(value.getString("uweight"));


                //to store this data after user edit it

            }
        });


        GoogleSignInAccount SignInAccount = GoogleSignIn.getLastSignedInAccount(getActivity());
        if(SignInAccount != null){
            x = SignInAccount.getDisplayName() +""+SignInAccount.getFamilyName();
            profileName.setText(SignInAccount.getFamilyName());
            profileEmail.setText(SignInAccount.getEmail());

        }
//
//        fAuth = FirebaseAuth.getInstance();
//        profileName.setText(""+fAuth.getCurrentUser().getDisplayName());


        return root;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.a:
//                startActivity(new Intent(getApplicationContext(), profileController.class));
//                return true;
            case R.id.action_settings:
                Toast.makeText(getActivity(), " settings not ready yet !", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_logout:
                fAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), LoginActivity.class));
                return true;
            case R.id.action_create:
                startActivity(new Intent(getContext(), CreateGroupController.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

}