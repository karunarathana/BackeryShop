package com.example.backeryshop.InterFaces;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.backeryshop.Codes.Upload_Item_Details;
import com.example.backeryshop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Random;

public class Add_Product_Page extends AppCompatActivity {

    Uri uri;
    String imageURL,cName;
    ImageView uploadImage;
    EditText itemName, itemDesc, itemPrice,itemId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_page);

        itemId = findViewById(R.id.itemName);
        uploadImage = findViewById(R.id.uploadImage);
        itemName =findViewById(R.id.itemName1);
        itemDesc =findViewById(R.id.itemDescription);
        itemPrice =findViewById(R.id.itemPrice);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        cName = extras.getString("V1");

        ActivityResultLauncher<Intent> activityResultLauncher =registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK){
                            Intent data  = result.getData();
                            uri =data.getData();
                            uploadImage.setImageURI(uri);

                        }else{
                            Toast.makeText(Add_Product_Page.this, "No Image Selector", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }

        });
    }
    public void saveData(View view){
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("ProductImages")
                .child(uri.getLastPathSegment());

//        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
//        builder.setCancelable(false);
//        builder.setView(R.layout.progress_layout);
//        AlertDialog dialog = builder.create();
//        dialog.show();
//        dialog.dismiss();

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while(!uriTask.isComplete());
                Uri uri1 = uriTask.getResult();
                imageURL = uri1.toString();
                uploadData();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Add_Product_Page.this, "Error Please Try Again", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void uploadData(){


        String shopName=cName;
        String productID=itemId.getText().toString();
        String productName = itemName.getText().toString();
        String productDescription = itemDesc.getText().toString();
        String productPrice = itemPrice.getText().toString();

        Upload_Item_Details uploadItemDetails = new Upload_Item_Details(productName,productDescription,productPrice,imageURL,productID);
        FirebaseDatabase.getInstance().getReference("foodStore").child(shopName).child(productID).setValue(uploadItemDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    updateAllFoodStore();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Add_Product_Page.this, "Please Try Again", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void updateAllFoodStore(){
        String shopName=cName;
        String productID=itemId.getText().toString();
        String productName = itemName.getText().toString();
        String productDescription = itemDesc.getText().toString();
        String productPrice = itemPrice.getText().toString();

        Upload_Item_Details uploadItemDetails = new Upload_Item_Details(shopName,productName,productDescription,productPrice,imageURL,productID);
        FirebaseDatabase.getInstance().getReference("foodAllStore").child(productID).setValue(uploadItemDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Add_Product_Page.this, "Saved", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Add_Product_Page.this, "Please Try Again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}