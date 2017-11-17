package com.example.android_mvvm_tdd.register;

import com.example.android_mvvm_tdd.util.ILogger;

/**
 * Created by vivek on 05/11/17.
 */

public class RegisterViewModel {

    private RegisterScreen registerScreen;

    private RegisterModel registerModel;


    public RegisterViewModel(RegisterScreen registerScreen, ILogger logger) {
        this.registerScreen = registerScreen;

        this.registerModel = new RegisterModel(logger);
    }

    public void doRegister(){
        boolean hasError = false;

        if (!registerModel.isNameValid()){
            registerScreen.showNameError();
            hasError = true;
        }

        if (!registerModel.isEmailValid()){
            registerScreen.showEmailError();
            hasError = true;
        }

        if (!registerModel.isPhoneValid()){
            registerScreen.showPhoneError();
            hasError = true;
        }

        if (!registerModel.isAddressValid()){
            registerScreen.showAddressError();
            hasError = true;
        }


        if (hasError){
            return;
        }

        registerModel.register();

        registerScreen.showRegisterSuccess();
    }

    //provide only getter for the model to prevent direct replacement
    public RegisterModel getRegisterModel() {
        return registerModel;
    }

}
