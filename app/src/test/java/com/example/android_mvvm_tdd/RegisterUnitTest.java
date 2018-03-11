package com.example.android_mvvm_tdd;

import com.example.android_mvvm_tdd.register.RegisterScreen;
import com.example.android_mvvm_tdd.register.RegisterViewModel;
import com.example.android_mvvm_tdd.util.UnitTestLogger;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;

/**
 * Created by vivek on 05/11/17.
 */

public class RegisterUnitTest {

    //class that is being tested
    RegisterViewModel registerViewModel;

    final String dummyName = "Name";
    final String dummyEmail = "email@example.com";
    final String dummyPhone = "1234567890";
    final String dummyAddress = "Address line 1, Address line 2";

    @Before
    public void setupRegisterViewModel(){
        //we create an instance of the class to be tested by passing the mocked object
        registerViewModel = new RegisterViewModel(new UnitTestLogger());

        registerViewModel.getRegisterModel().setName(dummyName);
        registerViewModel.getRegisterModel().setEmail(dummyEmail);
        registerViewModel.getRegisterModel().setPhoneNo(dummyPhone);
        registerViewModel.getRegisterModel().setAddress(dummyAddress);
    }

    @Test
    public void registerUserWithEmptyName_showsNameError(){
        TestObserver<RegisterViewModel.RegisterViewModelCommand> testObserver = new TestObserver<>();

        registerViewModel.getRegisterModel().setName("");
        registerViewModel.doRegister().subscribe(testObserver);

        testObserver.assertNoErrors();
        testObserver.assertValue(new RegisterViewModel.RegisterViewModelCommand.RegisterViewModelCommandNameError());
        testObserver.assertNever(new RegisterViewModel.RegisterViewModelCommand.RegisterViewModelCommandRegisterSuccess());

    }

    @Test
    public void registerUserWithValidName_dontShowNameError(){
        TestObserver<RegisterViewModel.RegisterViewModelCommand> testObserver = new TestObserver<>();
        registerViewModel.getRegisterModel().setName("Vivek");
        registerViewModel.doRegister().subscribe(testObserver);

        testObserver.assertNoErrors();
        testObserver.assertNever(new RegisterViewModel.RegisterViewModelCommand.RegisterViewModelCommandNameError());
        testObserver.assertValue(new RegisterViewModel.RegisterViewModelCommand.RegisterViewModelCommandRegisterSuccess());

    }

    @Test
    public void registerUserWithEmptyEmail_showsEmailError(){
        TestObserver<RegisterViewModel.RegisterViewModelCommand> testObserver = new TestObserver<>();
        registerViewModel.getRegisterModel().setEmail("");
        registerViewModel.doRegister().subscribe(testObserver);

        testObserver.assertNoErrors();
        testObserver.assertValue(new RegisterViewModel.RegisterViewModelCommand.RegisterViewModelCommandEmailError());
        testObserver.assertNever(new RegisterViewModel.RegisterViewModelCommand.RegisterViewModelCommandRegisterSuccess());

    }

    @Test
    public void registerUserWithEmptyPhone_showsPhoneError(){
        TestObserver<RegisterViewModel.RegisterViewModelCommand> testObserver = new TestObserver<>();
        registerViewModel.getRegisterModel().setPhoneNo("");
        registerViewModel.doRegister().subscribe(testObserver);


        testObserver.assertNoErrors();
        testObserver.assertValue(new RegisterViewModel.RegisterViewModelCommand.RegisterViewModelCommandPhoneNoError());
        testObserver.assertNever(new RegisterViewModel.RegisterViewModelCommand.RegisterViewModelCommandRegisterSuccess());
    }

    @Test
    public void registerUserWithEmptyAddress_showsAddressError(){
        TestObserver<RegisterViewModel.RegisterViewModelCommand> testObserver = new TestObserver<>();
        registerViewModel.getRegisterModel().setAddress("");
        registerViewModel.doRegister().subscribe(testObserver);

        testObserver.assertNoErrors();
        testObserver.assertValue(new RegisterViewModel.RegisterViewModelCommand.RegisterViewModelCommandAddressError());
        testObserver.assertNever(new RegisterViewModel.RegisterViewModelCommand.RegisterViewModelCommandRegisterSuccess());
    }


    @Test
    public void registerUserWithAllDetails_shouldCallRegisterSuccess(){
        TestObserver<RegisterViewModel.RegisterViewModelCommand> testObserver = new TestObserver<>();
        registerViewModel.doRegister().subscribe(testObserver);

        testObserver.assertNoErrors();
        testObserver.assertNever(new RegisterViewModel.RegisterViewModelCommand.RegisterViewModelCommandNameError());
        testObserver.assertNever(new RegisterViewModel.RegisterViewModelCommand.RegisterViewModelCommandPhoneNoError());
        testObserver.assertNever(new RegisterViewModel.RegisterViewModelCommand.RegisterViewModelCommandEmailError());
        testObserver.assertNever(new RegisterViewModel.RegisterViewModelCommand.RegisterViewModelCommandAddressError());
        testObserver.assertValue(new RegisterViewModel.RegisterViewModelCommand.RegisterViewModelCommandRegisterSuccess());
    }

}
