package com.example.bulusma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import io.opencensus.stats.AggregationData;

public class RegisterActivity extends AppCompatActivity {

    EditText Surname, Mail, Password, Number;
    Button Registergiris;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Surname = findViewById(R.id.Surname);
        Mail = findViewById(R.id.Mail);
        Password = findViewById(R.id.Password);
        Number = findViewById(R.id.Number);
        Registergiris = findViewById(R.id.Registergiris);

        fAuth =FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(),AnaActivity.class));
            finish();
        }

        Registergiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Mail.getText().toString().trim();
                String password = Password.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Mail.setError("boş kalamaz knk");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Password.setError("Şifre olmadan olur mu?");
                    return;
                }
                if (password.length() < 6);{
                    Password.setError("Biraz daha uzun yap knk");
                }

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this,"Kullanıcı Oluşturuldu", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(getApplicationContext(),AnaActivity.class));
                        }else{
                            Toast.makeText(RegisterActivity.this,"Hata var" +task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}
