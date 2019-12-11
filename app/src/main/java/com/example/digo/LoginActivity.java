package com.example.digo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.digo.Home.Information;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "LoginActivity";


    private DatabaseReference mDatabase;
    private FirebaseDatabase FirebaseDatabase;
    private FirebaseAuth mAuth;
    private EditText edtEmail;
    private EditText edtPass;
    private Button btnMasuk;
    private Button btnDaftar;
    private AwesomeValidation mAwesomeValidation;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final int LOGIN_STATUS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getAllUserInfo();
        mAwesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        edtEmail = (EditText)findViewById(R.id.userid);
        edtPass = (EditText)findViewById(R.id.passwrd);

        btnMasuk = (Button)findViewById(R.id.login);
        btnDaftar = (Button)findViewById(R.id.buatakun);

        btnMasuk.setOnClickListener(this);
        btnDaftar.setOnClickListener(this);
        //Validate Email
        mAwesomeValidation.addValidation(LoginActivity.this, R.id.userid, Patterns.EMAIL_ADDRESS,R.string.err_email);

        //Validate Password
        String regexPassword = ".{8,}";
        mAwesomeValidation.addValidation(LoginActivity.this, R.id.passwrd, regexPassword, R.string.err_password);
    }

    public void goToRegisterActivity(View view) {
        Intent register = new Intent(this,DaftarActivity.class);
        startActivity(register);

    }

    public void goToHome(View view) {
        Intent home = new Intent(this,MainActivity.class);
        startActivity(home);
    }

    private void signIn(){
        Log.d(TAG,"SignIn");
        if(!mAwesomeValidation.validate()){
            return;
        }
        String email = edtEmail.getText().toString();
        String password = edtPass.getText().toString();
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG,"sigIn:onComplete : " + task.isSuccessful());

                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"Berhasil Masuk",Toast.LENGTH_SHORT).show();
                            saveLoginState();
                            Intent home = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(home);
                            finish();
                        }
                        else{
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if(i == R.id.login){
            signIn();
        }
        else if(i == R.id.buatakun){
            Intent daftar = new Intent(LoginActivity.this,DaftarActivity.class);
            startActivity(daftar);
        }
    }

    public void saveLoginState(){
        SharedPreferences mySharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putInt("LOGIN_STATUS",1);
        editor.apply();
    }

    public ArrayList<Information> getAllUserInfo(){
        ArrayList<Information> list = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (int i=0;i<3;i++) {
                    dataSnapshot.child("code").getValue(String.class);
                    Log.d(TAG, "onDataChange: dataSnapshot "+dataSnapshot.getValue());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return list;
    }
}
