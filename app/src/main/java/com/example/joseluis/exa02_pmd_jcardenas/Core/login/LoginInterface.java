package com.example.joseluis.exa02_pmd_jcardenas.Core.login;

import android.app.Activity;

public interface LoginInterface {
    interface View{
        void onLoginSuccess(String mensaje);
        void onLoginError(String mensaje);
    }
    interface Presenter{
        void login(Activity activity,String email,String password);
    }
    interface Model{
        void FirebaseLogin(Activity activity,String email,String password);
    }
    interface onLoginListener{
        void onSuccess(String mensaje);
        void onError(String mensaje);
    }
}
