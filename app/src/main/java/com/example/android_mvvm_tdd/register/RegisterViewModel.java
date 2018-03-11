package com.example.android_mvvm_tdd.register;

import com.example.android_mvvm_tdd.util.ILogger;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by vivek on 05/11/17.
 */

public class RegisterViewModel {
    private static final String TAG = RegisterViewModel.class.getSimpleName();

    private RegisterModel registerModel;

    public RegisterViewModel(ILogger logger) {
        this.registerModel = new RegisterModel(logger);
    }

    public Observable<RegisterViewModelCommand> doRegister(){

        Observable<RegisterViewModelCommand> observable = Observable.create(new ObservableOnSubscribe<RegisterViewModelCommand>() {
            @Override
            public void subscribe(ObservableEmitter<RegisterViewModelCommand> emitter) throws Exception {
                boolean hasError = false;

                if (!registerModel.isNameValid()){
                    hasError = true;
                    emitter.onNext(new RegisterViewModelCommand.RegisterViewModelCommandNameError());
                }

                if (!registerModel.isEmailValid()){
                    hasError = true;
                    emitter.onNext(new RegisterViewModelCommand.RegisterViewModelCommandEmailError());
                }

                if (!registerModel.isPhoneValid()){
                    hasError = true;
                    emitter.onNext(new RegisterViewModelCommand.RegisterViewModelCommandPhoneNoError());
                }

                if (!registerModel.isAddressValid()){
                    hasError = true;
                    emitter.onNext(new RegisterViewModelCommand.RegisterViewModelCommandAddressError());
                }


                if (!hasError){
                    registerModel.register();
                    emitter.onNext(new RegisterViewModelCommand.RegisterViewModelCommandRegisterSuccess());
                }

            }
        });

        return observable;
    }

    //provide only getter for the model to prevent direct replacement
    public RegisterModel getRegisterModel() {
        return registerModel;
    }


    public interface RegisterViewModelCommand {


        class RegisterViewModelCommandNameError implements RegisterViewModelCommand {
            public RegisterViewModelCommandNameError() {}

            @Override
            public boolean equals(Object obj) {
                return obj instanceof RegisterViewModelCommandNameError;
            }
        }

        class RegisterViewModelCommandEmailError implements RegisterViewModelCommand {
            public RegisterViewModelCommandEmailError() {}
            @Override
            public boolean equals(Object obj) {
                return obj instanceof RegisterViewModelCommandEmailError;
            }
        }

        class RegisterViewModelCommandPhoneNoError implements RegisterViewModelCommand {
            public RegisterViewModelCommandPhoneNoError() {}
            @Override
            public boolean equals(Object obj) {
                return obj instanceof RegisterViewModelCommandPhoneNoError;
            }
        }

        class RegisterViewModelCommandAddressError implements RegisterViewModelCommand {
            public RegisterViewModelCommandAddressError() {}
            @Override
            public boolean equals(Object obj) {
                return obj instanceof RegisterViewModelCommandAddressError;
            }
        }

        class RegisterViewModelCommandRegisterSuccess implements RegisterViewModelCommand {
            public RegisterViewModelCommandRegisterSuccess() {}
            @Override
            public boolean equals(Object obj) {
                return obj instanceof RegisterViewModelCommandRegisterSuccess;
            }
        }

    }

}
