package com.example.android_mvvm_tdd.register;

import com.example.android_mvvm_tdd.util.ILogger;
import com.google.common.base.Strings;

/**
 * Created by vivek on 05/11/17.
 */

public class RegisterModel {
    private static final String TAG = RegisterModel.class.getSimpleName();

    private String name;

    private String phoneNo;

    private String email;

    private String address;

    private ILogger log;

    public RegisterModel(ILogger log) {
        this.log = log;
    }

    public boolean isNameValid(){
        if (Strings.isNullOrEmpty(name)){
            return false;
        }else {
            return true;
        }
    }

    public boolean isPhoneValid(){
        if (Strings.isNullOrEmpty(phoneNo)){
            return false;
        }else {
            return true;
        }
    }

    public boolean isEmailValid(){
        if (Strings.isNullOrEmpty(email)){
            return false;
        }else {
            return true;
        }
    }

    public boolean isAddressValid(){
        if (Strings.isNullOrEmpty(address)){
            return false;
        }else {
            return true;
        }
    }

    public void register(){
        log.d(TAG, "register() called");
    }



    //getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
