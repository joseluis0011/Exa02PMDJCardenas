package com.example.joseluis.exa02_pmd_jcardenas.Core.login;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginModel implements LoginInterface.Model {
    private LoginInterface.onLoginListener mOnLoginListener;
    public LoginModel(LoginInterface.onLoginListener onLoginListener){
        this.mOnLoginListener=onLoginListener;
    }
    @Override
    public void FirebaseLogin(Activity activity, String email, String password) {
        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            mOnLoginListener.onSuccess(task.getResult().toString());
                        }else{
                            mOnLoginListener.onError(task.getException().toString());
                        }
                    }
                });
    }
}
