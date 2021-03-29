package com.example.jam3naapp2021;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.jam3naapp2021.LoginActivity;
import com.example.jam3naapp2021.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText FirstName, mEmail, mPassword, mPhone, LastName, Password;
    Button mRegisterBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    TextView LoginUser;
    String VisitorID;
    ProgressDialog progressDialog;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FirstName   = findViewById(R.id.inputFirstUsername);
        LastName   = findViewById(R.id.inputLastUsername);
        mEmail      = findViewById(R.id.inputEmail);
        mPassword   = findViewById(R.id.inputPassword);
        Password=findViewById(R.id.inputConformPassword);
        mRegisterBtn= findViewById(R.id.btnRegister);
        LoginUser=findViewById(R.id.alreadyHaveAccount);
        mPhone=findViewById(R.id.inputPhoneNumber);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Resistering User...");

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            finish();
        }


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
            progressDialog.show();
            fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "User Created.", Toast.LENGTH_SHORT).show();

                        FirebaseUser user =fAuth.getCurrentUser();
//                         DocumentReference documentReference = fStore.collection("Visitor").document(VisitorID);
                        Toast.makeText(RegisterActivity.this,"Regesterd...\n"+user.getEmail(),Toast.LENGTH_LONG).show();
                      
                        Map<String, Object> visitor = new HashMap<>();
                        visitor.put("fname", UserfirstName);
                        visitor.put("lname", UserLastName);
                        visitor.put("Uphone", phoneNumber);
                        visitor.put("Uemail", email);
//                        documentReference.set(visitor).addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                        Log.d(TAG, "onSuccess: user Profile is created for " + VisitorID);
//                        }
//                        });
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                    else{
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);

                    }
                }
            });
        });

        LoginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));

            }
        });

    }
}




