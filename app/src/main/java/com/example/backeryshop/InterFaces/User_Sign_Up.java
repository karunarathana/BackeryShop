package com.example.backeryshop.InterFaces;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.backeryshop.Codes.User_Sign_Up_Details;
import com.example.backeryshop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class User_Sign_Up extends AppCompatActivity {

    EditText userEmail,userPhone,userPassword,confirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);

        userEmail = findViewById(R.id.useremail);
        userPhone = findViewById(R.id.telPhone);
        userPassword = findViewById(R.id.userpassword);
        confirmPassword = findViewById(R.id.userpassword1);

    }
    public void sign_Up(View view){


        String email =userEmail.getText().toString();
        String phone = userPhone.getText().toString();
        String password = userPassword.getText().toString();
        String passwordC = confirmPassword.getText().toString();

        String[] customEmail = email.split("@");
        String customEmail1= customEmail[0];

        Random rnd = new Random();
        //userId must be unique please check Implement this method
        String userID = String.valueOf( rnd.nextInt(9999));

        if(password.equals(passwordC)){
            User_Sign_Up_Details userDetails = new User_Sign_Up_Details(userID,email,phone,password);
            FirebaseDatabase.getInstance().getReference("User").child(customEmail1).setValue(userDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(User_Sign_Up.this, "Saved", Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(User_Sign_Up.this, "Hellow", Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Toast.makeText(this, "Password Does not Match", Toast.LENGTH_SHORT).show();
        }

    }
}