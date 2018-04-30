package com.example.joseluis.exa02_pmd_jcardenas.Core.registrar;

import android.app.Activity;

import com.google.firebase.auth.FirebaseUser;

public interface RegistrarInterface {
    interface View{
        void onRegistarSuccess(FirebaseUser firebaseUser);
        void onRegistrarError(String menaje);
    }
    interface Presenter{
        void Registrar(Activity activity,String email,String password);
    }
    interface Modelo{
        void FirebaseRegistracion(Activity activity , String email,String password);
    }
    interface onRegistrarListener{
        void onSuccess(FirebaseUser firebaseUser);
        void onError(String mensaje);
    }
}
