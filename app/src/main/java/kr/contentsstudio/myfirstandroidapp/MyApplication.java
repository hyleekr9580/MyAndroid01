package kr.contentsstudio.myfirstandroidapp;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by hoyoung on 2016-03-10.
 */
public class MyApplication extends Application {

    // Application 상속 받고 > onCreate 오버라이드 해놓는다.
    @Override
    public void onCreate() {
        super.onCreate();


        Stetho.initializeWithDefaults(this);

    }


}
