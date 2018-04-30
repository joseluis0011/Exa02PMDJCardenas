package com.example.joseluis.exa02_pmd_jcardenas.Core.registrar;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrarModel implements RegistrarInterface.Modelo {
    private static final String TAG= RegistrarModel.class.getSimpleName();
    private RegistrarInterface.onRegistrarListener mOnregistrarListener;
    public RegistrarModel(RegistrarInterface.onRegistrarListener onRegistrarListener){
        this.mOnregistrarListener=onRegistrarListener;
    }
    @Override
    public void FirebaseRegistracion(Activity activity, String email, String password) {
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){
                            mOnregistrarListener.onError(task.getException().getMessage());
                        }else{
                            mOnregistrarListener.onSuccess(task.getResult().getUser());
                        }
                    }
                });
    }
}
