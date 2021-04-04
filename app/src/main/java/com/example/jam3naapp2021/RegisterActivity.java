package com.example.jam3naapp2021;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.jam3naapp2021.ui.gallery.GalleryFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText FirstName,mEmail,mPassword,mPhone,LastName,Password;
    Button mRegisterBtn,insProfileImage;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    TextView LoginUser ;
    String VisitorID ;
    ImageView Profile_pic;
    int SELECT_PICTURE = 200;
    Uri selectedImageUri;
    String imageId;
    StorageReference mStorgeRef;
    String ImageId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mStorgeRef= FirebaseStorage.getInstance().getReference("Users/UsersProfilePictures");
        FirstName   = findViewById(R.id.inputFirstUsername);
        LastName   = findViewById(R.id.inputLastUsername);
        mEmail      = findViewById(R.id.inputEmail);
        mPassword   = findViewById(R.id.inputPassword);
        Password=findViewById(R.id.inputConformPassword);
        mRegisterBtn= findViewById(R.id.btnRegister);
        LoginUser=findViewById(R.id.alreadyHaveAccount);
        mPhone=findViewById(R.id.inputPhoneNumber);
        Profile_pic=findViewById(R.id.ProfileImage);
        insProfileImage=findViewById(R.id.ins_pic);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);


        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), GalleryFragment.class));
            finish();
        }
        insProfileImage.setOnClickListener(e->{
            imageChooser();
        });

        mRegisterBtn.setOnClickListener(v -> {
            String email = mEmail.getText().toString().trim();
            String password = mPassword.getText().toString().trim();
            String UserPassword = Password.getText().toString().trim();
            String UserfirstName = FirstName.getText().toString();
            String UserLastName = LastName.getText().toString();
            String phoneNumber = mPhone.getText().toString();


            if (TextUtils.isEmpty(email)) {
                mEmail.setError("Email is Required.");
                return;
            }
            if (TextUtils.isEmpty(UserfirstName)) {
                FirstName.setError("First Name is Required.");
                return;
            }
            if (TextUtils.isEmpty(UserLastName)) {
                LastName.setError("Last Name is Required.");
                return;
            }
            if (TextUtils.isEmpty(password)) {
                mPassword.setError("Password is Required.");
                return;
            }
            if (TextUtils.isEmpty(phoneNumber)) {
                mPassword.setError("Phone Number is Required.");
                return;
            }
            if (password.length() < 6) {
                mPassword.setError("Password Must be more than 6 Characters");
                return;
            }

            fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "User Created.", Toast.LENGTH_SHORT).show();

                        VisitorID = fAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = fStore.collection("Visitor").document(VisitorID);
                        Map<String, Object> visitor = new HashMap<>();
                        visitor.put("fname", UserfirstName);
                        visitor.put("lname", UserLastName);
                        visitor.put("Uphone", phoneNumber);
                        visitor.put("Uemail", email);
                        documentReference.set(visitor).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "onSuccess: user Profile is created for " + VisitorID);
                            }
                        });
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                    else{
                        Toast.makeText(RegisterActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);

                    }
                }
            });
            upload_Profile_Pic();
        });

        LoginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));

            }
        });

    }
    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    Profile_pic.setImageURI(selectedImageUri);
                }
            }
        }
    }
    private String getExtension(Uri uri) {
        ContentResolver cr=getContentResolver();
        MimeTypeMap mimetypemap=MimeTypeMap.getSingleton();
        return mimetypemap.getExtensionFromMimeType(cr.getType(uri));

    }

    private void upload_Profile_Pic() {
        ImageId=""+mEmail.getText();
        StorageReference Ref = mStorgeRef.child(ImageId+ "." + getExtension(selectedImageUri));
        Ref.putFile(selectedImageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(RegisterActivity.this, "Image Uploaded successfully.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, "Image Uploaded failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }


}