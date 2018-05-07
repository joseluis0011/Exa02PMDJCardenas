package com.example.joseluis.exa02_pmd_jcardenas.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.joseluis.exa02_pmd_jcardenas.Core.login.LoginInterface;
import com.example.joseluis.exa02_pmd_jcardenas.Core.login.LoginPresenter;
import com.example.joseluis.exa02_pmd_jcardenas.R;

public class ActivityLogin extends AppCompatActivity  implements View.OnClickListener,LoginInterface.View{
    Button ingresar,registrar;
    EditText email,password;
    private LoginPresenter mLoginPresenter;
    ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
   //     Toast.makeText(getApplicationContext(),"Requiere Internet",Toast.LENGTH_LONG).show();
        ingresar=(Button)findViewById(R.id.entra);
        registrar=(Button) findViewById(R.id.regis);
        email=(EditText)findViewById(R.id.ETcorreo);
        password=(EditText)findViewById(R.id.ETpassword);

        registrar.setOnClickListener(this);
        ingresar.setOnClickListener(this);

        mLoginPresenter=new LoginPresenter(this);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Porfavor ingrese un password");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.entra:
                checkLoginDetails();
                break;
            case R.id.regis:
                moveToRegisterActivity();
                break;
        }
    }
    private void moveToRegisterActivity( ) {
           Intent intent = new Intent(getApplicationContext(), ActivityRegistrar.class);
            startActivity(intent);
    }
    private void checkLoginDetails() {
            if(!TextUtils.isEmpty(email.getText().toString()) && !TextUtils.isEmpty(password.getText().toString())){
              initLogin(email.getText().toString(), password.getText().toString());
              }else{
                if(TextUtils.isEmpty(email.getText().toString())){
                  email.setError("Porfavor ingrese un correo");
            }if(TextUtils.isEmpty(password.getText().toString())){
              password.setError("Porfavor ingrese un password");
                }
          }
    }
    private void initLogin(String email, String password) {
        mProgressDialog.show();
        mLoginPresenter.login(this, email, password);
    }
    @Override
    public void onLoginSuccess(String mensaje) {
        mProgressDialog.dismiss();
     Toast.makeText(getApplicationContext(), "Secion Iniciada" , Toast.LENGTH_SHORT).show();
     startActivity(new Intent(this,MainActivity.class));
     finish();
    }

    @Override
    public void onLoginError(String mensaje) {
        mProgressDialog.dismiss();
        Toast.makeText(getApplicationContext(),mensaje , Toast.LENGTH_SHORT).show();
    }
}