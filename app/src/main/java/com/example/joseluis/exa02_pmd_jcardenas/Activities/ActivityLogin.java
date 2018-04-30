package com.example.joseluis.exa02_pmd_jcardenas.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
        initViews();
    }

    public void initViews() {
        Toast.makeText(getApplicationContext(),"Requiere Internet",Toast.LENGTH_LONG).show();
        ingresar=(Button)findViewById(R.id.btnEntrar);
//        ingresar.setOnClickListener(this);
        registrar=(Button) findViewById(R.id.register);
        registrar.setOnClickListener(this);
        email=(EditText)findViewById(R.id.ETemail);
        password=(EditText)findViewById(R.id.ETpassword);

        mLoginPresenter=new LoginPresenter(this);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait, Logging in..");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEntrar:
                checkLoginDetails();
                break;
            case R.id.register:
                moveToRegisterActivity();
                break;
        }
    }
    private void moveToRegisterActivity() {
        Intent intent = new Intent(getApplicationContext(), ActivityRegistrar.class);
        startActivity(intent);
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
