package com.example.digitustinder;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class RegisterScreen<storageReference> extends AppCompatActivity implements AdapterView.OnItemSelectedListener {



    TextView name;
    TextView username;
    TextView password;
    Button register;

    Uri profileImageUrl;

    DatabaseReference databaseReference;

    TextView birthday_id;
    DatePickerDialog.OnDateSetListener mDateSetListener;

    Spinner city_id;
    TextView cityChooseId;

    Button btnChoose , btnUpload;
    ImageView imgView;

    //Firebase Inıt
    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseAuth firebaseAuth;

    private final int PICK_IMAGE_REQUEST = 71 ;
    Uri filePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        birthday_id = (TextView) findViewById(R.id.birthday_id);
        name = (TextView) findViewById(R.id.name_id);
        username = (TextView) findViewById(R.id.username_id);
        password = (TextView) findViewById(R.id.password_id);
        register = (Button) findViewById(R.id.register_btn_id);



        birthday_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        RegisterScreen.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1 ;


                String date = day + "/" + month + "/" + year ;
                birthday_id.setText(date);
            }
        };

        // Inıt View
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        btnChoose = (Button) findViewById(R.id.image_id);
        btnUpload = (Button) findViewById(R.id.upload_id);
        imgView = (ImageView) findViewById(R.id.img_view);

        ////////////////////////////////////////////   CİTY    /////////////////////////
        city_id = (Spinner) findViewById(R.id.city_id);
        cityChooseId = (TextView) findViewById(R.id.cityChooseId);

        city_id.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("İstanbul");
        categories.add("İzmir");
        categories.add("BUCA");
        categories.add("Adana");
        categories.add("Mersin");
        categories.add("Rize");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        city_id.setAdapter(dataAdapter);






/*
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.cities, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city_id.setAdapter(adapter);
*/


         //////////////////////////////////////   CİTY     ////////////////////////////


        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent.createChooser(intent,"Select picture"),PICK_IMAGE_REQUEST);

            }
        });






        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveUserInformation();





            }
        });
    }



            public void saveUserInformation(){





                 final String e_mail = username.getText().toString().trim();
                final String displayName = name.getText().toString().trim();
                final String date = birthday_id.getText().toString().trim();
                final String city = city_id.getSelectedItem().toString();
                final String passwordd = password.getText().toString().trim();


                if (e_mail.isEmpty()){
                    Toast.makeText(this,"Please enter email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (date.isEmpty()){
                    Toast.makeText(this,"Please choose your birthdate",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (passwordd.isEmpty()){
                    Toast.makeText(this,"Please enter the password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (displayName.isEmpty()){
                    name.setError("Name required");
                    name.requestFocus();
                    return;
                }
              final FirebaseUser user = firebaseAuth.getCurrentUser();

                firebaseAuth.createUserWithEmailAndPassword(e_mail,passwordd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful()){
                            Toast.makeText(RegisterScreen.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(RegisterScreen.this,"Registered Cancelled",Toast.LENGTH_SHORT).show();






                    }
                });

       if (user!=null && profileImageUrl != null){
           UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                   .setDisplayName(displayName)
                   .setPhotoUri(Uri.parse(String.valueOf(profileImageUrl)))
                   .build();


           user.updateProfile(profile)
                   .addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           if(task.isSuccessful()){
                               Toast.makeText(RegisterScreen.this,"Profile Updated",Toast.LENGTH_SHORT).show();
                           }


                       }
                   });

       }
                if (filePath!=null){
                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setTitle("Uploading...");
                    progressDialog.show();
                    final StorageReference ref = storageReference.child("image/*" + UUID.randomUUID().toString());
                    ref.putFile(filePath)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    progressDialog.dismiss();

                                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            profileImageUrl = uri;
                                            UserInformation userInformation =
                                        new UserInformation(user.getUid(),e_mail,displayName,date,city,passwordd,String.valueOf(profileImageUrl));
                                            databaseReference.child("user").child(user.getUid()).setValue(userInformation) ;


                                            Intent intent = new Intent(RegisterScreen.this, LoginScreen.class);


                                            startActivity(intent);

                                        }
                                    });
                                }

                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(RegisterScreen.this,"Failed" + e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }


    public Uri getProfileImageUrl() {
        return profileImageUrl;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
        && data!= null && data.getData()!=null){

            filePath = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                imgView.setImageBitmap(bitmap);



            }
        catch(IOException e) {

            e.printStackTrace();

        }



        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       final String item = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

/*


DatabaseReference imagestore = FirebaseDatabase.getInstance().getReference().child("Image");

                                            HashMap<String,String> hashMap = new HashMap<>();
                                            hashMap.put("imageurl" , String.valueOf(profileImageUrl));


                                            imagestore.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(RegisterScreen.this,"Uploaded",Toast.LENGTH_SHORT).show();
                                                }
                                            });
 */

}
