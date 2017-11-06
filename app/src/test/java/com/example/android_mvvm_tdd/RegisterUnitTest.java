package com.example.android_mvvm_tdd;

import com.example.android_mvvm_tdd.register.RegisterScreen;
import com.example.android_mvvm_tdd.register.RegisterViewModel;
import com.example.android_mvvm_tdd.util.UnitTestLogger;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Created by vivek on 05/11/17.
 */

public class RegisterUnitTest {

    @Mock
    RegisterScreen registerScreen;

    //class that is being tested
    RegisterViewModel registerViewModel;

    final String dummyName = "Name";
    final String dummyEmail = "email@example.com";
    final String dummyPhone = "1234567890";
    final String dummyAddress = "Address line 1, Address line 2";

    @Before
    public void setupRegisterViewModel(){ //this function will be called before all tests are run

        // call this function to init all objects annotated with @mock
        MockitoAnnotations.initMocks(this);

        //we create an instance of the class to be tested by passing the mocked object
        registerViewModel = new RegisterViewModel(registerScreen, new UnitTestLogger());

        registerViewModel.getRegisterModel().setName(dummyName);
        registerViewModel.getRegisterModel().setEmail(dummyEmail);
        registerViewModel.getRegisterModel().setPhoneNo(dummyPhone);
        registerViewModel.getRegisterModel().setAddress(dummyAddress);
    }

    @Test
    public void registerUserWithEmptyName_showsNameError(){
        registerViewModel.getRegisterModel().setName("");
        registerViewModel.doRegister();

        //use mockito to verify that the showNameError() method is called in the screen object
        Mockito.verify(registerScreen).showNameError();
    }

    @Test
    public void registerUserWithEmptyEmail_showsEmailError(){
        registerViewModel.getRegisterModel().setEmail("");
        registerViewModel.doRegister();

        //use mockito to verify that the showEmailError() method is called in the screen object
        Mockito.verify(registerScreen).showEmailError();
    }

    @Test
    public void registerUserWithEmptyPhone_showsPhoneError(){
        registerViewModel.getRegisterModel().setPhoneNo("");
        registerViewModel.doRegister();

        //use mockito to verify that the showNameError() method is called in the screen object
        Mockito.verify(registerScreen).showPhoneError();
    }

    @Test
    public void registerUserWithEmptyAddress_showsAddressError(){
        registerViewModel.getRegisterModel().setAddress("");
        registerViewModel.doRegister();

        //use mockito to verify that the showNameError() method is called in the screen object
        Mockito.verify(registerScreen).showAddressError();
    }


    @Test
    public void registerUserWithAllDetails_shouldCallRegisterSuccess(){
        registerViewModel.doRegister();

        Mockito.verify(registerScreen).showRegisterSuccess();
    }

}
