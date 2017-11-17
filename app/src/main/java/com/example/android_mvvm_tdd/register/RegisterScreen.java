package com.example.android_mvvm_tdd.register;

import com.example.android_mvvm_tdd.Screen;

/**
 * Created by vivek on 05/11/17.
 */

public interface RegisterScreen extends Screen {

    void showNameError();
    void showPhoneError();
    void showEmailError();
    void showAddressError();

    void showRegisterSuccess();
    void showRegisterFailed();

}
