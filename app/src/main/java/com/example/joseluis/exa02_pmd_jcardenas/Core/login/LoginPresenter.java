package com.example.joseluis.exa02_pmd_jcardenas.Core.login;

import android.app.Activity;

public class LoginPresenter implements LoginInterface.Presenter,LoginInterface.onLoginListener {
    private LoginInterface.View mLoginView;
    private LoginModel mLoginModel;
    public LoginPresenter(LoginInterface.View mLoginView){
        this.mLoginView=mLoginView;
        mLoginModel=new LoginModel(this);
    }
    @Override
    public void login(Activity activity, String email, String password) {
        mLoginModel.FirebaseLogin(activity , email,password);
    }

    @Override
    public void onSuccess(String mensaje) {
        mLoginView.onLoginSuccess(mensaje);
    }

    @Override
    public void onError(String mensaje) {
        mLoginView.onLoginError(mensaje);
    }
}
