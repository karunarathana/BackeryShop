package com.example.backeryshop.InterFaces;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.backeryshop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Shop_Login_InterFace extends AppCompatActivity {
    EditText companyName,userPassword;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_login_inter_face);

        companyName =findViewById(R.id.usercompany);
        userPassword = findViewById(R.id.userpassword);
    }
    public void loginUser(View view){
        String cName = companyName.getText().toString();
        String password = userPassword.getText().toString();
        readData(cName,password);

    }
    private void readData(String cName,String uPassword) {
        databaseReference = FirebaseDatabase.getInstance().getReference("ShopOwner");
        databaseReference.child(cName).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Shop_Login_InterFace.this, "Please Wait.......", Toast.LENGTH_SHORT).show();
                    if (task.getResult().exists()) {
                        DataSnapshot dataSnapshot = task.getResult();

                       String company_Name = dataSnapshot.child("userCompanyName").getValue().toString();
                       String email = dataSnapshot.child("userEmailAdd").getValue().toString();
                       String userPassword = dataSnapshot.child("userPassword").getValue().toString();

                        if(cName.equals(company_Name)){
                            if(uPassword.equals(userPassword)){

                                Bundle extras = new Bundle();
                                extras.putString("V1",company_Name);
                                extras.putString("V2",email);

                                Intent intent = new Intent(Shop_Login_InterFace.this,Shop_Owner_Main_Dashboard.class);
                                intent.putExtras(extras);
                                startActivity(intent);


                            }else{
                                Toast.makeText(Shop_Login_InterFace.this, "Password Does not matched", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(Shop_Login_InterFace.this, "Company name Does not matched", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(Shop_Login_InterFace.this, "Please Enter Correct Company Name", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(Shop_Login_InterFace.this, "Sorry Fail The Mission", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}