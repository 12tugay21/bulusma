package com.example.bulusma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText Mail, Password ;
    Button Logingiris;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Mail = findViewById(R.id.Mail);
        Password = findViewById(R.id.Password);
        Logingiris = findViewById(R.id.Logingiris);

        fAuth =FirebaseAuth.getInstance();
        if (fAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(),AnaActivity.class));
            finish();
        }


        Logingiris.setOnClickListener(new View.OnClickListener() {
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

                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"Başarılı giriş knk", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),AnaActivity.class));
                        }else {
                            Toast.makeText(LoginActivity.this,"Hata var" +task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
    }
}
