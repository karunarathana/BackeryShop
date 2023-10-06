package com.example.backeryshop.InterFaces;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.backeryshop.Codes.Shop_Owner_Signup_Details;
import com.example.backeryshop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ShopOwner_Sign_up extends AppCompatActivity {

    EditText userEmail,userPassword,userConfirmPassword,userCompanyName,userTelNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_owner_signin);

        userTelNumber = findViewById(R.id.userTelNo);
        userEmail = findViewById(R.id.useremail);
        userPassword = findViewById(R.id.userpassword);
        userConfirmPassword = findViewById(R.id.userconfirmpassword);
        userCompanyName = findViewById(R.id.usercompany);
    }

    public void SignUpUser(View view){

        //Please Correct This Logic
        String userTelNum = userTelNumber.getText().toString();
        String userEmailAdd = userEmail.getText().toString();
        String companyName = userCompanyName.getText().toString();
        String password = userPassword.getText().toString();
        String confirmPassword = userConfirmPassword.getText().toString();

        if(userEmailAdd.equals("") || companyName.equals("") || password.equals("") || confirmPassword.equals("") ){
            Toast.makeText(this, "Please Fill All Data", Toast.LENGTH_SHORT).show();
        }else{
           if(password.equals(confirmPassword)){
               storageDatabase(userEmailAdd,companyName,password,userTelNum);
           }
        }
    }

    private void storageDatabase(String userEmailAdd,String companyName,String password,String telNumber  ) {

        Shop_Owner_Signup_Details shopOwnerSignupDetails = new Shop_Owner_Signup_Details(userEmailAdd,companyName,password,telNumber);
        FirebaseDatabase.getInstance().getReference("ShopOwner").child(companyName).setValue(shopOwnerSignupDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ShopOwner_Sign_up.this, "Save All Details ", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ShopOwner_Sign_up.this, "Please Try Again", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void showLoginInterface(View view){
        Intent intent = new Intent(getApplicationContext(),Shop_Login_InterFace.class);
        startActivity(intent);
    }
}