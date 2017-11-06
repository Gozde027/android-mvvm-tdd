package com.example.android_mvvm_tdd.util;

/**
 * Created by vivek on 05/11/17.
 */

public interface ILogger {

    public void d(String tag, String msg);
    public void e(String tag, String msg);
    public void v(String tag, String msg);
    public void w(String tag, String msg);

}
