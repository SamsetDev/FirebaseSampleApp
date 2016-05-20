package com.samset.firebaseappexample;

import com.firebase.client.Firebase;

/**
 * Created by weesync on 02/05/16.
 */
public class CrowdWeather extends android.app.Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
