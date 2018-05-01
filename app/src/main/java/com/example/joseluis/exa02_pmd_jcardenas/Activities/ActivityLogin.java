package com.example.joseluis.exa02_pmd_jcardenas.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joseluis.exa02_pmd_jcardenas.Core.login.LoginInterface;
import com.example.joseluis.exa02_pmd_jcardenas.Core.login.LoginPresenter;
import com.example.joseluis.exa02_pmd_jcardenas.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityLogin extends AppCompatActivity  implements View.OnClickListener,LoginInterface.View{
    Button ingresar,registrar;
    EditText email,password;
    private LoginPresenter mLoginPresenter;
    ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toast.makeText(getApplicationContext(),"Requiere Internet",Toast.LENGTH_LONG).show();
        ingresar=(Button)findViewById(R.id.entra);
        registrar=(Button) findViewById(R.id.regis);
        email=(EditText)findViewById(R.id.ETcorreo);
        password=(EditText)findViewById(R.id.ETpassword);

        registrar.setOnClickListener(this);
        ingresar.setOnClickListener(this);

        mLoginPresenter=new LoginPresenter(this);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait, Logging in..");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.entra:
        //        String mailR = email.getText().toString();
        //        String passR = password.getText().toString();
                checkLoginDetails();
                break;
            case R.id.regis:
        //        String mailS = email.getText().toString();
        //        String passS = password.getText().toString();
                moveToRegisterActivity();
                break;
        }
    }
    private void moveToRegisterActivity( ) {
           Intent intent = new Intent(getApplicationContext(), ActivityRegistrar.class);
            startActivity(intent);
   //     FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
   //         @Override
   //         public void onComplete(@NonNull Task<AuthResult> task) {
   //             if (task.isSuccessful()){
   //                 Log.w("SESION","CREADO CORRECTAMENTE");
   //             }else{
   //                 Log.w("SESION",task.getException().getMessage());
   //             }
   //         }
   //     });
    }
    private void checkLoginDetails() {
            if(!TextUtils.isEmpty(email.getText().toString()) && !TextUtils.isEmpty(password.getText().toString())){
              initLogin(email.getText().toString(), password.getText().toString());
              }else{
                if(TextUtils.isEmpty(email.getText().toString())){
                  email.setError("Please enter a valid email");
            }if(TextUtils.isEmpty(password.getText().toString())){
              password.setError("Please enter password");
                }
          }
  //      FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password);
  //      Intent intent = new Intent(this,ActivityMain.class);
    }
    private void initLogin(String email, String password) {
        mProgressDialog.show();
        mLoginPresenter.login(this, email, password);
    }
    @Override
    public void onLoginSuccess(String mensaje) {
        mProgressDialog.dismiss();
        Toast.makeText(getApplicationContext(), "Successfully Logged in" , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginError(String mensaje) {
        mProgressDialog.dismiss();
        Toast.makeText(getApplicationContext(),mensaje , Toast.LENGTH_SHORT).show();
    }
}