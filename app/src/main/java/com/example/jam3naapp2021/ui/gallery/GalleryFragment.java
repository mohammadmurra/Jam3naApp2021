package com.example.jam3naapp2021.ui.gallery;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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

public class GalleryFragment extends Fragment {
    RadioGroup radioGroup ;
    RadioButton gender;
    EditText profileFirstName,profileEmail,profilePhone,profileLastName , UserAge , UserHeight , UserWeight;
    ImageView profileImageView;
    Button saveBtn;
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
//        profileFirstName = root.findViewById(R.id.profileFirstUsername);
//        profileLastName = root.findViewById(R.id.profileLastUsername);
//        profileEmail = root.findViewById(R.id.profileEmail);
//        profilePhone = root.findViewById(R.id.profilePhoneNumber);
//        profileImageView = root.findViewById(R.id.profileImageView);
//        saveBtn = root.findViewById(R.id.saveProfileInfo);
//        radioGroup=root.findViewById(R.id.gender);
//        UserAge = root.findViewById(R.id.UserAge);
//        UserHeight= root.findViewById(R.id.UserHeight);
//        UserWeight = root.findViewById(R.id.UserWeight);


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        visitorID = fAuth.getCurrentUser().getUid();
//        StorageReference mImageStorage = FirebaseStorage.getInstance().getReference();
//        Log.d("referance",mImageStorage.getBucket());
//        StorageReference ref = mImageStorage.child("gs://jam3naapp2021.appspot.com")
//                .child("/Users/UsersProfilePictures/test@test.com1617502861919.png");
//        ref.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
//            @Override
//            public void onComplete(@NonNull Task<Uri> task) {
//                if (task.isSuccessful()) {
//                    Uri downUri = task.getResult();
//                    String imageUrl = downUri.toString();
//
//
//                    profileImageView.setImageURI(downUri);
//
//                }else{
//
//                }
//            }
//        });

//        DocumentReference documentReference = fStore.collection("Visitor").document(visitorID);
//        documentReference.addSnapshotListener( new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//
//                profileFirstName.setText(value.getString("fname"));
//                profileLastName.setText(value.getString("lname"));
//                profileEmail.setText(value.getString("Uemail"));
//                profilePhone.setText(value.getString("Uphone"));
//
//                //to store this data after user edit it
//
//            }
//        });


        return root;
    }


}