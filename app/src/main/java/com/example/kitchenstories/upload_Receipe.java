package com.example.kitchenstories;


//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.annotation.RequiresApi;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Objects;

public class upload_Receipe extends AppCompatActivity {

    ImageView receipeImage;
    Uri uri;
    EditText txt_name,txt_description,txt_price;
    String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload__receipe);

        receipeImage = (ImageView) findViewById(R.id.iv_foodImage);
        txt_name =(EditText)findViewById(R.id.txt_receipe_name);
        txt_description=(EditText)findViewById(R.id.text_Description);
        txt_price=(EditText)findViewById(R.id.text_price);
    }

    public void btnSelectImage(View view) {

        Intent photoPicker = new Intent(Intent.ACTION_PICK);
        photoPicker.setType("image/*");
        startActivityForResult(photoPicker, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            uri = data.getData();
            receipeImage.setImageURI(uri);

        } else Toast.makeText(this, "You haven't picked an image", Toast.LENGTH_SHORT).show();


    }


   public void uploadImage(){
       FirebaseAuth mAuth = FirebaseAuth.getInstance();
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("ReceipeImage").child(uri.getLastPathSegment());
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Receipe uploading..");
        progressDialog.show();
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri>uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri uriImage = uriTask.getResult();
                imageUrl = uriImage.toString();
                uploadReceipe();
                progressDialog.dismiss();

               // Toast.makeText(upload_Receipe.this, "Image Uploaded", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
            }
        });
    }



    public void btnUploadReceipe(View view) {

        uploadImage();

    }


    public void uploadReceipe() {



        FoodData foodData = new FoodData(txt_name.getText().toString(), txt_description.getText().toString(), txt_price.getText().toString(), imageUrl

        );

        String myCurrentDateTime = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        FirebaseDatabase.getInstance().getReference("Receipe").child(myCurrentDateTime).setValue(foodData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(upload_Receipe.this, "Receipe Uploaded", Toast.LENGTH_SHORT).show();

                    finish();
                }


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(upload_Receipe.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();


            }

            ;
        });
    }
}