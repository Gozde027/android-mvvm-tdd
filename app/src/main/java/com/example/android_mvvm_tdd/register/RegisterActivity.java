package com.example.android_mvvm_tdd.register;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.android_mvvm_tdd.R;
import com.example.android_mvvm_tdd.databinding.ActivityRegisterBinding;
import com.example.android_mvvm_tdd.util.Logger;

public class RegisterActivity extends AppCompatActivity implements RegisterScreen, View.OnClickListener{

    public static final String TAG = RegisterActivity.class.getSimpleName();

    ActivityRegisterBinding dataBinding;

    RegisterViewModel registerViewModel;

    public static void start(Context context) {
        Intent starter = new Intent(context, RegisterActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        registerViewModel = new RegisterViewModel(this, new Logger());

        initUI();

        //using data binding to set the view model to the view
        dataBinding.setData(registerViewModel);
    }

    private void initUI() {
        dataBinding.btnRegister.setOnClickListener(this);
    }


    @Override
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

                registerViewModel.doRegister();
                break;
        }
    }


    //screen functions


    @Override
    public void showNameError() {
        dataBinding.txtRegisterName.setError("Please enter a valid name");
    }

    @Override
    public void showPhoneError() {
        dataBinding.txtRegisterPhone.setError("Please enter a valid phone no");
    }

    @Override
    public void showEmailError() {
        dataBinding.txtRegisterEmail.setError("Please enter a valid email");
    }

    @Override
    public void showAddressError() {
        dataBinding.txtRegisterAddress.setError("Please enter a valid address");
    }

    @Override
    public void showRegisterSuccess() {
        showToast("User registered");
    }

    @Override
    public void showRegisterFailed() {
        showToast("User registration failed");
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
