package com.example.joseluis.exa02_pmd_jcardenas.Core.registrar;

import android.app.Activity;

import com.google.firebase.auth.FirebaseUser;

public class RegistrarPresenter implements RegistrarInterface.Presenter,RegistrarInterface.onRegistrarListener{
    private RegistrarInterface.View mRegisterView;
    private RegistrarModel mRegistrarModel;
    public RegistrarPresenter(RegistrarInterface.View registerview){
        this.mRegisterView=registerview;
        mRegistrarModel=new RegistrarModel(this);
    }
    @Override
    public void Registrar(Activity activity, String email, String password) {
        mRegistrarModel.FirebaseRegistracion(activity,email,password);
    }

    @Override
    public void onSuccess(FirebaseUser firebaseUser) {
        mRegisterView.onRegistarSuccess(firebaseUser);
    }

    @Override
    public void onError(String mensaje) {
        mRegisterView.onRegistrarError(mensaje);
    }
}
