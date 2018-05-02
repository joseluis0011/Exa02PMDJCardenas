package com.example.joseluis.exa02_pmd_jcardenas.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joseluis.exa02_pmd_jcardenas.Core.registrar.RegistrarInterface;
import com.example.joseluis.exa02_pmd_jcardenas.Core.registrar.RegistrarPresenter;
import com.example.joseluis.exa02_pmd_jcardenas.R;
import com.google.firebase.auth.FirebaseUser;

public class ActivityRegistrar extends AppCompatActivity implements View.OnClickListener,RegistrarInterface.View {

    Button registrar,atras;
    EditText email,password;
    private RegistrarPresenter mRegistrarPresenter;
    ProgressDialog mPrgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        initViews();
    }

    private void initViews() {
        registrar=(Button)findViewById(R.id.btnRegistrar);
        registrar.setOnClickListener(this);
        atras= (Button) findViewById(R.id.btnAtras);
        atras.setOnClickListener(this);
        email=(EditText)findViewById(R.id.ETemail);
        password=(EditText)findViewById(R.id.ETpassword);

        mRegistrarPresenter=new RegistrarPresenter(this);
        mPrgressDialog = new ProgressDialog(this);
        mPrgressDialog.setMessage("Please wait, Adding profile to database.");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegistrar:
                checkRegistrationDetails();
                break;
            case R.id.btnAtras:
                moveToLoginActivity();
                break;
        }
    }
    private void moveToLoginActivity() {
        Intent intent = new Intent(getApplicationContext(), ActivityLogin.class);
        startActivity(intent);
    }
    private void checkRegistrationDetails() {
        if(!TextUtils.isEmpty(email.getText().toString()) && !TextUtils.isEmpty(password.getText().toString())){
            initLogin(email.getText().toString(), password.getText().toString());
        }else{
            if(TextUtils.isEmpty(email.getText().toString())){
                email.setError("Please enter a valid email");
            }if(TextUtils.isEmpty(password.getText().toString())){
                password.setError("Please enter password");
            }
        }
    }
    private void initLogin(String email, String password) {
        mPrgressDialog.show();
        mRegistrarPresenter.Registrar(this, email, password);
    }
    @Override
    public void onRegistarSuccess(FirebaseUser firebaseUser) {
        mPrgressDialog.dismiss();
        Toast.makeText(getApplicationContext(), "Successfully Registered" , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegistrarError(String menaje) {
        mPrgressDialog.dismiss();
        Toast.makeText(getApplicationContext(), menaje, Toast.LENGTH_SHORT).show();
    }
}
