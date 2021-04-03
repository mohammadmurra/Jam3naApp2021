package com.example.jam3naapp2021;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class CreateGroupController extends AppCompatActivity {


    FirebaseAuth fAuth;
    ProgressBar progressBar;
    Button InsBtn, createBtn;
    EditText GroupName, GroupDesc;
    Spinner GroupCate;
    TextView admin_Id;
    int SELECT_PICTURE = 200;
    ImageView GroupPic;
    StorageReference mStorgeRef;
    Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_group);
        fAuth = FirebaseAuth.getInstance();
        mStorgeRef= FirebaseStorage.getInstance().getReference("Images");
        progressBar = findViewById(R.id.progressBar2);
        InsBtn = findViewById(R.id.ins_pic);
        createBtn = findViewById(R.id.createBtn);
        GroupDesc = findViewById(R.id.groupDescText);
        GroupName = findViewById(R.id.groupNameText);
        admin_Id = findViewById(R.id.adminId);
        GroupCate = findViewById(R.id.groupCategroy);
        GroupPic=findViewById(R.id.imageView);
      //  admin_Id.setText("mohammad");


        createBtn.setOnClickListener(e -> {
            create_group_method();
           // upload_group_image();
        });
        InsBtn.setOnClickListener(e->{
            imageChooser();
        });


    }
    private String getExtension(Uri uri) {
        ContentResolver cr=getContentResolver();
        MimeTypeMap mimetypemap= MimeTypeMap.getSingleton();
        return mimetypemap.getExtensionFromMimeType(cr.getType(uri));

    }

    private void upload_group_image() {
            StorageReference Ref = mStorgeRef.child(System.currentTimeMillis() + "." + getExtension(selectedImageUri));
            Ref.putFile(selectedImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(com.example.jam3naapp2021.CreateGroupController.this, "Image Uploaded successfully.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(com.example.jam3naapp2021.CreateGroupController.this, "Image Uploaded failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void create_group_method() {
        String TAG = null;
        String Picurl;
        String Group_desc = GroupDesc.getText().toString();
        String Group_name = GroupName.getText().toString();
        String Group_adminid = admin_Id.getText().toString();
        String GroupCategory = GroupCate.getSelectedItem().toString();
         Picurl= DocumentSnapshot.class.getCanonicalName();
        Log.d("ID pic",Picurl);
        Picurl= DocumentSnapshot.class.getSimpleName();
        Log.d("ID pic1",Picurl);
        Picurl= DocumentSnapshot.class.getTypeName();
        Log.d("ID pic2",Picurl);
        Picurl= DocumentSnapshot.class.toGenericString();
        Log.d("ID pic3",Picurl);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> group = new HashMap<>();
        group.put("Group Name", Group_name);
        group.put("Group Desciption", Group_desc);
        group.put("Admin ID","mohammad");
        group.put("Group Category", GroupCategory);



// Add a new document with a generated ID
        db.collection("Groups")
                .add(group)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {



                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        Toast.makeText(com.example.jam3naapp2021.CreateGroupController.this, "added successfully.", Toast.LENGTH_SHORT).show();
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                        Toast.makeText(com.example.jam3naapp2021.CreateGroupController.this, "not added successfully.", Toast.LENGTH_SHORT).show();

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
                    GroupPic.setImageURI(selectedImageUri);
                }
            }
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.profile:
//                startActivity(new Intent(getApplicationContext(), PorfileActivity.class));
//                return true;
            case R.id.action_settings:
                Toast.makeText(this, " settings not ready yet !", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_logout:
                fAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                return true;
//            case R.id.CreateGroup:
//                startActivity(new Intent(getApplicationContext(), com.example.jam3naapp2021.CreateGroupController.class));
//                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }


}