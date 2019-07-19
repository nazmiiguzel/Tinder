package com.example.digitustinder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginScreen extends AppCompatActivity {
    TextView userName ;
    TextView password;
    Button signId;
    Button registerId;
    CheckBox showId;
    TextView forgotId;

    FirebaseAuth firebaseAuth;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);



        userName = findViewById(R.id.etUser);
        password = findViewById(R.id.etPass);
        signId = findViewById(R.id.sign_id);
        registerId = (Button) findViewById(R.id.register_id);
        showId = findViewById(R.id.showPass);
        forgotId = findViewById(R.id.forgot_id);

        firebaseAuth=FirebaseAuth.getInstance();




        registerId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginScreen.this, RegisterScreen.class);
                startActivity(intent);
            }
        });

        signId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseAuth.signInWithEmailAndPassword(userName.getText().toString(),
                        password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful()){
                            startActivity(new Intent(LoginScreen.this,AppActivity.class));
                        }
                        else
                            Toast.makeText(LoginScreen.this,task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();

                    }
                });




            }
        });
    }


}
