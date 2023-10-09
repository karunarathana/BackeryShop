package com.example.backeryshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.backeryshop.InterFaces.User_Sign_Up;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserSign_In extends AppCompatActivity {

    FirebaseAuth auth;
    GoogleSignInClient gsc;
    DatabaseReference databaseReference;
    EditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_sign_in);

        auth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();

        gsc = GoogleSignIn.getClient(this,gso);

        email = findViewById(R.id.useremail);
        password = findViewById(R.id.userpassword);




    }
    public void googleSign_in(View view){
        String uPassword1 = password.getText().toString();
        String userEmail =email.getText().toString();
        String [] email = userEmail.split("@");
        String uEmail = email[0];

        databaseReference = FirebaseDatabase.getInstance().getReference("User");
        databaseReference.child(uEmail).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        DataSnapshot dataSnapshot = task.getResult();
                        String uEmail = dataSnapshot.child("email").getValue().toString();
                        String uPassword = dataSnapshot.child("password").getValue().toString();
                        String uPhone = dataSnapshot.child("phone").getValue().toString();
                        String uID = dataSnapshot.child("userID").getValue().toString();

                        Bundle extras = new Bundle();
                        extras.putString("phone",uPhone);
                        extras.putString("userID",uID);

                        if(uEmail.equals(userEmail) && uPassword.equals(uPassword1) ){
                            Intent intent = new Intent(getApplicationContext(),MainDashboard.class);
                            intent.putExtras(extras);
                            startActivity(intent);
                        }


                    }else {
                        Toast.makeText(UserSign_In.this, "User Dosen't exisst", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(UserSign_In.this, "Faild to Read", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    public void checkValidUser(){
//       databaseReference = FirebaseDatabase.getInstance().getReference("User");
//        databaseReference.child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if(task.isSuccessful()){
//                    if(task.getResult().exists()){
//                        DataSnapshot dataSnapshot = task.getResult();
//                        String fname = dataSnapshot.child("userName").getValue().toString();
//                        String imagedata = dataSnapshot.child("dataImage").getValue().toString();
//                        Glide.with(Test.this).load(imagedata).into(uploadimg);
//
//                    }else {
//                        Toast.makeText(Test.this, "User Dosen't exisst", Toast.LENGTH_SHORT).show();
//                    }
//                }else {
//                    Toast.makeText(Test.this, "Faild to Read", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//    public void hk(){
//        Intent intent = gsc.getSignInIntent();
//        startActivityForResult(intent,100);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == 100){
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//
//            try {
//               task.getResult(ApiException.class);
//               navigateMainDashboard();
//            }catch (Exception e){
//                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    public void navigateMainDashboard(){
//        finish();
//        Intent intent = new Intent(UserSign_In.this,MainDashboard.class);
//        startActivity(intent);
//    }
    public void  send_Sign_Up_Page(View view){
        Intent intent = new Intent(getApplicationContext(), User_Sign_Up.class);
        startActivity(intent);
    }
}