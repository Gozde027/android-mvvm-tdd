package com.example.android_mvvm_tdd.register;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.android_mvvm_tdd.R;
import com.example.android_mvvm_tdd.databinding.ActivityRegisterBinding;
import com.example.android_mvvm_tdd.util.Logger;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = RegisterActivity.class.getSimpleName();

    ActivityRegisterBinding dataBinding;

    RegisterViewModel registerViewModel;
    private Observable<RegisterViewModel.RegisterViewModelCommand> registerObservable;

    public static void start(Context context) {
        Intent starter = new Intent(context, RegisterActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        registerViewModel = new RegisterViewModel(new Logger());

        initUI();

        //using data binding to set the view model to the view
        dataBinding.setData(registerViewModel);
    }

    private void initUI() {
        dataBinding.btnRegister.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_register:

                registerViewModel.getRegisterModel().setName(dataBinding.txtRegisterName
                        .getText().toString());
                registerViewModel.getRegisterModel().setEmail(dataBinding.txtRegisterEmail
                        .getText().toString());
                registerViewModel.getRegisterModel().setPhoneNo(dataBinding.txtRegisterPhone
                        .getText().toString());
                registerViewModel.getRegisterModel().setAddress(dataBinding.txtRegisterAddress
                        .getText().toString());

                registerObservable = registerViewModel.doRegister();
                registerObservable.subscribe(registerSubscriber);

                break;
        }
    }

    Observer<RegisterViewModel.RegisterViewModelCommand> registerSubscriber = new Observer<RegisterViewModel.RegisterViewModelCommand>() {
        @Override
        public void onSubscribe(Disposable d) {
            Log.d(TAG, "onSubscribe() called with: d = [" + d + "]");
        }

        @Override
        public void onNext(RegisterViewModel.RegisterViewModelCommand registerViewModelCommand) {
            Log.d(TAG, "onNext() called with: registerViewModelCommand = [" + registerViewModelCommand + "]");

            if (registerViewModelCommand instanceof RegisterViewModel.RegisterViewModelCommand.RegisterViewModelCommandNameError){
                showNameError();
            }

            if (registerViewModelCommand instanceof RegisterViewModel.RegisterViewModelCommand.RegisterViewModelCommandEmailError){
                showEmailError();
            }

            if (registerViewModelCommand instanceof RegisterViewModel.RegisterViewModelCommand.RegisterViewModelCommandAddressError){
                showAddressError();
            }

            if (registerViewModelCommand instanceof RegisterViewModel.RegisterViewModelCommand.RegisterViewModelCommandPhoneNoError){
                showPhoneError();
            }

            if (registerViewModelCommand instanceof RegisterViewModel.RegisterViewModelCommand.RegisterViewModelCommandRegisterSuccess){
                showRegisterSuccess();
            }


        }

        @Override
        public void onError(Throwable e) {
            Log.d(TAG, "onError() called with: e = [" + e + "]");
        }

        @Override
        public void onComplete() {
            Log.d(TAG, "onComplete() called");
        }
    };

    //screen functions

    public void showNameError() {
        dataBinding.txtRegisterName.setError("Please enter a valid name");
    }

    public void showPhoneError() {
        dataBinding.txtRegisterPhone.setError("Please enter a valid phone no");
    }

    public void showEmailError() {
        dataBinding.txtRegisterEmail.setError("Please enter a valid email");
    }

    public void showAddressError() {
        dataBinding.txtRegisterAddress.setError("Please enter a valid address");
    }

    public void showRegisterSuccess() {
        showToast("User registered");
    }

    public void showRegisterFailed() {
        showToast("User registration failed");
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
